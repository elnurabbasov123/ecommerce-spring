package com.example.ecommerse.service.cardService.impl;

import com.example.ecommerse.model.dto.request.CardRequestDto;
import com.example.ecommerse.model.dto.request.PaymentRequestDto;
import com.example.ecommerse.model.dto.response.CardResponseDto;
import com.example.ecommerse.model.entity.Card;
import com.example.ecommerse.model.entity.Cart;
import com.example.ecommerse.model.entity.User;
import com.example.ecommerse.model.enums.messages.Exceptions;
import com.example.ecommerse.model.exception.ApplicationException;
import com.example.ecommerse.model.exception.NotFoundException;
import com.example.ecommerse.model.exception.response.ExceptionResponse;
import com.example.ecommerse.model.mapper.CardMapper;
import com.example.ecommerse.repository.CardRepository;
import com.example.ecommerse.repository.CartRepository;
import com.example.ecommerse.service.authentication.AuthenticationService;
import com.example.ecommerse.service.cardService.CardService;
import com.example.ecommerse.service.mail.MailService;
import com.example.ecommerse.service.product.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.example.ecommerse.model.enums.messages.Exceptions.NOT_FOUND;

@RequiredArgsConstructor
@Service
public class CardServiceImpl implements CardService {
    private final AuthenticationService authenticationService;
    private final CardMapper cardMapper;
    private final CardRepository cardRepository;
    private final MailService mailService;
    private final OrderService orderService;
    private final CartRepository cartRepository;

    @Override
    public ResponseEntity<Void> add(CardRequestDto requestDto) {
        User user = authenticationService.getAuthenticatedUser();
        Card card = cardMapper.map(requestDto);

        //FIXME: request payment api for this card is real?

        card.setUser(user);
        cardRepository.save(card);
        return ResponseEntity.ok().build();
    }

    @Override
    public ResponseEntity<List<CardResponseDto>> getAll() {
        User user = authenticationService.getAuthenticatedUser();
        List<Card> cards = cardRepository.findAllByUserAndStatus(user, true);
        List<CardResponseDto> cardResponseDtos = new ArrayList<>(cards.size());
        cards.forEach(card -> cardResponseDtos
                .add(CardResponseDto
                        .builder()
                        .id(card.getId())
                        .cardNumber(card.getCardNumber().substring(12))
                        .build()));

        return ResponseEntity.ok(cardResponseDtos);
    }

    @Override
    public ResponseEntity<Void> payment(PaymentRequestDto requestDto) {
        User user = authenticationService.getAuthenticatedUser();

        Card card = cardRepository.findByIdAndUserAndStatus(requestDto.getCardId(), user, true)
                .orElseThrow(() -> new NotFoundException(new ExceptionResponse(HttpStatus.valueOf(NOT_FOUND.getCode()),NOT_FOUND.getMessage()), requestDto.getCardId()));

        Cart cart = cartRepository.findByUser(user).orElseThrow();

        if (cart.getTotalPrice() == null || cart.getTotalPrice().equals(BigDecimal.valueOf(0.00))){
            throw new NullPointerException();
        }

        //FIXME card - cart.getTotalPrice(); --->> send to Payment api ;)

        mailService.createAndSendToMailForPaymentInfo(user, cart.getTotalPrice().doubleValue());

        orderService.createOrder();


        return ResponseEntity.ok().build();
    }
}
