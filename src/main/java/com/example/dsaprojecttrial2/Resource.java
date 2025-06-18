package com.example.dsaprojecttrial2;

//base class for videos section
public abstract class Resource {
    private String id;
    private String title;
    private String description;
    private String topic;
    private String url;
    private int viewCount;

    public Resource(String id, String title, String description, String topic, String url, int viewCount) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.topic = topic;
        this.url = url;
        this.viewCount = viewCount;
    }

    // Getters and setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getViewCount() {
        return viewCount;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }
}
