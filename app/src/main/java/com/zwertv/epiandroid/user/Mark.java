package com.zwertv.epiandroid.user;

/**
 * Created by Elliott on 31/01/2016.
 */
public class Mark {
    private String ScolarYear;
    private String CodeModule;
    private String TitleModule;
    private String CodeInstance;
    private String CodeActi;
    private String Title;
    private String Date;
    private String Correcteur;
    private int FinalNote;
    private String Comment;

    public Mark(String scolarYear, String codeModule, String titleModule, String codeInstance, String codeActi, String title,
                String date, String correcteur, int finalNote, String comment) {
        ScolarYear = scolarYear;
        CodeModule = codeModule;
        TitleModule = titleModule;
        CodeInstance = codeInstance;
        CodeActi = codeActi;
        Title = title;
        Date = date;
        Correcteur = correcteur;
        FinalNote = finalNote;
        Comment = comment;
    }

    public String getScolarYear() {
        return ScolarYear;
    }

    public String getCodeModule() {
        return CodeModule;
    }

    public String getTitleModule() {
        return TitleModule;
    }

    public String getCodeInstance() {
        return CodeInstance;
    }

    public String getCodeActi() {
        return CodeActi;
    }

    public String getTitle() {
        return Title;
    }

    public String getDate() {
        return Date;
    }

    public String getCorrecteur() {
        return Correcteur;
    }

    public int getFinalNote() {
        return FinalNote;
    }

    public String getComment() {
        return Comment;
    }
}
