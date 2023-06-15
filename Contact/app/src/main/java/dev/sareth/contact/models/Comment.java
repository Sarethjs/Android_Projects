package dev.sareth.contact.models;

import androidx.annotation.NonNull;

public class Comment {

    private final int pubId;
    private final String content;

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

    @NonNull
    @Override
    public String toString() {
        return "Comment{" +
                "pubId=" + pubId +
                ", content='" + content + '\'' +
                '}';
    }
}
