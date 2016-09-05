package com.topie.campus.common;

import java.io.Serializable;

/**
 * Created by cgj on 2015/11/23.
 */
public class TreeNode implements Serializable {

    private static final long serialVersionUID = -5642499451755338385L;

    private int id;

    private int pId;

    private String name;

    private boolean checked;

    private boolean open;

    private String icon;

    private String url;

    private String type;

    private int i;

    private String s;

    private boolean b;

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isOpen() {
        return open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public int getI() {
        return i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public boolean isB() {
        return b;
    }

    public void setB(boolean b) {
        this.b = b;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
