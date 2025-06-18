package com.example.dsaprojecttrial2;

import java.util.PriorityQueue;

public class WebsiteResult implements Comparable<WebsiteResult> {
    private String title;
    private String url;
    private int priority;

    public WebsiteResult(String title, String url, int priority) {
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

    @Override
    public int compareTo(WebsiteResult other) {
        return Integer.compare(other.priority, this.priority); // Higher priority first
    }
}