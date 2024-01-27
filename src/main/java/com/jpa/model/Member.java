package com.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
<<<<<<< HEAD
import lombok.Getter;
import lombok.Setter;

//@Data
//@Entity
//@Table(name = "MEMBER")
//public class Member {
//    @Id
//    @Column(name = "ID")
//    private String id;
//    @Column(name = "NAME")
//    private String username;
//    //매핑 정보가 없는 필드
//    private Integer age;
//}
@Entity
@Getter
@Setter
=======
import org.springframework.stereotype.Component;

import java.util.Date;

@Entity
@Table(name="MEMBER", uniqueConstraints = {@UniqueConstraint( //추가 //**
        name = "NAME_AGE_UNIQUE",
        columnNames = {"NAME", "AGE"} )})
>>>>>>> 2bdbab1b4edf30f1e603987a5c64c440df53008a
public class Member {
    @Id
    @Column(name = "MEMBER_ID")
    private String id;
<<<<<<< HEAD
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
=======

    @Column(name = "NAME", nullable = false, length = 10) //추가 //**
//    @Column(name = "NAME") //추가 //**
    private String username;

    private Integer age;

    //=== 추가
    @Enumerated(EnumType.STRING)
    private RoleType roleType;

    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;

    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @Lob
    private String description;

    @Transient
    private String temp;

>>>>>>> 2bdbab1b4edf30f1e603987a5c64c440df53008a
}
