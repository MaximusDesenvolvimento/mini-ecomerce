package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.response;

public class GitHubImageResponse {

    private byte[] content;
    private String sha;
    private String url;

    public GitHubImageResponse(byte[] content, String sha, String url) {
        this.content = content;
        this.sha = sha;
        this.url = url;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getSha() {
        return sha;
    }

    public void setSha(String sha) {
        this.sha = sha;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
