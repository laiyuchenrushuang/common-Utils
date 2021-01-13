package com.seatrend.utilsdk.entity;

import com.seatrend.utilsdk.httpserver.entity.BaseEntity;

import java.util.List;

/**
 * Created by seatrend on 2018/11/14.
 */

public class CodeEntity extends BaseEntity {

    //代码表 实体
    /**
     * data : [{"xtlb":"00","dmlb":"1007","dmz":"01","dmsm1":"大型汽车","dmsm2":"黄底黑字","dmsm3":"1","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"02","dmsm1":"小型汽车","dmsm2":"蓝底白字","dmsm3":"2","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"03","dmsm1":"使馆汽车","dmsm2":"黑底白字、红使字","dmsm3":"3","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"04","dmsm1":"领馆汽车","dmsm2":"黑底白字、红领字","dmsm3":"3","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"05","dmsm1":"境外汽车","dmsm2":"黑底白/红字","dmsm3":"3","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"06","dmsm1":"外籍汽车","dmsm2":"黑底白字","dmsm3":"3","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"07","dmsm1":"普通摩托车","dmsm2":"黄底黑字","dmsm3":"1","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"08","dmsm1":"轻便摩托车","dmsm2":"蓝底白字","dmsm3":"2","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"09","dmsm1":"使馆摩托车","dmsm2":"黑底白字、红使字","dmsm3":"3","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"10","dmsm1":"领馆摩托车","dmsm2":"黑底白字、红领字","dmsm3":"3","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"11","dmsm1":"境外摩托车","dmsm2":"黑底白字","dmsm3":"3","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"12","dmsm1":"外籍摩托车","dmsm2":"黑底白字","dmsm3":"3","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"13","dmsm1":"低速车","dmsm2":"黄底黑字黑框线","dmsm3":"1","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"14","dmsm1":"拖拉机","dmsm2":"黄底黑字","dmsm3":"1","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"15","dmsm1":"挂车","dmsm2":"黄底黑字黑框线","dmsm3":"1","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"16","dmsm1":"教练汽车","dmsm2":"黄底黑字黑框线","dmsm3":"1","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"17","dmsm1":"教练摩托车","dmsm2":"黄底黑字黑框线","dmsm3":"1","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"18","dmsm1":"试验汽车","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"19","dmsm1":"试验摩托车","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"20","dmsm1":"临时入境汽车","dmsm2":"白底红字黑临时入境","dmsm3":"0","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"21","dmsm1":"临时入境摩托车","dmsm2":"白底红字黑临时入境","dmsm3":"0","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"22","dmsm1":"临时行驶车","dmsm2":"白底黑字黑框线","dmsm3":"0","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"23","dmsm1":"警用汽车","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"24","dmsm1":"警用摩托","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"25","dmsm1":"原农机号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"26","dmsm1":"香港入出境车","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"27","dmsm1":"澳门入出境车","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"31","dmsm1":"武警号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"32","dmsm1":"军队号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"41","dmsm1":"无号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"42","dmsm1":"假号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"43","dmsm1":"挪用号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"51","dmsm1":"大型新能源汽车","dmsm2":"左侧黄色右侧绿色双拼色底黑字","dmsm3":"1","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"52","dmsm1":"小型新能源汽车","dmsm2":"渐变绿底黑字","dmsm3":"2","dmsm4":"6","zt":"1"},{"xtlb":"00","dmlb":"1007","dmz":"99","dmsm1":"其他号牌","dmsm2":null,"dmsm3":"4","dmsm4":null,"zt":"1"}]
     * total : 35
     */

    private int total;
    private List<DataBean> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
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

        private String xtlb;
        private String dmlb;
        private String dmz;
        private String dmsm1;
        private String dmsm2;
        private String dmsm3;
        private String dmsm4;
        private String zt;

        public String getXtlb() {
            return xtlb;
        }

        public void setXtlb(String xtlb) {
            this.xtlb = xtlb;
        }

        public String getDmlb() {
            return dmlb;
        }

        public void setDmlb(String dmlb) {
            this.dmlb = dmlb;
        }

        public String getDmz() {
            return dmz;
        }

        public void setDmz(String dmz) {
            this.dmz = dmz;
        }

        public String getDmsm1() {
            return dmsm1;
        }

        public void setDmsm1(String dmsm1) {
            this.dmsm1 = dmsm1;
        }

        public String getDmsm2() {
            return dmsm2;
        }

        public void setDmsm2(String dmsm2) {
            this.dmsm2 = dmsm2;
        }

        public String getDmsm3() {
            return dmsm3;
        }

        public void setDmsm3(String dmsm3) {
            this.dmsm3 = dmsm3;
        }

        public String getDmsm4() {
            return dmsm4;
        }

        public void setDmsm4(String dmsm4) {
            this.dmsm4 = dmsm4;
        }

        public String getZt() {
            return zt;
        }

        public void setZt(String zt) {
            this.zt = zt;
        }
    }




}
