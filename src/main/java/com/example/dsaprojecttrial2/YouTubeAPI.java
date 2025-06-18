package com.example.dsaprojecttrial2;



import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;

//interaction with youtube
public class YouTubeAPI {
    private static final String API_KEY = APIKeyManager.getApiKey("youtube");

    //search based on search query
    public static List<Video> searchVideos(SearchQuery searchQuery) throws IOException {
        String topic = searchQuery.getTopic();
        int maxResults = searchQuery.getMaxResults();

        //building the url
        String searchUrl = Constants.YOUTUBE_API_BASE_URL + "/search" +
                "?part=snippet" +
               "&q=dsa+" + topic.replace(" ", "+") +
                //restricting the topic to only dsa ....
                "&type=video" +
                "&maxResults=" + maxResults +
                "&key=" + API_KEY;

        String searchResponse = HTTPClient.get(searchUrl);
        List<String> videoIds = extractVideoIds(searchResponse);

        //get detailed videos
        return getVideosDetails(videoIds, topic);
    }

    //return ids according to the search
    private static List<String> extractVideoIds(String searchResponse) {
        List<String> videoIds = new ArrayList<>();
        JSONObject jsonResponse = new JSONObject(searchResponse);
        JSONArray items = jsonResponse.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            String videoId = item.getJSONObject("id").getString("videoId");
            videoIds.add(videoId);
        }

        return videoIds;
    }

    //get details
    private static List<Video> getVideosDetails(List<String> videoIds, String topic) throws IOException {
        if (videoIds.isEmpty()) {
            return new ArrayList<>();
        }

        // building url
        StringBuilder videoIdsParam = new StringBuilder();
        for (String id : videoIds) {
            if (videoIdsParam.length() > 0) {
                videoIdsParam.append(",");
            }
            videoIdsParam.append(id);
        }

        String videosUrl = Constants.YOUTUBE_API_BASE_URL + "/videos" +
                "?part=snippet,contentDetails,statistics" +
                "&id=" + videoIdsParam +
                "&key=" + API_KEY;

        String videosResponse = HTTPClient.get(videosUrl);
        return parseVideos(videosResponse, topic);
    }

    //API gives responce in form of JSON which is read using this method
    private static List<Video> parseVideos(String videosResponse, String topic) {
        List<Video> videos = new ArrayList<>();
        JSONObject jsonResponse = new JSONObject(videosResponse);
        JSONArray items = jsonResponse.getJSONArray("items");

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);

            String id = item.getString("id");
            JSONObject snippet = item.getJSONObject("snippet");
            String title = snippet.getString("title");
            String description = snippet.getString("description");
            String channelName = snippet.getString("channelTitle");
            //thumbnail
            JSONObject thumbnails = snippet.getJSONObject("thumbnails");
            String thumbnailUrl = thumbnails.has("high") ?
                    thumbnails.getJSONObject("high").getString("url") :
                    thumbnails.getJSONObject("default").getString("url");
            //publish date
            String publishDateStr = snippet.getString("publishedAt");
            LocalDateTime publishDate = LocalDateTime.parse(publishDateStr, DateTimeFormatter.ISO_DATE_TIME);
            //duration
            String durationStr = item.getJSONObject("contentDetails").getString("duration");
            int durationInSeconds = parseDuration(durationStr);
            //views
            int viewCount = 0;
            if (item.has("statistics") && item.getJSONObject("statistics").has("viewCount")) {
                viewCount = item.getJSONObject("statistics").getInt("viewCount");
            }
            //create object of videos
            String url = Constants.YOUTUBE_VIDEO_BASE_URL + id;
            Video video = new Video(id, title, description, topic, url, viewCount,
                    channelName, thumbnailUrl, durationInSeconds, publishDate);

            videos.add(video);
        }

        return videos;
    }

    //parsing the duration like time
    private static int parseDuration(String durationStr) {
        int seconds = 0;

        //we remove first tw index as in json it is given in format PT 1 hr 40 M 30 s
        String duration = durationStr.substring(2);

        int hoursIndex = duration.indexOf('H');
        if (hoursIndex != -1) {
            seconds += Integer.parseInt(duration.substring(0, hoursIndex)) * 3600;
            duration = duration.substring(hoursIndex + 1);
        }

        int minutesIndex = duration.indexOf('M');
        if (minutesIndex != -1) {
            seconds += Integer.parseInt(duration.substring(0, minutesIndex)) * 60;
            duration = duration.substring(minutesIndex + 1);
        }

        int secondsIndex = duration.indexOf('S');
        if (secondsIndex != -1) {
            seconds += Integer.parseInt(duration.substring(0, secondsIndex));
        }

        return seconds;
    }
}