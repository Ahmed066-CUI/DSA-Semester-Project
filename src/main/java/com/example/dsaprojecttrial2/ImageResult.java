package com.example.dsaprojecttrial2;

public class ImageResult implements Comparable<ImageResult> {
    private String title;
    private String url;
    private int priority;

    public ImageResult(String title, String url, int priority) {
        this.title = title;
        this.url = url;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public int getPriority() {
        return priority;
    }

    @Override
    public int compareTo(ImageResult other) {
        return Integer.compare(other.priority, this.priority); // higher priority first
    }
}