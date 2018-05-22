package cn.studyhuang.cook.api;

import java.io.Serializable;

public class BaseResult implements Serializable{
	boolean succee = true;
	int code =0;
	String msg = "操作成功";
	public BaseResult() {

	}

	public BaseResult(boolean succee, String msg) {
		this.msg = msg;
		this.succee = succee;

	}
	

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public boolean isSuccee() {
		return succee;
	}

	public void setSuccee(boolean succee) {
		this.succee = succee;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public void fail(int code, String msg) {
		this.succee = false;
		this.msg = msg;
		this.code = code;
	}

	public void fail(String msg) {
		this.succee = false;
		this.msg = msg;
	}

	public void fail(int code) {
		this.succee = false;
		this.code = code;
	}

	public void succee(String msg) {
		this.succee = true;
		this.msg = msg;
	}
}
