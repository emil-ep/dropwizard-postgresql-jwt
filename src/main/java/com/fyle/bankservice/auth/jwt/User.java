package com.fyle.bankservice.auth.jwt;

import java.security.Principal;


public class User implements Principal {

    private final long id;
    private final String name;
    private final String roles;

    public User(long id, String name, String roles) {
        this.id = id;
        this.name = name;
        this.roles = roles;
    }

    public long getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getRoles() {
        return roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }

        User user = (User) o;

        if (id != user.id) {
            return false;
        }
        if (name != null ? !name.equals(user.name) : user.name != null) {
            return false;
        }
        return roles != null ? roles.equals(user.roles) : user.roles == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (roles != null ? roles.hashCode() : 0);
        return result;
    }
}
