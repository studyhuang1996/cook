package cn.studyhuang.cook.pojo;

import java.util.Date;

public class Evaluate {
    private Long evaluateId;

    private String evaluateContent;

    private Date evaluteDate;

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

    public Date getEvaluteDate() {
        return evaluteDate;
    }

    public void setEvaluteDate(Date evaluteDate) {
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