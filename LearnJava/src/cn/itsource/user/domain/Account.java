package cn.itsource.user.domain;

import java.util.Objects;

public class Account {
    private long id;
    private String accName;
    private double money;
    public Account(){}

    public Account(long id, String accName, double money) {
        this.id = id;
        this.accName = accName;
        this.money = money;
    }

    public Account(String accName, double money) {
        this.accName = accName;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && Double.compare(account.money, money) == 0 && Objects.equals(accName, account.accName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accName, money);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", accName='" + accName + '\'' +
                ", money=" + money +
                '}';
    }
}
