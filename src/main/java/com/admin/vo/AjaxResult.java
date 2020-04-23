package com.admin.vo;

import java.io.Serializable;

/**
 * Created by IntelliJ IDEA.
 * User: mousse
 * Date: 2020-03-12
 * Time: 16:11
 * To change this template use File | Settings | File Templates.
 */
public class AjaxResult implements Serializable {

    private String name;

    public AjaxResult() {
        super();
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}

