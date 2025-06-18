package com.example.dsaprojecttrial2;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.PriorityQueue;

public class WebsiteSearchAPI {

    private static final String API_KEY = "AIzaSyCU9QOeNubxHz5v1U5UGD9ug44XqUG-j-0";
    private static final String CX = "64b2f155007d84040";

    public PriorityQueue<WebsiteResult> search(String query) {
        PriorityQueue<WebsiteResult> pq = new PriorityQueue<>();
        try {
            if (query == null || query.trim().isEmpty()) {
                System.out.println("Error: Query is empty or null");
                return pq;
            }

            // Append " DSA" to the query to focus on Data Structures and Algorithms
            String modifiedQuery = query + " Data Structure and ALgorithms";
            String urlStr = "https://www.googleapis.com/customsearch/v1?q=" + URLEncoder.encode(modifiedQuery, "UTF-8") +
                    "&key=" + API_KEY + "&cx=" + CX;

            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();
            System.out.println("Response Code: " + responseCode);
            if (responseCode != 200) {
                if (responseCode == 429) {
                    System.out.println("Quota exceeded. Waiting 5 seconds before retrying...");
                    Thread.sleep(5000); // Wait 5 seconds
                    return search(query); // Retry once
                }
                throw new Exception("HTTP Error: " + responseCode);
            }

            BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                sb.append(line);
            }
            br.close();

            JSONObject json = new JSONObject(sb.toString());
            System.out.println("JSON Response: " + json.toString(2));
            JSONArray items = json.getJSONArray("items");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String title = item.getString("title");
                String link = item.getString("link");
                pq.add(new WebsiteResult(title, link, 10 - i));
            }
            System.out.println("Found " + pq.size() + " results for query: " + modifiedQuery);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error querying API with query: " + query);
        }
        return pq;
    }
}