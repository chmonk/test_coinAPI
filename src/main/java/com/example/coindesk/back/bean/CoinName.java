package com.example.coindesk.back.bean;


import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

}
