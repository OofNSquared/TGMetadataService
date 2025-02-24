package com.TGMetadataService;

import com.TGMetadataService.Clients.VClient;
public class Main {
    public static void main(String[] args) {
        System.out.println("Request for Vid Gen.");
        try {
            VClient instance = new VClient();

            String response = instance.sendRequest();
            System.out.println(response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}