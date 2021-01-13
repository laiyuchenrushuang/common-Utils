package com.seatrend.utilsdk.database.greendao.table;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Unique;

/**
 * Created by ly on 2020/12/16 14:42
 *
 * 离线数据库的一级菜单
 */
@Entity
public class OnePictureIndexEntity {
    @Id
    private Long id;

    @Unique
    private String lsh; //车辆流水
    private String hphm;  //号牌号码
    private int zpTotal;//照片总数
    private String cllxmc;//车辆类型
    @Generated(hash = 79664716)
    public OnePictureIndexEntity(Long id, String lsh, String hphm, int zpTotal,
            String cllxmc) {
        this.id = id;
        this.lsh = lsh;
        this.hphm = hphm;
        this.zpTotal = zpTotal;
        this.cllxmc = cllxmc;
    }
    @Generated(hash = 1130799764)
    public OnePictureIndexEntity() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getLsh() {
        return this.lsh;
    }
    public void setLsh(String lsh) {
        this.lsh = lsh;
    }
    public String getHphm() {
        return this.hphm;
    }
    public void setHphm(String hphm) {
        this.hphm = hphm;
    }
    public int getZpTotal() {
        return this.zpTotal;
    }
    public void setZpTotal(int zpTotal) {
        this.zpTotal = zpTotal;
    }
    public String getCllxmc() {
        return this.cllxmc;
    }
    public void setCllxmc(String cllxmc) {
        this.cllxmc = cllxmc;
    }

}
