package cn.studyhuang.cook.pojo;

import java.io.Serializable;

public class Material implements Serializable {

    /**
     * materialId : 3005
     * materialName : 老抽
     * materialWeight : 2勺
     * menuId : 3
     */

    private Long materialId;
    private String materialName;
    private String materialWeight;
    private Long menuId;

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public void setMaterialName(String materialName) {
        this.materialName = materialName;
    }

    public String getMaterialWeight() {
        return materialWeight;
    }

    public void setMaterialWeight(String materialWeight) {
        this.materialWeight = materialWeight;
    }

    public Long getMenuId() {
        return menuId;
    }

    public void setMenuId(Long menuId) {
        this.menuId = menuId;
    }
}