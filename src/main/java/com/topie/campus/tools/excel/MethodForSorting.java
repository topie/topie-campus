package com.topie.campus.tools.excel;

import java.lang.reflect.Method;

/**
 * The <code>FieldForSorting</code>
 *
 * @author sargeras.wang
 * @version 1.0, Created at 2013年9月17日
 */
public class MethodForSorting {
    private Method method;
    private int index;

    public MethodForSorting(Method method) {
        super();
        this.method = method;
    }

    public MethodForSorting(Method method, int index) {
        super();
        this.method = method;
        this.index = index;
    }

    public Method getMethod() {
        return method;
    }

    public void setMethod(Method method) {
        this.method = method;
    }

    /**
     * @return the index
     */
    public int getIndex() {
        return index;
    }

    /**
     * @param index the index to set
     */
    public void setIndex(int index) {
        this.index = index;
    }

}
