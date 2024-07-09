package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.service;

import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.client.GitHubClient;
import br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response.GitHubFileResponse;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Base64;

@Service
@Log4j2
public class GitHubService {

    private final RestTemplate restTemplate;
    private final GitHubClient gitHubClient;
    @Value("${GITHUB_URL}")
    private String githubApiUrl;
    @Value("${GITHUB_ACCESS_TOKEN}")
    private String githubAccessToken;

    @Autowired
    public GitHubService(GitHubClient gitHubClient, RestTemplate restTemplate) {
        this.gitHubClient = gitHubClient;
        this.restTemplate = restTemplate;
    }

    public ResponseEntity<GitHubFileResponse> uploadImage(String id, byte[] imageContent) throws IOException {

        String encodedImage = Base64.getEncoder().encodeToString(imageContent);

        String repo = "imagens";
        String path = "product_"+id+"/imagem.png";
        String owner = "MaximusDesenvolvimento";
        String message = "Upload da imagem feita!";
        return gitHubClient.uploadImage(githubAccessToken,githubApiUrl,message,encodedImage,owner,repo,path);
    }

    public ResponseEntity<GitHubFileResponse> replaceImage(String id,String sha, byte[] imageContent) throws IOException {

        String encodedImage = Base64.getEncoder().encodeToString(imageContent);

        String repo = "imagens";
        String path = "product_"+id+"/imagem.png";
        String owner = "MaximusDesenvolvimento";
        String message = "Replace da imagem feita!";
        return gitHubClient.replaceImage(githubAccessToken,githubApiUrl,message,encodedImage,owner,repo,path,sha);
    }
    public ResponseEntity<String> deleteImage(String id,String sha) throws IOException {


        String repo = "imagens";
        String path = "product_"+id+"/imagem.png";
        String owner = "MaximusDesenvolvimento";
        String message = "Delete da imagem feita!";
        return gitHubClient.deleteImage(githubAccessToken,githubApiUrl,message,owner,repo,path,sha);
    }
}
