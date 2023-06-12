package dev.sareth.contact.models;

public class Comment {

    private int pubId;
    private String content;

    public Comment(int pubId, String content) {
        this.pubId = pubId;
        this.content = content;
    }

    public int getPubId() {
        return pubId;
    }

    public String getContent() {
        return content;
    }
}
