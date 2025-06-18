

package com.example.dsaprojecttrial2;

import java.util.Arrays;

public class SearchQuery {
    private String topic;
    private static final int MAX_RESULTS = 7; // Fixed to top 5 videos
    private boolean strictMode;

    public SearchQuery(String topic) {
        this(topic, false); // Default to non-strict mode for better YouTube matching
    }

    public SearchQuery(String topic, boolean strictMode) {
        setTopic(topic);
        setStrictMode(strictMode);
    }

    // Getters and setters with validation
    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        if (topic == null || topic.trim().isEmpty()) {
            throw new IllegalArgumentException("Topic cannot be null or empty");
        }

        topic = topic.trim();
        if (!topic.toLowerCase().contains("dsa")) {
            this.topic = "DSA " + topic;  // Enforce DSA at the beginning
        } else {
            this.topic = topic;
        }
    }

    public int getMaxResults() {
        return MAX_RESULTS; // Always returns 5
    }

    public void setStrictMode(boolean strictMode) {
        this.strictMode = strictMode;
    }

    @Override
    public String toString() {
        return String.format("SearchQuery{topic='%s', maxResults=%d, strictMode=%s}",
                topic, MAX_RESULTS, strictMode);
    }
}
