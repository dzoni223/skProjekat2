//package com.example.ServisZakazivanje.client.userservice;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.web.client.RestTemplate;
//
//public class UserServiceClient {
//
//    private final RestTemplate restTemplate;
//
//    public UserServiceClient(RestTemplate restTemplate) {
//        this.restTemplate = restTemplate;
//    }
//
//    public UserDetails getUserDetails(String username) {
//        // Use restTemplate to make a request to the user service
//        String url = "/api/user"; // Adjust the URL based on your user service API
//        return restTemplate.getForObject(url, UserDetails.class, username);
//    }
//}



