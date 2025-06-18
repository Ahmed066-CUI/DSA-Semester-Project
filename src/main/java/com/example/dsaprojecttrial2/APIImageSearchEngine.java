package com.example.dsaprojecttrial2;

import java.io.InputStreamReader;
import java.io.BufferedReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.PriorityQueue;

import org.json.JSONArray;
import org.json.JSONObject;

public class APIImageSearchEngine {
    private static final String API_KEY = "AIzaSyCU9QOeNubxHz5v1U5UGD9ug44XqUG-j-0";
    private static final String SEARCH_ENGINE_ID = "64b2f155007d84040";

    public PriorityQueue<ImageResult> searchImages(String query) {
        PriorityQueue<ImageResult> queue = new PriorityQueue<>();
        try {
            // Append " DSA" to the query to focus on Data Structures and Algorithms
            String modifiedQuery = query + " Data Structures and algorithms";
            String encodedQuery = URLEncoder.encode(modifiedQuery, "UTF-8");
            String urlString = "https://www.googleapis.com/customsearch/v1?q=" + encodedQuery
                    + "&cx=" + SEARCH_ENGINE_ID
                    + "&searchType=image"
                    + "&key=" + API_KEY;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder jsonResult = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                jsonResult.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonResult.toString());
            JSONArray items = jsonObject.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String title = item.getString("title");
                String link = item.getString("link");

                // Give random priority for now (or add logic later)
                int priority = title.toLowerCase().contains(query.toLowerCase()) ? 100 : 50;

                queue.add(new ImageResult(title, link, priority));
            }
        } catch (Exception e) {
            System.out.println("API error: " + e.getMessage());
        }

        return queue;
    }
}