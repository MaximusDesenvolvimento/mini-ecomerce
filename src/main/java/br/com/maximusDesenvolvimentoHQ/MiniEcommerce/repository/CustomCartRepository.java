package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomCartRepository {

    Page<Cart> findByOrderDateAndUserIdIgnoringTime(String orderDate, String userId, Pageable pageable);
    Page<Cart> findByOrderDateIgnoringTime(String orderDate,Pageable pageable);
    Page<Cart> findByOrderDateMonthIgnoringTime(String orderDate, Pageable pageable);
}
