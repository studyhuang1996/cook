package cn.studyhuang.cook.pojo;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
    /**
     * succee : true
     * code : 0
     * msg : 操作成功
     * data : [{"cid":1,"cname":"家常菜"},{"cid":2,"cname":"快手菜"},{"cid":3,"cname":"下饭菜"},{"cid":4,"cname":"早餐"},{"cid":5,"cname":"减肥"},{"cid":6,"cname":"汤羹"},{"cid":7,"cname":"烘焙"},{"cid":9,"cname":"我的天"},{"cid":49,"cname":"的广"},{"cid":50,"cname":"打个梵蒂"},{"cid":52,"cname":"英雄"},{"cid":53,"cname":"100万"},{"cid":54,"cname":"蝴蝶酥"}]
     * datas : null
     */

    private boolean succee;
    private int code;
    private String msg;
    private Object datas;
    private List<DataBean> data;

    public boolean isSuccee() {
        return succee;
    }

    public void setSuccee(boolean succee) {
        this.succee = succee;
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

    public Object getDatas() {
        return datas;
    }

    public void setDatas(Object datas) {
        this.datas = datas;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * cid : 1
         * cname : 家常菜
         */

        private Long cid;
        private String cname;
        private String cpic;

        public String getCpic() {
            return cpic;
        }

        public void setCpic(String cpic) {
            this.cpic = cpic;
        }
        public Long getCid() {
            return cid;
        }

        public void setCid(Long cid) {
            this.cid = cid;
        }

        public String getCname() {
            return cname;
        }

        public void setCname(String cname) {
            this.cname = cname;
        }
    }
//    private Long cid;
//
//    private String cname;
//
//    public Long getCid() {
//        return cid;
//    }
//
//    public void setCid(Long cid) {
//        this.cid = cid == null ? null : cid;
//    }
//
//    public String getCname() {
//        return cname;
//    }
//
//    public void setCname(String cname) {
//        this.cname = cname == null ? null : cname.trim();
//    }
}