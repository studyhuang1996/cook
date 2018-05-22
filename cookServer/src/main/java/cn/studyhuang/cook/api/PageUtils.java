package cn.studyhuang.cook.api;


import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class PageUtils<T> implements Serializable {
	/** 当前页 */
	private int page = 1;// 当前页
	/** 每页显示记录数 */
	private int limit = 10;// 每页显示记录数
	/** 总记录数 */
	private int count;// 总记录数
	/** 总页数 */
	private int totalPages;

	private List<T> data;

	private int code;

	private String msg;

	private String orderBy;

	//private Map<String, OrderType> orderMap = new LinkedHashMap<String, OrderType>(); //页面排序

	public String getOrderBy() {
		return orderBy;
	}

	public void setOrderBy(String orderBy) {
		this.orderBy = orderBy;
	}

//	public Map<String, OrderType> getOrderMap() {
//		return orderMap;
//	}
//
//	public void setOrderMap(Map<String, OrderType> orderMap) {
//		this.orderMap = orderMap;
//	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getTotalPages() {
		if (this.count < 3 || this.limit == 0) {
			return 1;
		}
		return (this.count + this.limit - 1) / this.limit;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
		this.data = data;
	}

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

	public int getCurPage() {
		return page;
	}
}

