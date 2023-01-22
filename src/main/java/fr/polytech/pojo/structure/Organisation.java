package fr.polytech.pojo.structure;

import java.util.Set;

public abstract class Organisation {
    private int id;
    private String name;

    private Set<Employee> staff;
    private Set<Employee> administrators;
}
