package com.project.back_end.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

@Entity
public class Admin extends BasePerson {

    // id inherited from BaseModel
    // password inherited from BasePerson

    @Column(unique = true, nullable = false, length = 50)
    @NotNull
    private String username;

    public Admin(String username, String password) {
        this();
        this.username = username;
        this.password = password;
    }

    protected Admin() {
        super();
    }

    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }

}