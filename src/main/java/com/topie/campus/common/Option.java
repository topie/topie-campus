package com.topie.campus.common;

import java.io.Serializable;

/**
 * Created by chenguojun on 9/5/16.
 */
public class Option implements Serializable {

    private static final long serialVersionUID = -2973418153744261705L;

    private String text;

    private Object value;

    public Option() {
    }

    public Option(String text, Object value) {
        this.text = text;
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
