package com.davis.uitrapulltorefresh.demo.bean;

public class MainItemBean {

    private int id;
    private String name;
    private Class aClass;

    public MainItemBean(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public MainItemBean(int id, String name, Class aClass) {
        this.id = id;
        this.name = name;
        this.aClass = aClass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Class getaClass() {
        return aClass;
    }

    public void setaClass(Class aClass) {
        this.aClass = aClass;
    }
}
