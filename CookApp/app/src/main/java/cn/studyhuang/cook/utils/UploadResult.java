package cn.studyhuang.cook.utils;

import java.io.Serializable;

/**
 * Created by huang on 2018/5/15.
 */

public class UploadResult implements Serializable{

    private String msg;

    private String url;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
