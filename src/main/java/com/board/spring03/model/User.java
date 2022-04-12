package com.board.spring03.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Setter
@Getter
@NoArgsConstructor
@Entity
public class User extends Timestamped {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    //null 값일 수 없고,  중복될 수 없다.(unique = 고유한 이라는 의미. 고유하다 true= 당연히 중복 안됨, 고유하지 않아 false= 중복가능.)
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    @Enumerated(value = EnumType.STRING) //enum이 db에 저장 될때는 string 으로 변환해서 저장해라
    private UserRoleEnum role;

    @Column(unique = true)
    private Long kakaoId;

    public User (String username, String password, String email, UserRoleEnum role){
        this.username = username;
        this.password =password;
        this.email = email;
        this.role = role;
        this.kakaoId = null;
    }

    public User (String username, String password, String email, UserRoleEnum role, Long kakaoId){
        this.username = username;
        this.password =password;
        this.email = email;
        this.role = role;
        this.kakaoId = kakaoId;
    }

}
