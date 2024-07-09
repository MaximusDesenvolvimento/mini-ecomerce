package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
@Log4j2
public class GitHubClient {

    RestTemplate restTemplate;
    @Autowired
    public GitHubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<GitHubFileResponse> uploadImage(String githubAccessToken, String githubApiUrl, String message,
                                                          String encodedImage, String owner, String repo, String path)
            throws IOException {
//        log.info("encoder da imagem: "+encodedImage);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("content", encodedImage);

        // Criar entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

        // fazer a requisição
        ResponseEntity<GitHubFileResponse> response = restTemplate.exchange(githubApiUrl, HttpMethod.PUT, entity,
                GitHubFileResponse.class, owner, repo, path);

        log.info("Estou tentando: "+response.getBody().getContent().getHtmlUrl());
        return response;

    }

    public ResponseEntity<GitHubFileResponse> replaceImage(String githubAccessToken, String githubApiUrl, String message,
                                                          String encodedImage, String owner, String repo, String path, String sha)
            throws IOException {
//        log.info("encoder da imagem: "+encodedImage);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("content", encodedImage);
        requestBody.put("sha", sha);

        // Criar entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

        // fazer a requisição
        ResponseEntity<GitHubFileResponse> response = restTemplate.exchange(githubApiUrl, HttpMethod.PUT, entity,
                GitHubFileResponse.class, owner, repo, path);

        log.info("Estou tentando: "+response.getBody().getContent().getHtmlUrl());
        return response;

    }

    public ResponseEntity<String> deleteImage(String githubAccessToken, String githubApiUrl, String message,
                                                          String owner, String repo, String path, String sha)
            throws IOException {
//        log.info("encoder da imagem: "+encodedImage);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("sha", sha);

        // Criar entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

        // fazer a requisição
        ResponseEntity<String> response = restTemplate.exchange(githubApiUrl, HttpMethod.DELETE, entity,
                String.class, owner, repo, path);

        log.info("Estou tentando: "+response.getBody());
        return response;

    }

}

