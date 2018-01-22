package com.teamknp.hotel.speification;

import com.teamknp.hotel.entity.Product;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.Predicate;

@Service
public class ProductSpecification {
    public Specification<Product> bySearchKeyword(String keyword) {
        return (root, q, cb) -> {
            String keywordLike = "%" + keyword + "%";
            Predicate namePred = cb.like(root.get("name"), keywordLike);
            Predicate eanPred = cb.like(root.get("ean"), keywordLike);
            return cb.or(namePred, eanPred);
        };
    }

    public Specification<Product> onlyAvailable() {
        return (root, q, cb) -> cb.notEqual(root.get("available"), 0);
    }

    public Specification<Product> onlyOutOfStock() {
        return (root, q, cb) -> cb.greaterThan(root.get("available"), root.get("warning"));
    }
}
