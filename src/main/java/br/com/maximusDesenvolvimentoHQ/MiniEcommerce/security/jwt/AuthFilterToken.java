package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.security.jwt;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
@Log4j2
public class AuthFilterToken extends OncePerRequestFilter {
@Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;



    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        try {
            log.info("não nulo");
            String jwt = getToken(request);
            log.info("teste: "+jwtUtils);
            if(jwt != null && jwtUtils.validateJwtToken(jwt)){
                String email = jwtUtils.getUsernameToken(jwt);

                UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername(email);
                UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
        }catch (Exception e){
            log.info("exception, ocorreu um erro ao processar o token: "+e.getMessage());
        }finally {

        }
        filterChain.doFilter(request,response);

    }

    private String getToken(HttpServletRequest request){
        String headerToken  = request.getHeader("Authorization");
        log.info("TOKEN: "+headerToken.substring(7));
        if (StringUtils.hasText(headerToken) && headerToken.startsWith("Bearer ")){
            return headerToken.substring(7);
        }
        return null;
    }
}
