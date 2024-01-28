package com.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

//@Entity
//@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint( //추가 //**
//        name = "NAME_AGE_UNIQUE",
//        columnNames = {"NAME", "AGE"} )})
//public class Member {
//    @Id
//    @Column(name = "MEMBER_ID")
//    private String id;
//    @ManyToOne
//    @JoinColumn(name = "TEAM_ID")
//    private Team team;
//    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
////    @Column(name = "NAME") //추가 //**
//    private String username;
//
//    private Integer age;
//
//    //=== 추가
//    @Enumerated(EnumType.STRING)
//    private RoleType roleType;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date createdDate;
//
//    @Temporal(TemporalType.TIMESTAMP)
//    private Date lastModifiedDate;
//
//    @Lob
//    private String description;
//
//    @Transient
//    private String temp;
//
//}
@Entity//다대일 단방향 Member, 다대일 양방향 Member
@Getter
@Setter
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;
    private String username;
    //연관관계 매핑
    @ManyToOne
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public Member() {

    }

    //연관관계 설정
    public void setTeam(Team team) {
        this.team = team;
    }

    public Member(String id, String username) {
        this.id = id;
        this.username = username;
    }
    //Getter, Setter ...
}