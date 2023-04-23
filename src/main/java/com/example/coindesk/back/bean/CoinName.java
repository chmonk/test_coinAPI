package com.example.coindesk.back.bean;


import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Data
@Table(name = "COINNAME")
public class CoinName {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private int id;

    @Column(name="EN",length = 20,unique = true)
    private String enName ;

    @Column(name="CH",length = 50)
    private String chName;

    public CoinName(String en,String ch){
        this.enName=en;
        this.chName=ch;
    }

}
