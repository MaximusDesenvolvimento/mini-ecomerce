package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.requests.AuthPostRequest;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.AcessPostResponseBody;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.security.jwt.JwtUtils;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Log4j2
public class AuthService {

    private AuthenticationManager authenticationManager;
    private JwtUtils jwtUtils;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager, JwtUtils jwtUtils) {
        this.authenticationManager = authenticationManager;
        this.jwtUtils = jwtUtils;
    }

    public AcessPostResponseBody login(AuthPostRequest authPostRequest){
        try {
        // cria mecanismo de credencial para o spring
        UsernamePasswordAuthenticationToken userAuth
                = new UsernamePasswordAuthenticationToken(authPostRequest.getUserName()
                ,authPostRequest.getPassword());

        // prepara o mecanismo para autenticacao
        Authentication authentication = authenticationManager.authenticate(userAuth);

        //busca usuario logado
        UserDetailsImpl userAuthenticate = (UserDetailsImpl) authentication.getPrincipal();

        String token = jwtUtils.generateTokenFromUserDetailsImpl(userAuthenticate);

        AcessPostResponseBody acessPostResponseBody = new AcessPostResponseBody(token);
        return acessPostResponseBody;
        }catch (BadCredentialsException e){
            log.info("erro de autenticação"+e.getMessage());
        }
        return new AcessPostResponseBody("Acesso negado");
    }
}
