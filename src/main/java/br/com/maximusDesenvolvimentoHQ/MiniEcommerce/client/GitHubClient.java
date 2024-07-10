package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

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

        // fazer a requisição
        return restTemplate.exchange(githubApiUrl, HttpMethod.DELETE, entity,
                String.class, owner, repo, path);
    }

}

