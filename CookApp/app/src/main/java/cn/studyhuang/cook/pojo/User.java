package cn.studyhuang.cook.pojo;

import java.io.Serializable;

public class User implements Serializable{


    /**
     * uid : 1
     * uname : 10001
     * upassword : 123456
     * uemail : 1135586231@qq.com
     * ubirthday : 1507392000000
     * utel : 12585248745
     * ustate : null
     */

    private int uid;
    private String uname;
    private String upassword;
    private String uemail;
    private long ubirthday;
    private String utel;
    private Object ustate;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getUpassword() {
        return upassword;
    }

    public void setUpassword(String upassword) {
        this.upassword = upassword;
    }

    public String getUemail() {
        return uemail;
    }

    public void setUemail(String uemail) {
        this.uemail = uemail;
    }

    public long getUbirthday() {
        return ubirthday;
    }

    public void setUbirthday(long ubirthday) {
        this.ubirthday = ubirthday;
    }

    public String getUtel() {
        return utel;
    }

    public void setUtel(String utel) {
        this.utel = utel;
    }

    public Object getUstate() {
        return ustate;
    }

    public void setUstate(Object ustate) {
        this.ustate = ustate;
    }
}