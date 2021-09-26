package cn.itsource.user.domain;

import java.util.Objects;

public class User {
    private long id;
    private String name;
    private String pwd;
    private double money;

    public User(){}

    /**
     * 添加user对象，不需要id
     * @param name
     * @param pwd
     * @param money
     */
    public User(String name, String pwd, double money) {
        this.name = name;
        this.pwd = pwd;
        this.money = money;
    }

    /**
     * 添加yser对象，不需要id，money
     * @param name
     * @param pwd
     */
    public User(String name, String pwd) {
        this.name = name;
        this.pwd = pwd;
    }

    /**
     * 修改或删除或查询user对象
     * @param id
     * @param name
     * @param pwd
     * @param money
     */
    public User(long id, String name, String pwd, double money) {
        this.id = id;
        this.name = name;
        this.pwd = pwd;
        this.money = money;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
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
        User user = (User) o;
        return id == user.id && Double.compare(user.money, money) == 0 && Objects.equals(name, user.name) && Objects.equals(pwd, user.pwd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, pwd, money);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", pwd='" + pwd + '\'' +
                ", money=" + money +
                '}';
    }
}
