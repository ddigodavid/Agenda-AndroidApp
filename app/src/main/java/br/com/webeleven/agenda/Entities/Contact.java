package br.com.webeleven.agenda.Entities;

import java.io.Serializable;

public class Contact implements Serializable {
    protected int id;
    protected String name;
    protected String address;
    protected String phone;
    protected String email;
    protected float score;

    public int getId() {
        return id;
    }

    public boolean hasId() {
        return id > 0;
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

    public float getScore() {
        return score;
    }

    public Contact setScore(float score) {
        this.score = score;
        return this;
    }

    @Override
    public String toString() {
        return String.format("%s - Telefone: %s", this.getName(), this.getPhone());
    }
}
