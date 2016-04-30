package com.zwertv.epiandroid.user;

/**
 * Created by Elliott on 31/01/2016.
 */
public class Student {
    private String Login;
    private String Title;
    private String InternalEmail;
    private String LastName;
    private String FirstName;
    private StudentInfo StudentInfo;
    private boolean ReferentUsed;
    private String Picture;
    private String PictureFun;
    private String ScolarYear;
    private int Promo;
    private int Semester;
    private int Uid;
    private int Gid;
    private String Location;
    private String Documents;
    private String UserDocs;
    private String Shell;
    private boolean Close;
    private String CTime;
    private String MTime;
    private String IdPromo;
    private String IdHistory;
    private String CourseCode;
    private String SemesterCode;
    private String SchoolId;
    private String SchoolCode;
    private String SchoolTitle;
    private String OldIdPromo;
    private String OldIdLocation;
    // rights
    private boolean Invited;
    private int StudentYear;
    private boolean Admin;
    private boolean Editable;
    private String Locations;
    // groups
    // events
    private String Gpa[];
    // spice
    private NetSoulStat NetSoulStat;

    public Student(String login, String title, String internalEmail, String lastName, String firstName, StudentInfo infos, boolean referentUsed,
                   String picture, String pictureFun, String scolarYear, int promo, int semester, int uid, int gid, String location,
                   String documents, String userDocs, String shell, boolean close, String cTime, String mTime, String idPromo, String idHistory,
                   String courseCode, String semesterCode, String schoolId, String schoolCode, String schoolTitle, String oldIdPromo, String oldIdLocation,
                   boolean invited, int studentYear, boolean admin, boolean editable, String locations, String gpa[], NetSoulStat netSoulStat) {
        Login = login;
        Title = title;
        InternalEmail = internalEmail;
        LastName = lastName;
        FirstName = firstName;
        ReferentUsed = referentUsed;
        Picture = picture;
        PictureFun = pictureFun;
        ScolarYear = scolarYear;
        Promo = promo;
        Semester = semester; Uid = uid;
        Gid = gid;
        Location = location;
        Documents = documents;
        UserDocs = userDocs;
        Shell = shell;
        Close = close;
        CTime = cTime;
        MTime = mTime;
        IdPromo = idPromo;
        IdHistory = idHistory;
        CourseCode = courseCode;
        SemesterCode = semesterCode;
        SchoolId = schoolId;
        SchoolCode = schoolCode;
        SchoolTitle = schoolTitle;
        OldIdPromo = oldIdPromo;
        OldIdLocation = oldIdLocation;
        Invited = invited;
        StudentYear = studentYear;
        Admin = admin;
        Editable = editable;
        Locations = locations;
        Gpa = gpa;
        NetSoulStat = netSoulStat;
    }

    public String getLogin() {
        return Login;
    }

    public String getTitle() {
        return Title;
    }

    public String getInternalEmail() {
        return InternalEmail;
    }

    public String getLastName() {
        return LastName;
    }

    public String getFirstName() {
        return FirstName;
    }

    public boolean isReferentUsed() {
        return ReferentUsed;
    }

    public String getPicture() {
        return Picture;
    }

    public String getPictureFun() {
        return PictureFun;
    }

    public String getScolarYear() {
        return ScolarYear;
    }

    public int getPromo() {
        return Promo;
    }

    public int getSemester() {
        return Semester;
    }

    public int getUid() {
        return Uid;
    }

    public int getGid() {
        return Gid;
    }

    public String getLocation() {
        return Location;
    }

    public String getDocuments() {
        return Documents;
    }

    public String getUserDocs() {
        return UserDocs;
    }

    public String getShell() {
        return Shell;
    }

    public boolean isClose() {
        return Close;
    }

    public String getCTime() {
        return CTime;
    }

    public String getMTime() {
        return MTime;
    }

    public String getIdPromo() {
        return IdPromo;
    }

    public String getIdHistory() {
        return IdHistory;
    }

    public String getCourseCode() {
        return CourseCode;
    }

    public String getSemesterCode() {
        return SemesterCode;
    }

    public String getSchoolId() {
        return SchoolId;
    }

    public String getSchoolCode() {
        return SchoolCode;
    }

    public String getSchoolTitle() {
        return SchoolTitle;
    }

    public String getOldIdPromo() {
        return OldIdPromo;
    }

    public String getOldIdLocation() {
        return OldIdLocation;
    }

    public boolean isInvited() {
        return Invited;
    }

    public int getStudentYear() {
        return StudentYear;
    }

    public boolean isAdmin() {
        return Admin;
    }

    public boolean isEditable() {
        return Editable;
    }

    public String getLocations() {
        return Locations;
    }

    public String[] getGpa() {
        return Gpa;
    }

    public NetSoulStat getNetSoulStat() {return NetSoulStat;}

    public static class NetSoulStat {
        private int Active;
        private int Idle;
        private int OutActive;
        private int OutIdle;
        private int LogNorm;

        public NetSoulStat(int active, int idle, int outActive, int outIdle, int logNorm) {
            Active = active;
            Idle = idle;
            OutActive = outActive;
            OutIdle = outIdle;
            LogNorm = logNorm;
        }

        public int getActive() {
            return Active;
        }

        public int getIdle() {
            return Idle;
        }

        public int getOutActive() {
            return OutActive;
        }

        public int getOutIdle() {
            return OutIdle;
        }

        public int getLogNorm() {
            return LogNorm;
        }
    }
    public static class StudentInfo {
        private DataWithVisibility Email = null;
        private DataWithVisibility Website = null;
        private DataWithVisibility Poste = null;
        private DataWithVisibility Job = null;

        public StudentInfo(DataWithVisibility email, DataWithVisibility website, DataWithVisibility poste, DataWithVisibility job) {
            Email = email;
            Website = website;
            Poste = poste;
            Job = job;
        }

        public DataWithVisibility getEmail() { return Email; }
        public DataWithVisibility getWebsite() { return Website; }
        public DataWithVisibility getPoste() { return Poste; }
        public DataWithVisibility getJob() { return Job; }

        public boolean hasEmail() { return Email != null; }
        public boolean hasWebsite() { return Website != null; }
        public boolean hasPoste() { return Poste != null; }
        public boolean hasJob() { return Job != null; }

        public static class DataWithVisibility {
            private String Data;
            private boolean Public;
            private boolean Adm;

            public DataWithVisibility(String data, boolean isPublic, boolean adm) {
                Data = data;
                Public = isPublic;
                Adm = adm;
            }

            public String getData() { return Data; }
            public boolean isPublic() { return Public; }
            public boolean isAdm() { return Adm; }
        }
    }
}
