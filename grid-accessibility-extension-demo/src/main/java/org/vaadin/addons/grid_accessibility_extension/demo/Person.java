package org.vaadin.addons.grid_accessibility_extension.demo;

public class Person {

    private String name;
    private String lastName;
    private String attr1;

    public Person(String name, String x, String attr1) {
        setName(name);
        setLastName(x);
        setAttr1(attr1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }
}
