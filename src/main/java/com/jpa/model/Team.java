package com.jpa.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity//일대다 양방향 Team
@Getter
@Setter
public class Team {
    @Id
    @Column(name = "TEAM_ID")
    private String id;
    private String name;
    @OneToMany(mappedBy = "team")
    private List<Member> members = new ArrayList<Member>();
    //Getter, Setter ...

    public Team(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public Team() {

    }
}