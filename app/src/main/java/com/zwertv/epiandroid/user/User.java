package com.zwertv.epiandroid.user;

import android.util.Log;

import com.zwertv.epiandroid.requester.HTTPRequester;
import com.zwertv.epiandroid.requester.IRequester;
import com.zwertv.epiandroid.requester.RequesterException;

import junit.framework.Assert;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Elliott on 28/01/2016.
 */
public class User implements IUser {
    private static User ourInstance = new User();

    private static UserCredentials userCredentials = new UserCredentials();
    private static IRequester requester = HTTPRequester.getInstance();
    private static String M_API_URL = "https://epitech-api.herokuapp.com";
    private static String M_LOGIN_URL = "/login";
    private static String M_MESSAGES_URL = "/messages";
    private static String M_MODULES_URL = "/modules";
    private static String M_ALERTS_URL = "/alerts";
    private static String M_MARKS_URL = "/marks";
    private static String M_IMAGE_URL = "/photo";
    private static String M_STUDENT_URL = "/user";
    private static String M_CALENDAR_URL = "/planning";

    public static User getInstance() {
        return ourInstance;
    }

    private User() {
    }

    private String[] getGPA(JSONArray gpao) {
        String gpa[] = null;
        try {
            gpa = new String[2];
            gpa[0] = gpao.getJSONObject(0).getString("gpa");
            if (gpao.length() > 1)
                gpa[1] = gpao.getJSONObject(1).getString("gpa");
            else
                gpa[1] = null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return gpa;
    }
    private Student.StudentInfo getInfos(JSONObject o) {
        Student.StudentInfo infos = null;
        try {
            Student.StudentInfo.DataWithVisibility email = null;
            Student.StudentInfo.DataWithVisibility website = null;
            Student.StudentInfo.DataWithVisibility poste = null;
            Student.StudentInfo.DataWithVisibility job = null;

            JSONObject e;
            if (!o.isNull("email")) {
                e = o.getJSONObject("email");
                email = new Student.StudentInfo.DataWithVisibility(e.getString("value"), e.getBoolean("public"), e.getBoolean("adm"));
            }
            if (!o.isNull("website")) {
                e = o.getJSONObject("website");
                website = new Student.StudentInfo.DataWithVisibility(e.getString("value"), e.getBoolean("public"), e.getBoolean("adm"));
            }
            if (!o.isNull("poste")) {
                e = o.getJSONObject("poste");
                poste = new Student.StudentInfo.DataWithVisibility(e.getString("value"), e.getBoolean("public"), e.getBoolean("adm"));
            }
            if (!o.isNull("job")) {
                e = o.getJSONObject("job");
                job = new Student.StudentInfo.DataWithVisibility(e.getString("value"), e.getBoolean("public"), e.getBoolean("adm"));
            }
            infos = new Student.StudentInfo(email, website, poste, job);
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return infos;
    }
    private CalendarActivity.Room getRoom(JSONObject o) {
        com.zwertv.epiandroid.user.CalendarActivity.Room room = null;
        try {
            room = new CalendarActivity.Room(o.getString("type"), o.getInt("seats"), o.getString("code"));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return room;
    }

    @Override
    public boolean isLogged() {
        if (userCredentials.getToken() == null)
            return false;
        return !userCredentials.getToken().isEmpty();
    }
    @Override
    public boolean login(String login, String password) {
        if (isLogged())
            logout();
        String result = null;
        result = requester.post(M_API_URL + M_LOGIN_URL, "login=" + login + "&password=" + password);
        try {
            JSONObject o = new JSONObject(result);
            userCredentials.setLogin(login);
            userCredentials.setToken(o.getString("token"));
        } catch (JSONException e) {
            return false;
        }
        return isLogged();
    }
    @Override
    public boolean logout() {
        if (!isLogged())
            return false;
        userCredentials.setToken("");
        return true;
    }

    @Override
    public String getLogin() {
        return userCredentials.getLogin();
    }
    @Override
    public String getPictureUrl(String login) {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }
        if (login == null || login.isEmpty()) {
            Log.d("Picture", "login empty");
            return null;
        }

        String res = null;
        String resp = requester.get(M_API_URL + M_IMAGE_URL, "token=" + userCredentials.getToken() + "&login=" + login);
        try {
            JSONObject o = new JSONObject(resp);
            res = o.getString("url");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public Student getStudent(String login) {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }
        if (login == null || login.isEmpty()) {
            Log.d("Picture", "login empty");
            return null;
        }

        Student res = null;
        String resp = requester.get(M_API_URL + M_STUDENT_URL, "token=" + userCredentials.getToken() + "&user=" + login);
        try {
            JSONObject o = new JSONObject(resp);
            String[] gpa = getGPA(o.getJSONArray("gpa"));
            Student.StudentInfo infos = getInfos(o.getJSONObject("userinfo"));
            JSONObject nsstat = o.getJSONObject("nsstat");
            res = new Student(o.getString("login"), o.getString("title"), o.getString("internal_email"), o.getString("lastname"), o.getString("firstname"), infos,
                    o.getBoolean("referent_used"), o.getString("picture"), o.getString("picture_fun"), o.getString("scolaryear"), o.getInt("promo"), o.getInt("semester"),
                    o.getInt("uid"), o.getInt("gid"), o.getString("location"), o.getString("documents"), o.getString("userdocs"), o.getString("shell"), o.getBoolean("close"),
                    o.getString("ctime"), o.getString("mtime"),o.getString("id_promo"), o.getString("id_history"), o.getString("course_code"), o.getString("semester_code"),
                    o.getString("school_id"), o.getString("school_code"), o.getString("school_title"), o.getString("old_id_promo"), o.getString("old_id_location"),
                    o.getBoolean("invited"), o.getInt("studentyear"), o.getBoolean("admin"), o.getBoolean("editable"), o.getString("locations"), gpa,
                    new Student.NetSoulStat(nsstat.getInt("active"), nsstat.getInt("idle"), nsstat.getInt("out_active"), nsstat.getInt("out_idle"), nsstat.getInt("nslog_norm")));
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public List<Message> getMessages() {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }

        List<Message> res = new ArrayList<Message>();
        String resp = requester.get(M_API_URL + M_MESSAGES_URL, "token=" + userCredentials.getToken());
        try {
            JSONArray o = new JSONArray(resp);
            for (int i = 0; i < o.length(); i++) {
                JSONObject r = o.getJSONObject(i);
                JSONObject u = r.getJSONObject("user");
                res.add(new Message(r.getString("title"), r.getString("content"), r.getString("date"), u.getString("title"), u.getString("picture"), u.getString("url")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public List<Module> getModules() {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }

        List<Module> res = new ArrayList<Module>();
        String resp = requester.get(M_API_URL + M_MODULES_URL, "token=" + userCredentials.getToken());
        try {
            JSONObject useless = new JSONObject(resp);
            JSONArray o = useless.getJSONArray("modules");
            for (int i = 0; i < o.length(); i++) {
                JSONObject r = o.getJSONObject(i);
                Module m = new Module(r.getString("scolaryear"), r.getString("id_user_history"), r.getString("codemodule"),
                        r.getString("codeinstance"), r.getString("title"), r.getString("id_instance"), r.getString("date_ins"),
                        r.getString("cycle"), r.getString("grade"), r.getInt("credits"), r.getString("flags"),
                        r.getInt("barrage"), r.getString("module_rating"), r.getInt("semester"));
                res.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public List<Alert> getAlerts() {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }

        List<Alert> res = new ArrayList<Alert>();
        String resp = requester.get(M_API_URL + M_ALERTS_URL, "token=" + userCredentials.getToken());
        try {
            JSONArray o = new JSONArray(resp);
            for (int i = 0; i < o.length(); i++) {
                JSONObject r = o.getJSONObject(i);
                Alert m = new Alert(r.getString("title"));
                res.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public List<Mark> getMarks() {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }

        List<Mark> res = new ArrayList<Mark>();
        String resp = requester.get(M_API_URL + M_MARKS_URL, "token=" + userCredentials.getToken());
        try {
            JSONArray o = new JSONArray(resp);
            for (int i = 0; i < o.length(); i++) {
                JSONObject r = o.getJSONObject(i);
                Mark m = new Mark(r.getString("scolaryear"), r.getString("codemodule"), r.getString("titlemodule"),
                        r.getString("codeinstance"), r.getString("codeacti"), r.getString("title"), r.getString("date"),
                        r.getString("correcteur"), r.getInt("final_note"), r.getString("comment"));
                res.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }

    @Override
    public List<CalendarActivity> getActivities(Date start, Date end) {
        if (!isLogged()) {
            Log.d("Messages ", "not logged");
            return null;
        }

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<CalendarActivity> res = new ArrayList<CalendarActivity>();
        String resp = requester.get(M_API_URL + M_CALENDAR_URL, "token=" + userCredentials.getToken() + "&start=" + dateFormat.format(start) + "&end=" + dateFormat.format(end));
        try {
            Log.d("resp calendar", resp.toString());
            JSONArray o = new JSONArray(resp);
            for (int i = 0; i < o.length(); i++) {
                JSONObject r = o.getJSONObject(i);
                com.zwertv.epiandroid.user.CalendarActivity.Room room = null;
                if (!r.isNull("room"))
                    room = getRoom(r.getJSONObject("room"));
                CalendarActivity m = new CalendarActivity(r.getString("acti_title"), r.getString("instance_location"), r.getBoolean("past"), r.getString("nb_hours"),
                        room, r.getString("codemodule"), r.getString("codeacti"), r.getString("codeevent"),
                        r.getString("codeinstance"), r.getString("scolaryear"), r.getInt("semester"), r.getString("type_code"), r.getString("type_title"), r.getString("titlemodule"),
                        r.getBoolean("allow_register"), r.getBoolean("allow_token"), r.getBoolean("module_registered"), r.getString("start"), r.getString("end"));
                res.add(m);
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return res;
    }
    @Override
    public List<CalendarActivity> getActivitiesOfDay(Date day) {
        Date start = new Date();
        Date end = new Date();

        Log.d("Activity day", day.toString());

        start.setYear(day.getYear());
        start.setMonth(day.getMonth());
        start.setDate(day.getDate());
        end.setYear(day.getYear());
        end.setMonth(day.getMonth());
        end.setDate(day.getDate());

        Log.d("Activity day start", start.toString());
        Log.d("Activity day end", end.toString());

        return getActivities(start, end);
    }
}
