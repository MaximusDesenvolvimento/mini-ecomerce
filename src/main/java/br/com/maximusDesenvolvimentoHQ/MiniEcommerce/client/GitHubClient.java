package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubImageResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Arrays;
import java.util.Base64;
import java.util.Map;
import java.util.Objects;

@Component
@Log4j2
public class GitHubClient {

    RestTemplate restTemplate;
    @Autowired
    public GitHubClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<GitHubFileResponse> uploadImage(String githubAccessToken,
                                                          String githubApiUrl,
                                                          Map<String, Object> requestBody,
                                                          String owner,
                                                          String repo,
                                                          String path) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        // construção da entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

        // fazer a requisição
        return restTemplate.exchange(githubApiUrl, HttpMethod.PUT, entity,
                GitHubFileResponse.class, owner, repo, path);
    }

    public byte[] downloadImage(String githubAccessToken,
                                                          String githubApiUrl,
                                                          Map<String, Object> requestBody,
                                                          String owner,
                                                          String repo,
                                                          String path) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("Accept", "application/vnd.github.v3+json");

        HttpEntity<Void> entity = new HttpEntity<>(httpHeaders);

        // Fazer a requisição
        ResponseEntity<GitHubImageResponse> response = restTemplate.exchange(githubApiUrl, HttpMethod.GET, entity, GitHubImageResponse.class, owner, repo, path);
        log.info("response retornado: "+ response.getBody().getContent());
        if(response.getStatusCode().is2xxSuccessful()){
            return response.getBody().getContent();
        }
//        if (response.getStatusCode().is2xxSuccessful()) {
//            GitHubFileResponse fileResponse = response.getBody();
//            if (fileResponse != null) {
//                String base64Content = String.valueOf(fileResponse.getContent());
//                // Decodificar o conteúdo base64
//                //return Base64.getDecoder().decode(base64Content);
////                log.info("mensagem retornada: "+base64Content);
//                return base64Content;
//            }
//        }
        return null;
    }

    public ResponseEntity<GitHubFileResponse> replaceImage(String githubAccessToken, String githubApiUrl,
                                                           Map<String, Object> requestBody , String owner,
                                                           String repo, String path) throws IOException {

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        // construção da entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);

        // fazer a requisição
        return restTemplate.exchange(githubApiUrl, HttpMethod.PUT, entity,
                GitHubFileResponse.class, owner, repo, path);

    }

    public ResponseEntity<String> deleteImage(String githubAccessToken, String githubApiUrl,
                                              Map<String,Object> requestBody , String owner, String repo, String path)
            throws IOException {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", "Bearer " + githubAccessToken);
        httpHeaders.set("X-GitHub-Api-Version", "2022-11-28");

        // Construção da entidade de requisição
        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, httpHeaders);
        log.info("O status do delete e: "+restTemplate.exchange(githubApiUrl, HttpMethod.DELETE, entity,
                String.class, owner, repo, path).getStatusCode());
        // fazer a requisição
        return restTemplate.exchange(githubApiUrl, HttpMethod.DELETE, entity,
                String.class, owner, repo, path);
    }


}

