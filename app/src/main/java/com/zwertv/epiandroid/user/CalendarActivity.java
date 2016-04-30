package com.zwertv.epiandroid.user;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Elliott on 01/02/2016.
 */
public class CalendarActivity {
    private String ActiTitle;
    private String InstanceLocation;
    private boolean Past;
    private String NbHours;
    private Room Room;
    private String CodeModule;
    private String CodeActi;
    private String CodeEvent;
    private String CodeInstance;
    private String ScolarYear;
    private int Semester;
    private String TypeCode;
    private String TypeTitle;
    private String TitleModule;
    private boolean AllowRegister;
    private boolean AllowToken;
    private boolean ModuleRegistered;
    private String Start;
    private String End;

    public CalendarActivity(String actiTitle, String instanceLocation, boolean past, String nbHours, Room room, String codeModule,
                            String codeActi, String codeEvent, String codeInstance, String scolarYear, int semester, String typeCode,
                            String typeTitle, String titleModule, boolean allowRegister, boolean allowToken, boolean moduleRegistered,
                            String start, String end) {
        ActiTitle = actiTitle;
        InstanceLocation = instanceLocation;
        Past = past;
        NbHours = nbHours;
        Room = room;
        CodeModule = codeModule;
        CodeActi = codeActi;
        CodeEvent = codeEvent;
        CodeInstance = codeInstance;
        ScolarYear = scolarYear;
        Semester = semester;
        TypeCode = typeCode;
        TypeTitle = typeTitle;
        TitleModule = titleModule;
        AllowRegister = allowRegister;
        AllowToken = allowToken;
        ModuleRegistered = moduleRegistered;
        Start = start;
        End = end;
    }

    public String getActiTitle() {
        return ActiTitle;
    }

    public String getInstanceLocation() {
        return InstanceLocation;
    }

    public boolean isPast() {
        return Past;
    }

    public String getNbHours() {
        return NbHours;
    }

    public CalendarActivity.Room getRoom() {
        return Room;
    }

    public String getCodeModule() {
        return CodeModule;
    }

    public String getCodeActi() {
        return CodeActi;
    }

    public String getCodeEvent() {
        return CodeEvent;
    }

    public String getCodeInstance() {
        return CodeInstance;
    }

    public String getScolarYear() {
        return ScolarYear;
    }

    public int getSemester() {
        return Semester;
    }

    public String getTypeCode() {
        return TypeCode;
    }

    public String getTypeTitle() {
        return TypeTitle;
    }

    public String getTitleModule() {
        return TitleModule;
    }

    public boolean isAllowRegister() {
        return AllowRegister;
    }

    public boolean isAllowToken() {
        return AllowToken;
    }

    public boolean isModuleRegistered() { return ModuleRegistered; }

    public String getStart() { return Start; }

    public String getEnd() { return End; }

    public boolean isBetween(Date start, Date end) {
        try {
            Date rs = getTime(this, false);
            Date re = getTime(this, true);

            if (rs.getTime() >= start.getTime() && rs.getTime() <= end.getTime())
                return true;
            if (re.getTime() >= start.getTime() && re.getTime() <= end.getTime())
                return true;
        } catch (Exception e) {}
        return false;
    }

    public static Date getTime(CalendarActivity act, boolean end) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            if (end) {
                return f.parse(act.getEnd());
            } else {
                return f.parse(act.getStart());
            }
        } catch (Exception e) {}
        return null;
    }

    public static class Room {
        private String Type;
        private int Seats;
        private String Code;

        public Room(String type, int seats, String code) {
            Type = type;
            Seats = seats;
            Code = code;
        }

        public String getType() {
            return Type;
        }

        public int getSeats() {
            return Seats;
        }

        public String getCode() {
            return Code;
        }
    }
}
