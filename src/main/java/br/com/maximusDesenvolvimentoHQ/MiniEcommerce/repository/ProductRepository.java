package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;

import java.util.List;

public interface ProductRepository {
    List<Product> listAll();
}

