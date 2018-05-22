package cn.studyhuang.cook.utils;

import java.io.Serializable;

/**
 * json返回数据类型. <br/>
 * date:
 *
 * @author
 */
public class Result implements Serializable{
	/**
	 * succee : true
	 * code : 0
	 * msg : 操作成功

	 */
	private Integer code;

	private boolean succee;

	private String msg;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
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


}
