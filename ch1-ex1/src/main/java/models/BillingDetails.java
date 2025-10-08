package models;

import java.util.HashSet;
import java.util.Set;

public class BillingDetails {
    private String account;
    private String bankname;
    private User user;

//    Many-to-Many association
//    private Set<User> users = new HashSet<>();

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getBankName() {
        return bankname;
    }

    public void setBankName(String bankname) {
        this.bankname = bankname;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
