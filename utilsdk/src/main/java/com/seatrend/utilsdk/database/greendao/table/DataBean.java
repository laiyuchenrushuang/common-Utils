package com.seatrend.utilsdk.database.greendao.table;


import androidx.annotation.NonNull;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;

import java.util.regex.Pattern;

/**
 * Created by ly on 2020/11/20 11:56
 */
@Entity
public class DataBean{
    /**
     * xtlb : 00
     * dmlb : 1007
     * dmz : 01
     * dmsm1 : 大型汽车
     * dmsm2 : 黄底黑字
     * dmsm3 : 1
     * dmsm4 : 6
     * zt : 1
     */
    @Id
    private Long id;

    private String xtlb;
    private String dmlb;
    private String dmz;
    private String dmsm1;
    private String dmsm2;
    private String dmsm3;
    private String dmsm4;
    private String zt;
    @Generated(hash = 1733420843)
    public DataBean(Long id, String xtlb, String dmlb, String dmz, String dmsm1,
            String dmsm2, String dmsm3, String dmsm4, String zt) {
        this.id = id;
        this.xtlb = xtlb;
        this.dmlb = dmlb;
        this.dmz = dmz;
        this.dmsm1 = dmsm1;
        this.dmsm2 = dmsm2;
        this.dmsm3 = dmsm3;
        this.dmsm4 = dmsm4;
        this.zt = zt;
    }
    @Generated(hash = 908697775)
    public DataBean() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getXtlb() {
        return this.xtlb;
    }
    public void setXtlb(String xtlb) {
        this.xtlb = xtlb;
    }
    public String getDmlb() {
        return this.dmlb;
    }
    public void setDmlb(String dmlb) {
        this.dmlb = dmlb;
    }
    public String getDmz() {
        return this.dmz;
    }
    public void setDmz(String dmz) {
        this.dmz = dmz;
    }
    public String getDmsm1() {
        return this.dmsm1;
    }
    public void setDmsm1(String dmsm1) {
        this.dmsm1 = dmsm1;
    }
    public String getDmsm2() {
        return this.dmsm2;
    }
    public void setDmsm2(String dmsm2) {
        this.dmsm2 = dmsm2;
    }
    public String getDmsm3() {
        return this.dmsm3;
    }
    public void setDmsm3(String dmsm3) {
        this.dmsm3 = dmsm3;
    }
    public String getDmsm4() {
        return this.dmsm4;
    }
    public void setDmsm4(String dmsm4) {
        this.dmsm4 = dmsm4;
    }
    public String getZt() {
        return this.zt;
    }
    public void setZt(String zt) {
        this.zt = zt;
    }

}

