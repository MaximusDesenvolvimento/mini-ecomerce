package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Adress;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Product;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.exception.BadRequestException;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.AdressMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.UserMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.AdressRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.UserRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.UserPostRequestBody;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
@Log4j2
public class UserService {

    private final UserRepository userRepository;
    private final AdressRepository adressRepository;
    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, AdressRepository adressRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.adressRepository = adressRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public User findByUserNameOrThrowBadRequestException(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(()->new BadRequestException("Usuario não encontrado."));
        return user;
    }

    public User createUser(UserPostRequestBody userPostRequestBody){
        Optional<User> userOptional = userRepository.findByEmail(userPostRequestBody.getEmail());
        if(userOptional.isEmpty()){
            Adress adress = AdressMapper.INSTANCE.toAdress(userPostRequestBody.getAdress());
            adress = adressRepository.save(adress);
            User user = UserMapper.INSTANCE.toUser(userPostRequestBody);
            user.setAdress(adress);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            return userRepository.save(user);
        }else{
            throw new BadRequestException("Email já existe");
        }
    }

    public Page<User> listAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
