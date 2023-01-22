package pojo.structure;

import java.util.Set;

public class Organisation {
    private int id;
    private String name;

    private Set<User> staff;
    private Set<User> administrators;
}
