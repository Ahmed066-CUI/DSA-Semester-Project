package com.example.dsaprojecttrial2;

/**
 * PDF model class to represent PDF resources
 */
public class PDF {
    private String title;
    private String description;
    private String url;
    private String topic;
    private double rating;
    private String source;
    private String thumbnailUrl;
    private int pageCount;
    private String difficulty;

    // Constructor
    public PDF(String title, String description, String url, String topic,
               double rating, String source, String thumbnailUrl, int pageCount, String difficulty) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.topic = topic;
        this.rating = rating;
        this.source = source;
        this.thumbnailUrl = thumbnailUrl;
        this.pageCount = pageCount;
        this.difficulty = difficulty;
    }
    public PDF() {}
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getUrl() { return url; }
    public void setUrl(String url) { this.url = url; }

    public String getTopic() { return topic; }
    public void setTopic(String topic) { this.topic = topic; }

    public double getRating() { return rating; }
    public void setRating(double rating) { this.rating = rating; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public String getThumbnailUrl() { return thumbnailUrl; }
    public void setThumbnailUrl(String thumbnailUrl) { this.thumbnailUrl = thumbnailUrl; }

    public int getPageCount() { return pageCount; }
    public void setPageCount(int pageCount) { this.pageCount = pageCount; }

    public String getDifficulty() { return difficulty; }
    public void setDifficulty(String difficulty) { this.difficulty = difficulty; }

    @Override
    public String toString() {
        return "PDF{" +
                "title='" + title + '\'' +
                ", topic='" + topic + '\'' +
                ", rating=" + rating +
                ", source='" + source + '\'' +
                ", difficulty='" + difficulty + '\'' +
                '}';
    }
}