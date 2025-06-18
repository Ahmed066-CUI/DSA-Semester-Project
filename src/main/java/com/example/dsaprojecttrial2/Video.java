package com.example.dsaprojecttrial2;

import java.time.LocalDateTime;

/**
 * Class representing a video resource
 */
public class Video extends Resource {
    private String channelName;
    private String thumbnailUrl;
    private int durationInSeconds;
    private LocalDateTime publishDate;

    public Video(String id, String title, String description, String topic, String url,
                 int viewCount, String channelName, String thumbnailUrl,
                 int durationInSeconds, LocalDateTime publishDate) {
        super(id, title, description, topic, url, viewCount);
        this.channelName = channelName;
        this.thumbnailUrl = thumbnailUrl;
        this.durationInSeconds = durationInSeconds;
        this.publishDate = publishDate;
    }

    // Getters and setters
    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    public int getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(int durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public LocalDateTime getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(LocalDateTime publishDate) {
        this.publishDate = publishDate;
    }

  //formatted date and time
    public String getFormattedDuration() {
        int hours = durationInSeconds / 3600;
        int minutes = (durationInSeconds % 3600) / 60;
        int seconds = durationInSeconds % 60;

        if (hours > 0) {
            return String.format("%d:%02d:%02d", hours, minutes, seconds);
        } else {
            return String.format("%d:%02d", minutes, seconds);
        }
    }
}
