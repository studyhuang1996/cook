package cn.studyhuang.cook.utils;

import java.util.List;

/**
 * Created by huang
 */
public class JsonListResult<T> extends Result {
    private static final long serialVersionUID = 7880907731807860636L;

    /**
     * 数据
     */
    private List<T> data;


    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public JsonListResult() {
        super();
    }
}
