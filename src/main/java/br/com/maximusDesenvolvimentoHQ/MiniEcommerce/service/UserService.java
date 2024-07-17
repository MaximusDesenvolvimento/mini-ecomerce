package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.Adress;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.domain.User;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.AdressMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.mapper.UserMapper;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.AdressRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.repository.UserRepository;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.UserPostRequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AdressRepository adressRepository;

    @Autowired
    public UserService(UserRepository userRepository, AdressRepository adressRepository) {
        this.userRepository = userRepository;
        this.adressRepository = adressRepository;

    }

    public User createUser(UserPostRequestBody userPostRequestBody){
        Adress adress = AdressMapper.INSTANCE.toAdress(userPostRequestBody.getAdress());
        adress = adressRepository.save(adress);
        User user = UserMapper.INSTANCE.toUser(userPostRequestBody);
        user.setAdress(adress);
        return userRepository.save(user);
    }

    public Page<User> listAllUser(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
}
