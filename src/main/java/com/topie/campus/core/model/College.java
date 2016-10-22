package com.topie.campus.core.model;

import javax.persistence.*;

@Table(name = "t_college")
public class College {
    @Id
    private String xydm;

    private String xymc;

    private String xyjc;

    /**
     * @return xydm
     */
    public String getXydm() {
        return xydm;
    }

    /**
     * @param xydm
     */
    public void setXydm(String xydm) {
        this.xydm = xydm;
    }

    /**
     * @return xymc
     */
    public String getXymc() {
        return xymc;
    }

    /**
     * @param xymc
     */
    public void setXymc(String xymc) {
        this.xymc = xymc;
    }

    /**
     * @return xyjc
     */
    public String getXyjc() {
        return xyjc;
    }

    /**
     * @param xyjc
     */
    public void setXyjc(String xyjc) {
        this.xyjc = xyjc;
    }
}