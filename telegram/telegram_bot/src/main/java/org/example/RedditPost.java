package org.example;


import java.io.InputStream;

public class RedditPost {
    private String description;
    private InputStream imageStream;

    public RedditPost(String description, InputStream imageStream) {
        this.description = description;
        this.imageStream = imageStream;
    }

    public String getDescription() {
        return description;
    }

    public InputStream getImageStream() {
        return imageStream;
    }
}
