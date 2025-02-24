package com.TGMetadataService.Clients;
import com.TGMetadataService.Helpers.ConfigLoader;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class VClient implements AIClientInterface {
    private static final String API_URL = ConfigLoader.get("V_URL");
    private static final String API_KEY = ConfigLoader.get("KEY");
    public String sendRequest() {
        try {
            URL url = new URL(API_URL);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();

            // Request prop. ref - https://github.com/SamurAIGPT/Text-To-Video-API
            conn.setRequestMethod("POST");
            conn.setRequestProperty("X-API-KEY", API_KEY);
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            // Hardcoded test case for podcast generation. Inputs should be decided from user algorithm.
            // We can also dynamically set this by initiating random on selection on the get: theme, topic, voice, etc.
            String requestBody = "{"
                    // Literally my favorite place to eat so its our use case. 10/10 recommend.
                    + "\"url\": \"https://towa-wa.com/about/index.html\","
                    + "\"name1\": \"joe\","
                    + "\"topic\": \"Random AI Story\","
                    + "\"name2\": \"ryan\","
                    + "\"voice\": \"Charlie\","
                    + "\"theme\": \"Hormozi_1\","
                    + "\"language\": \"English\","
                    + "\"duration\": \"30-60\""
                    + "}";

            try (OutputStream os = conn.getOutputStream()) {
                byte[] input = requestBody.getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            int responseCode = conn.getResponseCode();
            InputStream responseStream = (responseCode == 200) ? conn.getInputStream() : conn.getErrorStream();

            try (Scanner scanner = new Scanner(responseStream).useDelimiter("\\A")) {
                return scanner.hasNext() ? scanner.next() : "Empty Response";
            } finally {
                conn.disconnect();
            }

        } catch (Exception e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }
    public getResponse() {
        // {"vid":924216641651} example generated from above.
    }
}
