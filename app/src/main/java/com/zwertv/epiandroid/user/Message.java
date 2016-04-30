package com.zwertv.epiandroid.user;

/**
 * Created by Elliott on 28/01/2016.
 */
public class Message {
    private String Title;
    private String Content;
    private String Date;

    private String UserTitle;
    private String UserPicture;
    private String UserUrl;

    public Message(String title, String content, String date, String userTitle, String userPicture, String userUrl) {
        Title = title;
        Content = content;
        Date = date;
        UserTitle = userTitle;
        UserPicture = userPicture;
        UserUrl = userUrl;
    }

    public String getTitle() { return Title; }
    public String getContent() { return Content; }
    public String getDate() { return Date; }

    public String getUserTitle() { return UserTitle; }
    public String getUserPicture() { return UserPicture; }
    public String getUserUrl() { return UserUrl; }
}
