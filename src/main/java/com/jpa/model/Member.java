package com.jpa.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.Data;
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
