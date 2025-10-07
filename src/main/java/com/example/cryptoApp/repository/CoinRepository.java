package com.example.cryptoApp.repository;

import com.example.cryptoApp.dto.CoinTransactionDTO;
import com.example.cryptoApp.entity.Coin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoinRepository {

    private EntityManager entityManager;

    public CoinRepository(EntityManager entityManager) { this.entityManager = entityManager; }

    @Transactional
    public Coin insert (Coin coin) {
        entityManager.persist(coin);
        return coin;
    }

    @Transactional
    public Coin update(Coin coin) {
        entityManager.merge(coin);
        return coin;
    }

    public List<CoinTransactionDTO> getAll() {
        String jpql = "select new com.example.cryptoApp.dto.CoinTransactionDTO(c.name, sum(c.quantity)) from Coin c group by c.name";
        TypedQuery<CoinTransactionDTO> query = entityManager.createQuery(jpql, CoinTransactionDTO.class);
        return query.getResultList();
    }

    public List<Coin> getByName(String name) {
        String jpql = "select c from Coin c where c.name like :name";
        TypedQuery<Coin> query = entityManager.createQuery(jpql, Coin.class);
        query.setParameter("name", "%" + name + "%");
        return query.getResultList();
    }
/*
    public int remove(int id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }*/
}
