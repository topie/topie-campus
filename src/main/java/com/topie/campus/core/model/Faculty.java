package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_faculty")
public class Faculty {
    /**
     * 系代码
     */
    @Id
    private String xdm;

    private String xmc;

    private String ssxydm;

    private String xfzr;

    private String lxfs;

    private String xclsj;

    private String xkyjyjfx;

    private String xjj;

    private String kyjjxcg;

    private String bz;

    private String ssxsxydm;

    private String xywmc;

    /**
     * 获取系代码
     *
     * @return xdm - 系代码
     */
    public String getXdm() {
        return xdm;
    }

    /**
     * 设置系代码
     *
     * @param xdm 系代码
     */
    public void setXdm(String xdm) {
        this.xdm = xdm;
    }

    /**
     * @return xmc
     */
    public String getXmc() {
        return xmc;
    }

    /**
     * @param xmc
     */
    public void setXmc(String xmc) {
        this.xmc = xmc;
    }

    /**
     * @return ssxydm
     */
    public String getSsxydm() {
        return ssxydm;
    }

    /**
     * @param ssxydm
     */
    public void setSsxydm(String ssxydm) {
        this.ssxydm = ssxydm;
    }

    /**
     * @return xfzr
     */
    public String getXfzr() {
        return xfzr;
    }

    /**
     * @param xfzr
     */
    public void setXfzr(String xfzr) {
        this.xfzr = xfzr;
    }

    /**
     * @return lxfs
     */
    public String getLxfs() {
        return lxfs;
    }

    /**
     * @param lxfs
     */
    public void setLxfs(String lxfs) {
        this.lxfs = lxfs;
    }

    /**
     * @return xclsj
     */
    public String getXclsj() {
        return xclsj;
    }

    /**
     * @param xclsj
     */
    public void setXclsj(String xclsj) {
        this.xclsj = xclsj;
    }

    /**
     * @return xkyjyjfx
     */
    public String getXkyjyjfx() {
        return xkyjyjfx;
    }

    /**
     * @param xkyjyjfx
     */
    public void setXkyjyjfx(String xkyjyjfx) {
        this.xkyjyjfx = xkyjyjfx;
    }

    /**
     * @return xjj
     */
    public String getXjj() {
        return xjj;
    }

    /**
     * @param xjj
     */
    public void setXjj(String xjj) {
        this.xjj = xjj;
    }

    /**
     * @return kyjjxcg
     */
    public String getKyjjxcg() {
        return kyjjxcg;
    }

    /**
     * @param kyjjxcg
     */
    public void setKyjjxcg(String kyjjxcg) {
        this.kyjjxcg = kyjjxcg;
    }

    /**
     * @return bz
     */
    public String getBz() {
        return bz;
    }

    /**
     * @param bz
     */
    public void setBz(String bz) {
        this.bz = bz;
    }

    /**
     * @return ssxsxydm
     */
    public String getSsxsxydm() {
        return ssxsxydm;
    }

    /**
     * @param ssxsxydm
     */
    public void setSsxsxydm(String ssxsxydm) {
        this.ssxsxydm = ssxsxydm;
    }

    /**
     * @return xywmc
     */
    public String getXywmc() {
        return xywmc;
    }

    /**
     * @param xywmc
     */
    public void setXywmc(String xywmc) {
        this.xywmc = xywmc;
    }
}