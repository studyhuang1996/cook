package cn.studyhuang.cook.pojo;

public class Category {
    private Long cid;

    private String cname;
    private String cpic;


    public Long getCid() {
        return cid;
    }

    public String getCpic() {
		return cpic;
	}

	public void setCpic(String cpic) {
		this.cpic = cpic;
	}

	public void setCid(Long cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname == null ? null : cname.trim();
    }
}