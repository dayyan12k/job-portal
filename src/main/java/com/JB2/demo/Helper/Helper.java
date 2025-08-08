package com.JB2.demo.Helper;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.OAuth2User;


//yaha hame user ne konsa medium use krke logged in kia hai uske hisab se email(usename) ko return karna hai
//yeh hame islie zroori hai kyuke ham email ka use krke userinfo fetch kar skate hai aur  use profile page par dikha sakte hai
public class Helper {

    public static String getEmailOFLoggedInUsers(Authentication authentication){
       
         
          // agar email is password se login kiya hai to : email kaise nikalenge
        if (authentication instanceof OAuth2AuthenticationToken) {

            var aOAuth2AuthenticationToken = (OAuth2AuthenticationToken) authentication;
            var clientId = aOAuth2AuthenticationToken.getAuthorizedClientRegistrationId();

            var oauth2User = (OAuth2User) authentication.getPrincipal();
            String username = "";

            if (clientId.equalsIgnoreCase("google")) {

                // sign with google
                System.out.println("Getting email from google");
                username = oauth2User.getAttribute("email").toString();

            }
            
            return username;

        }
        else{
            System.out.print("Getting Email from local databse:");
            return authentication.getName().toString();
        }

  
}

    public static String formatTimeAgo(LocalDateTime dateTime) {
        if (dateTime == null) {
            return "Unknown";
        }
        
        LocalDateTime now = LocalDateTime.now();
        
        long minutes = ChronoUnit.MINUTES.between(dateTime, now);
        long hours = ChronoUnit.HOURS.between(dateTime, now);
        long days = ChronoUnit.DAYS.between(dateTime, now);
        long weeks = ChronoUnit.WEEKS.between(dateTime, now);
        long months = ChronoUnit.MONTHS.between(dateTime, now);
        
        if (minutes < 1) {
            return "Just now";
        } else if (minutes < 60) {
            return minutes + (minutes == 1 ? " minute ago" : " minutes ago");
        } else if (hours < 24) {
            return hours + (hours == 1 ? " hour ago" : " hours ago");
        } else if (days < 7) {
            return days + (days == 1 ? " day ago" : " days ago");
        } else if (weeks < 4) {
            return weeks + (weeks == 1 ? " week ago" : " weeks ago");
        } else if (months < 12) {
            return months + (months == 1 ? " month ago" : " months ago");
        } else {
            long years = ChronoUnit.YEARS.between(dateTime, now);
            return years + (years == 1 ? " year ago" : " years ago");
        }
    }
}

