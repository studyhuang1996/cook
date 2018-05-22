package cn.studyhuang.cook.utils;

import java.util.List;

/**
 * Created by huang on 2018/5/3.
 */

public class CallResult<T> {

    private int  code =0;
    private String msg ="";
    private  Boolean succee ;
    private List<T> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Boolean getSuccee() {
        return succee;
    }

    public void setSuccee(Boolean succee) {
        this.succee = succee;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }
}
