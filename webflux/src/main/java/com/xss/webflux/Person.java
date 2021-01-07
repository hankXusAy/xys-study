package com.xss.webflux;

/**
 * @ClassName Person
 * @Description
 * @Author xushaoshuai
 * @Parameters
 * @Date 2021/1/7 3:09 下午
 * @Return
 */

public class Person {

    private Integer id;

    private String name;

    public Integer getId() {
        return id;
    }

    public Person(Integer id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
