package cn.studyhuang.cook.pojo;

import java.io.Serializable;
import java.util.Date;

public class Evaluate implements Serializable {

    private Long evaluateId;

    private String evaluateContent;

    private Long evaluteDate;

    private Long userId;

    private Long mid;

    private Float score;

    private Long replyId;

    public Long getReplyId() {
        return replyId;
    }

    public void setReplyId(Long replyId) {
        this.replyId = replyId;
    }

    public Long getEvaluateId() {
        return evaluateId;
    }

    public void setEvaluateId(Long evaluateId) {
        this.evaluateId = evaluateId;
    }

    public String getEvaluateContent() {
        return evaluateContent;
    }

    public void setEvaluateContent(String evaluateContent) {
        this.evaluateContent = evaluateContent == null ? null : evaluateContent.trim();
    }

    public Long getEvaluteDate() {
        return evaluteDate;
    }

    public void setEvaluteDate(Long evaluteDate) {
        this.evaluteDate = evaluteDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getMid() {
        return mid;
    }

    public void setMid(Long mid) {
        this.mid = mid;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }
}