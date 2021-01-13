package com.seatrend.utilsdk.entity;

/**
 * service 提供实体类
 */
public class PhotoEntity {

    private String lsh; //流水号
    private String xh; //序号
    private String zpzl; //照片种类
    private String zpdz; // 照片id
    private String zpsm; // 车辆识别代号
    private String cffs; // 存放方式
    private String lrr; // 人
    private String lrbm; // 部门
    private String zpPath; //照片加载路径
    private String zpBase64; //照片base64
    private String sfz; // 方便员工备案 增加身份识别标记
    private String zplx;//照片类型(1入场照片，2拆解前照片，3拆解后照片，4拆解签名照）

    private String hphm;//车辆号牌号码
    private String cllxmc;//车辆类型
    private String zp_num;//照片上传次数

    public String getHphm() {
        return hphm;
    }

    public void setHphm(String hphm) {
        this.hphm = hphm;
    }

    public String getCllxmc() {
        return cllxmc;
    }

    public void setCllxmc(String cllxmc) {
        this.cllxmc = cllxmc;
    }

    public String getZp_num() {
        return zp_num;
    }

    public void setZp_num(String zp_num) {
        this.zp_num = zp_num;
    }


    public String getZpBase64() {
        return zpBase64;
    }

    public void setZpBase64(String zpBase64) {
        this.zpBase64 = zpBase64;
    }

    public String getZplx() {
        return zplx;
    }

    public void setZplx(String zplx) {
        this.zplx = zplx;
    }


    public String getSfz() {
        return sfz;
    }

    public void setSfz(String sfz) {
        this.sfz = sfz;
    }
    public String getLsh() {
        return lsh;
    }

    public void setLsh(String lsh) {
        this.lsh = lsh;
    }

    public String getXh() {
        return xh;
    }

    public void setXh(String xh) {
        this.xh = xh;
    }

    public String getZpzl() {
        return zpzl;
    }

    public void setZpzl(String zpzl) {
        this.zpzl = zpzl;
    }

    public String getZpdz() {
        return zpdz;
    }

    public void setZpdz(String zpdz) {
        this.zpdz = zpdz;
    }

    public String getZpsm() {
        return zpsm;
    }

    public void setZpsm(String zpsm) {
        this.zpsm = zpsm;
    }

    public String getCffs() {
        return cffs;
    }

    public void setCffs(String cffs) {
        this.cffs = cffs;
    }

    public String getLrr() {
        return lrr;
    }

    public void setLrr(String lrr) {
        this.lrr = lrr;
    }

    public String getLrbm() {
        return lrbm;
    }

    public void setLrbm(String lrbm) {
        this.lrbm = lrbm;
    }

    public String getZpPath() {
        return zpPath;
    }

    public void setZpPath(String zpPath) {
        this.zpPath = zpPath;
    }
}
