package pojo;

import java.util.List;

public class Client {
    private int id;
    private String name;
    private String password;
    private String email;

    private FidelityAccount fidelityAccount;
    private List<Payment> history;
}
