package cn.studyhuang.cook.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by huang on 2018/1/21.
 */

public class MenuDetails implements Serializable {

    private   Menu menu;
    private  List<Material> material;
    private  List<Step> step;

    public List<Step> getStep() {
        return step;
    }

    public void setStep(List<Step> step) {
        this.step = step;
    }

    public Menu getMenu() {
        return menu;
    }

    public void setMenu(Menu menu) {
        this.menu = menu;
    }

    public List<Material> getMaterial() {
        return material;
    }

    public void setMaterial(List<Material> material) {
        this.material = material;
    }
}
