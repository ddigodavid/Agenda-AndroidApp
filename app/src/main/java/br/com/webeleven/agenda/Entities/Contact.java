package br.com.webeleven.agenda.Entities;

public class Contact {
    protected int id;
    protected String name;
    protected String address;
    protected String phone;
    protected String email;
    protected double score;

    public int getId() {
        return id;
    }

    public Contact setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Contact setName(String name) {
        this.name = name;
        return this;
    }

    public String getAddress() {
        return address;
    }

    public Contact setAddress(String address) {
        this.address = address;
        return this;
    }

    public String getPhone() {
        return phone;
    }

    public Contact setPhone(String phone) {
        this.phone = phone;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public Contact setEmail(String email) {
        this.email = email;
        return this;
    }

    public double getScore() {
        return score;
    }

    public Contact setScore(double score) {
        this.score = score;
        return this;
    }
}
