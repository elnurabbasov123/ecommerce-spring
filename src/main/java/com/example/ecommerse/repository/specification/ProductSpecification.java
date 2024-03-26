package com.example.ecommerse.repository.specification;

import com.example.ecommerse.model.entity.Product;
import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class ProductSpecification {
    public Specification<Product> hasName(String name){
        return ((root, query, criteriaBuilder) -> name != null ? criteriaBuilder.like(root.get("name"),"%" + name + "%") : criteriaBuilder.conjunction());
    }
    public Specification<Product> hasFirstPrice(Double firstPrice){
        return ((root, query, criteriaBuilder) -> firstPrice != null ? criteriaBuilder.greaterThan(root.get("price"),firstPrice) : criteriaBuilder.conjunction());
    }
    public Specification<Product> hasSecondPrice(Double secondPrice){
        return ((root, query, criteriaBuilder) -> secondPrice != null ? criteriaBuilder.lessThan(root.get("price"),secondPrice) : criteriaBuilder.conjunction());
    }
    public Specification<Product> hasBrand(Long brandId){
        return ((root, query, criteriaBuilder) ->
                brandId != null ? criteriaBuilder.equal(root.join("brand", JoinType.INNER).get("id"),brandId) : criteriaBuilder.conjunction());
    }
    public Specification<Product> hasCategory(Long categoryId){
        return ((root, query, criteriaBuilder) ->
                categoryId != null ? criteriaBuilder.equal(root.join("categories", JoinType.INNER).get("id"),categoryId) : criteriaBuilder.conjunction());
    }

    public Specification<Product> none(){
        return ((root, query, criteriaBuilder) -> criteriaBuilder.conjunction());
    }
}

