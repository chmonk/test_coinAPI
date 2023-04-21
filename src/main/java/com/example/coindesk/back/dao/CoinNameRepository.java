package com.example.coindesk.back.dao;

import com.example.coindesk.back.bean.CoinName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CoinNameRepository extends JpaRepository<CoinName,Integer> {
    CoinName findByEnName(String enName);

    @Modifying
    @Query("delete from CoinName where id = ?1 ")
    public int deleteWithId(Integer id);

}
