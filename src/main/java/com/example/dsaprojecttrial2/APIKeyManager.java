package com.example.dsaprojecttrial2;

public class APIKeyManager {

    // api key added
    private static final String YOUTUBE_API_KEY = "AIzaSyD52yHqldLB0HpOks_f6v6oOu5flm6yQqc";

    public static String getApiKey(String service) {
        switch (service.toLowerCase()) {
            case "youtube":
                return YOUTUBE_API_KEY;

            default:
                return null;
        }
    }
}
