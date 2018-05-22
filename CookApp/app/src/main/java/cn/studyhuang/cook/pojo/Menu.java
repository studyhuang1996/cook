package cn.studyhuang.cook.pojo;

import java.io.Serializable;

public class Menu implements Serializable{


    /**
     * mid : 1
     * mname : 西红柿厚蛋烧
     * mpic : http://s2.cdn.xiachufang.com/620034ea884011e6a9a10242ac110002_650w_650h.jpg?imageView2/1/w/215/h/136/interlace/1/q/90
     * userid : 1
     * cid : 1
     * mdate : 1509638400000
     * mdesc : ╮(╯▽╰)╭ 今天把西红柿炒鸡蛋换成懒人版的西红柿厚蛋烧！厚蛋烧做成番茄炒蛋的亲请注意：
     ①西红柿和鸡蛋的比例是1:2，两枚鸡蛋的话用一个小小西红柿就够了，西红柿多了鸡蛋饼粘不住不好成型！
     ②西红柿切片再横切成丝竖切成丁，去不去皮无所谓，重要的是要细碎，切大块很容易变成番茄炒蛋啊亲！西红柿碎丁越小成功率越高哦～
     ③蛋液量多锅子不够大的情况分次摊嘛，薄点可以摊匀实，太厚会失败滴=_=，虽然叫做厚蛋烧o(╯□╰)o。
     摊的时候注意要小火慢煎～
     */

    private long mid;
    private String mname;
    private String mpic;
    private long userid;
    private long cid;
    private long mdate;
    private String mdesc;

    public long getMid() {
        return mid;
    }

    public void setMid(long mid) {
        this.mid = mid;
    }

    public String getMname() {
        return mname;
    }

    public void setMname(String mname) {
        this.mname = mname;
    }

    public String getMpic() {
        return mpic;
    }

    public void setMpic(String mpic) {
        this.mpic = mpic;
    }

    public long getUserid() {
        return userid;
    }

    public void setUserid(long userid) {
        this.userid = userid;
    }

    public long getCid() {
        return cid;
    }

    public void setCid(long cid) {
        this.cid = cid;
    }

    public long getMdate() {
        return mdate;
    }

    public void setMdate(long mdate) {
        this.mdate = mdate;
    }

    public String getMdesc() {
        return mdesc;
    }

    public void setMdesc(String mdesc) {
        this.mdesc = mdesc;
    }
}