package cn.studyhuang.cook.pojo;

import java.io.Serializable;

public class Step implements Serializable{


    /**
     * sid : 4
     * stepId : 1
     * spic : null
     * menuId : 3
     * sdesc : null
     */

    private Long sid;
    private Integer stepId;
    private String spic;
    private Long menuId;
    private String sdesc;

    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    public Integer getStepId() {
        return stepId;
    }

    public void setStepId(Integer stepId) {
        this.stepId = stepId;
    }

    public String getSpic() {
        return spic;
    }

    public void setSpic(String spic) {
        this.spic = spic;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }

    public String getSdesc() {
        return sdesc;
    }

    public void setSdesc(String sdesc) {
        this.sdesc = sdesc;
    }
}