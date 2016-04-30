package com.zwertv.epiandroid.user;

import com.zwertv.epiandroid.requester.IRequester;


import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Elliott on 28/01/2016.
 */
public interface IUser extends Serializable {
    public boolean isLogged();
    public boolean login(String login, String password);
    public boolean logout();

    public String getLogin();

    public String getPictureUrl(String login);
    public Student getStudent(String login);
    public List<Message> getMessages();
    public List<Module> getModules();
    public List<Alert> getAlerts();
    public List<Mark> getMarks();

    public List<CalendarActivity> getActivities(Date start, Date end);
    public List<CalendarActivity> getActivitiesOfDay(Date day);
}
