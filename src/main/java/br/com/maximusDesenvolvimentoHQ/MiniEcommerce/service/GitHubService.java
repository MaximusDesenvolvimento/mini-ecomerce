package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client.GitHubClient;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Service
@Log4j2
public class GitHubService {

    private final GitHubClient gitHubClient;
    @Value("${GITHUB_URL}")
    private String githubApiUrl;
    @Value("${GITHUB_ACCESS_TOKEN}")
    private String githubAccessToken;

    String repo = "store1";
    String path = "product/imagens/product_";
    String name = "/imagem";
    String type = ".png";
    String owner = "MaximusDesenvolvimento";

    @Autowired
    public GitHubService(GitHubClient gitHubClient) {
        this.gitHubClient = gitHubClient;
    }

    public ResponseEntity<GitHubFileResponse> uploadImage(String id, String encodedImage) throws IOException {

        String repo = this.repo;
        String path = this.path + id + this.name + this.type;
        String owner = this.owner;
        String message = "Upload da imagem feita!";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("content", encodedImage);
        return gitHubClient.uploadImage(githubAccessToken, githubApiUrl, requestBody,owner, repo, path);
    }

    public ResponseEntity<GitHubFileResponse> replaceImage(String id, String sha, String encodedImage) throws IOException {

        String repo = this.repo;
        String path = this.path + id + this.name + this.type;
        String owner = this.owner;
        String message = "Replace da imagem feita!";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("content", encodedImage);
        requestBody.put("sha", sha);

        return gitHubClient.replaceImage(githubAccessToken, githubApiUrl, requestBody, owner, repo, path);
    }

    public ResponseEntity<String> deleteImage(String id, String sha) throws IOException {

        String repo = this.repo;
        String path = this.path + id + this.name + this.type;
        String owner = this.owner;
        String message = "Delete da imagem feita!";

        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("message", message);
        requestBody.put("sha", sha);

        return gitHubClient.deleteImage(githubAccessToken, githubApiUrl, requestBody, owner, repo, path);
    }
}
