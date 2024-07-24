package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<User,String> {

    Optional<User> findByEmail(String email);
    Optional<User> findByPhoneNumber(String phoneNumber);
}
