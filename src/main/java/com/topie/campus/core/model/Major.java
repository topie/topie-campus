package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_major")
public class Major {
    /**
     * 专业代码
     */
    @Id
    private String zydm;

    /**
     * 专业名称
     */
    private String zymc;

    /**
     * 专业类别
     */
    private String zylb;

    /**
     * 学制
     */
    private Integer xz;

    /**
     * 学位
     */
    private String xw;

    private String zyywmc;

    private String ssxydm;

    private String yxj;

    private String ssxdm;

    private String zyjc;

    private String jzydm;

    private String cc;

    private String xklb;

    private String bz;

    private String zszydm;

    private String tjzymc;

    private String sfwy;

    private String sfsf;

    private String sfwxw;

    private String kxlb;

    private String bks;

    private String ssxsxydm;

    private String sfxs;

    private String xkjxpt;

    private String bkflmc;

    private String ywxwlb;

    private String gbxh;

    private String zypymb;

    private String zypyyq;

    private String zykc;

    private String tskc;

    /**
     * 获取专业代码
     *
     * @return zydm - 专业代码
     */
    public String getZydm() {
        return zydm;
    }

    /**
     * 设置专业代码
     *
     * @param zydm 专业代码
     */
    public void setZydm(String zydm) {
        this.zydm = zydm;
    }

    /**
     * 获取专业名称
     *
     * @return zymc - 专业名称
     */
    public String getZymc() {
        return zymc;
    }

    /**
     * 设置专业名称
     *
     * @param zymc 专业名称
     */
    public void setZymc(String zymc) {
        this.zymc = zymc;
    }

    /**
     * 获取专业类别
     *
     * @return zylb - 专业类别
     */
    public String getZylb() {
        return zylb;
    }

    /**
     * 设置专业类别
     *
     * @param zylb 专业类别
     */
    public void setZylb(String zylb) {
        this.zylb = zylb;
    }

    /**
     * 获取学制
     *
     * @return xz - 学制
     */
    public Integer getXz() {
        return xz;
    }

    /**
     * 设置学制
     *
     * @param xz 学制
     */
    public void setXz(Integer xz) {
        this.xz = xz;
    }

    /**
     * 获取学位
     *
     * @return xw - 学位
     */
    public String getXw() {
        return xw;
    }

    /**
     * 设置学位
     *
     * @param xw 学位
     */
    public void setXw(String xw) {
        this.xw = xw;
    }

    /**
     * @return zyywmc
     */
    public String getZyywmc() {
        return zyywmc;
    }

    /**
     * @param zyywmc
     */
    public void setZyywmc(String zyywmc) {
        this.zyywmc = zyywmc;
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
     * @return yxj
     */
    public String getYxj() {
        return yxj;
    }

    /**
     * @param yxj
     */
    public void setYxj(String yxj) {
        this.yxj = yxj;
    }

    /**
     * @return ssxdm
     */
    public String getSsxdm() {
        return ssxdm;
    }

    /**
     * @param ssxdm
     */
    public void setSsxdm(String ssxdm) {
        this.ssxdm = ssxdm;
    }

    /**
     * @return zyjc
     */
    public String getZyjc() {
        return zyjc;
    }

    /**
     * @param zyjc
     */
    public void setZyjc(String zyjc) {
        this.zyjc = zyjc;
    }

    /**
     * @return jzydm
     */
    public String getJzydm() {
        return jzydm;
    }

    /**
     * @param jzydm
     */
    public void setJzydm(String jzydm) {
        this.jzydm = jzydm;
    }

    /**
     * @return cc
     */
    public String getCc() {
        return cc;
    }

    /**
     * @param cc
     */
    public void setCc(String cc) {
        this.cc = cc;
    }

    /**
     * @return xklb
     */
    public String getXklb() {
        return xklb;
    }

    /**
     * @param xklb
     */
    public void setXklb(String xklb) {
        this.xklb = xklb;
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
     * @return zszydm
     */
    public String getZszydm() {
        return zszydm;
    }

    /**
     * @param zszydm
     */
    public void setZszydm(String zszydm) {
        this.zszydm = zszydm;
    }

    /**
     * @return tjzymc
     */
    public String getTjzymc() {
        return tjzymc;
    }

    /**
     * @param tjzymc
     */
    public void setTjzymc(String tjzymc) {
        this.tjzymc = tjzymc;
    }

    /**
     * @return sfwy
     */
    public String getSfwy() {
        return sfwy;
    }

    /**
     * @param sfwy
     */
    public void setSfwy(String sfwy) {
        this.sfwy = sfwy;
    }

    /**
     * @return sfsf
     */
    public String getSfsf() {
        return sfsf;
    }

    /**
     * @param sfsf
     */
    public void setSfsf(String sfsf) {
        this.sfsf = sfsf;
    }

    /**
     * @return sfwxw
     */
    public String getSfwxw() {
        return sfwxw;
    }

    /**
     * @param sfwxw
     */
    public void setSfwxw(String sfwxw) {
        this.sfwxw = sfwxw;
    }

    /**
     * @return kxlb
     */
    public String getKxlb() {
        return kxlb;
    }

    /**
     * @param kxlb
     */
    public void setKxlb(String kxlb) {
        this.kxlb = kxlb;
    }

    /**
     * @return bks
     */
    public String getBks() {
        return bks;
    }

    /**
     * @param bks
     */
    public void setBks(String bks) {
        this.bks = bks;
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
     * @return sfxs
     */
    public String getSfxs() {
        return sfxs;
    }

    /**
     * @param sfxs
     */
    public void setSfxs(String sfxs) {
        this.sfxs = sfxs;
    }

    /**
     * @return xkjxpt
     */
    public String getXkjxpt() {
        return xkjxpt;
    }

    /**
     * @param xkjxpt
     */
    public void setXkjxpt(String xkjxpt) {
        this.xkjxpt = xkjxpt;
    }

    /**
     * @return bkflmc
     */
    public String getBkflmc() {
        return bkflmc;
    }

    /**
     * @param bkflmc
     */
    public void setBkflmc(String bkflmc) {
        this.bkflmc = bkflmc;
    }

    /**
     * @return ywxwlb
     */
    public String getYwxwlb() {
        return ywxwlb;
    }

    /**
     * @param ywxwlb
     */
    public void setYwxwlb(String ywxwlb) {
        this.ywxwlb = ywxwlb;
    }

    /**
     * @return gbxh
     */
    public String getGbxh() {
        return gbxh;
    }

    /**
     * @param gbxh
     */
    public void setGbxh(String gbxh) {
        this.gbxh = gbxh;
    }

    /**
     * @return zypymb
     */
    public String getZypymb() {
        return zypymb;
    }

    /**
     * @param zypymb
     */
    public void setZypymb(String zypymb) {
        this.zypymb = zypymb;
    }

    /**
     * @return zypyyq
     */
    public String getZypyyq() {
        return zypyyq;
    }

    /**
     * @param zypyyq
     */
    public void setZypyyq(String zypyyq) {
        this.zypyyq = zypyyq;
    }

    /**
     * @return zykc
     */
    public String getZykc() {
        return zykc;
    }

    /**
     * @param zykc
     */
    public void setZykc(String zykc) {
        this.zykc = zykc;
    }

    /**
     * @return tskc
     */
    public String getTskc() {
        return tskc;
    }

    /**
     * @param tskc
     */
    public void setTskc(String tskc) {
        this.tskc = tskc;
    }
}