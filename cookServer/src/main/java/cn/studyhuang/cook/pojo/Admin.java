package cn.studyhuang.cook.pojo;

import java.util.Date;

public class Admin {
    private Long aid;

    private String aname;

    private String apassword;

    private String aemail;

    private String atelphone;

    private Date birthday;

    public Long getAid() {
        return aid;
    }

    public void setAid(Long aid) {
        this.aid = aid;
    }

    public String getAname() {
        return aname;
    }

    public void setAname(String aname) {
        this.aname = aname == null ? null : aname.trim();
    }

    public String getApassword() {
        return apassword;
    }

    public void setApassword(String apassword) {
        this.apassword = apassword == null ? null : apassword.trim();
    }

    public String getAemail() {
        return aemail;
    }

    public void setAemail(String aemail) {
        this.aemail = aemail == null ? null : aemail.trim();
    }

    public String getAtelphone() {
        return atelphone;
    }

    public void setAtelphone(String atelphone) {
        this.atelphone = atelphone == null ? null : atelphone.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }
}