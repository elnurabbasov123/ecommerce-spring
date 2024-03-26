package com.example.ecommerse.service.product.impl;

import com.example.ecommerse.model.entity.*;
import com.example.ecommerse.model.enums.type.OrderType;
import com.example.ecommerse.repository.*;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.product.OrderService;
import com.example.ecommerse.service.product.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sound.midi.Track;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final ProductService productService;
    private final AuthenticationService authenticationService;
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderStatusRepository orderStatusRepository;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public ResponseEntity<Void> addProductToCart(Long productId) {
        User user = authenticationService.getAuthenticatedUser();
        Product product = productService.findById(productId);
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        CartItem cartItem = hasCartItem(product, cart);
        updateCartItem(cartItem, product, cart);

        cart.setTotalPrice(cart.getTotalPrice() == null ?
                cartItem.getTotalPrice() : cart.getTotalPrice().add(product.getPrice()));

        cartRepository.save(cart);
        cartItemRepository.save(cartItem);

        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<Void> incrementQuantity(Long productId) {
        User user = authenticationService.getAuthenticatedUser();
        Product product = productService.findById(productId);


        Cart cart = cartRepository.findByUser(user).orElseThrow();
        CartItem cartItem = hasCartItem(product, cart);

        updateCartItem(cartItem, product, cart);

        cart.setTotalPrice(cart.getTotalPrice().add(product.getPrice()));

        cartItemRepository.save(cartItem);
        cartRepository.save(cart);

        return ResponseEntity.ok().build();
    }

    @Override
    @Transactional
    public void createOrder() {
        User user = authenticationService.getAuthenticatedUser();
        Customer customer = customerRepository.findByUser(user).orElseThrow();
        Cart cart = cartRepository.findByUser(user).orElseThrow();

        List<CartItem> cartItems = cartItemRepository.findAllByCartAndStatus(cart, true);

        cartItems
                .forEach(cartItem -> cartItem.setStatus(false));
        cart.setTotalPrice(null);

        OrderStatus orderStatus = orderStatusRepository
                .findByOrderType(OrderType.ON_THE_WAY).orElseThrow();

        Order order = Order.builder()
                .orderStatus(orderStatus)
                .customer(customer)
                .cartItems(cartItems)
                .build();

        orderRepository.save(order);

    }

    private CartItem hasCartItem(Product product, Cart cart) {
        Optional<CartItem> cartItem = cartItemRepository.findByProductAndCartAndStatus(product, cart, true);

        return cartItem.orElseGet(() -> CartItem.builder().build());
    }

    private void updateCartItem(CartItem cartItem, Product product, Cart cart) {
        cartItem.setCart(cart);
        cartItem.setTotalPrice(cartItem.getTotalPrice() == null ?
                product.getPrice() : cartItem.getTotalPrice().add(product.getPrice()));
        cartItem.setQuantity(cartItem.getQuantity() + 1);
        cartItem.setProduct(product);
    }

}
