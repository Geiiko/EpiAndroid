package com.zwertv.epiandroid.user;

import java.nio.channels.GatheringByteChannel;
import java.util.Date;

/**
 * Created by Elliott on 29/01/2016.
 */
public class Module {
    private String ScholarYear;
    private String IdUserHistory;
    private String CodeModule;
    private String CodeInstance;
    private String Title;
    private String IdInstance;
    private String DateInscription;
    private String Cycle;
    private String Grade;
    private int Credits;
    private String Flags;
    private int Barrage;
    private String ModuleRating;
    private int Semester;

    public Module(String scholarYear, String idUserHistory, String codeModule, String codeInstance, String title, String idInstance,
                  String dateInscription, String cycle, String grade, int credits, String flags, int barrage, String moduleRating, int semester) {
        ScholarYear = scholarYear;
        IdUserHistory = idUserHistory;
        CodeModule = codeModule;
        Title = title;
        IdInstance = idInstance;
        DateInscription = dateInscription;
        Cycle = cycle;
        Grade = grade;
        Credits = credits;
        Flags = flags;
        Barrage = barrage;
        ModuleRating = moduleRating;
        Semester = semester;
    }

    public String getScholarYear() { return ScholarYear; }
    public String getIdUserHistory() { return IdUserHistory; }
    public String getCodeModule() { return CodeModule; }
    public String getCodeInstance() { return CodeInstance; }
    public String getTitle() { return Title; }
    public String getIdInstance() { return IdInstance; }
    public String getDateInscription() { return DateInscription; }
    public String getCycle() { return Cycle; }
    public String getGrade() { return Grade; }
    public int getCredits() { return Credits; }
    public String getFlags() { return Flags; }
    public boolean isBarrage() { return Barrage > 0; }
    public String getModuleRating() { return ModuleRating; }
    public int getSemester() { return Semester; }

    public boolean isRated() { return Grade != "-"; }
    public boolean isFailed() { return Grade == "Echec"; }
}
