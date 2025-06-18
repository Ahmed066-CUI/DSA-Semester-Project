package com.example.dsaprojecttrial2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

//video related operations
public class VideoService {

    public List<Video> searchVideosByTopic(String topic) {
        return searchVideosByTopic(topic, 7);
    }

   //restrict videos to top 5 only
    public List<Video> searchVideosByTopic(String topic, int maxResults) {
        try {
            SearchQuery query = new SearchQuery(topic);
            List<Video> videos = YouTubeAPI.searchVideos(query);
            return videos.stream()
                    .sorted(Comparator.comparingInt(Video::getViewCount).reversed())
                    .limit(maxResults)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }
}