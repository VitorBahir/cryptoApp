package com.example.cryptoApp.repository;

import com.example.cryptoApp.dto.CoinTransactionDTO;
import com.example.cryptoApp.entity.Coin;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CoinRepository {

    private EntityManager entityManager;

    public CoinRepository(EntityManager entityManager) { this.entityManager = entityManager; }

    public Coin insert (Coin coin) {
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(coin);
        transaction.commit();
        return coin;
    }

    public Coin update(Coin coin) {
        Object[] attr = new Object[] {
            coin.getName(),
            coin.getPrice(),
            coin.getQuantity(),
            coin.getId()
        };
        jdbcTemplate.update(UPDATE, attr);
        return coin;
    }

    public List<CoinTransactionDTO> getAll() {
        return jdbcTemplate.query(SELECT_ALL, new RowMapper<CoinTransactionDTO>() {
            @Override
            public CoinTransactionDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
                CoinTransactionDTO coin = new CoinTransactionDTO();
                coin.setName(rs.getString("name"));
                coin.setQuantity(rs.getBigDecimal("quantity"));
                return coin;
            }
        });
    }

    public List<Coin> getByName(String name) {
        Object[] attr = new Object[] { name };

        return jdbcTemplate.query(SELECT_BY_NAME, new RowMapper<Coin>() {
            @Override
            public Coin mapRow(ResultSet rs, int rowNum) throws SQLException {
                Coin coin = new Coin();
                coin.setId(rs.getInt("id"));
                coin.setName(rs.getString("name"));
                coin.setQuantity(rs.getBigDecimal("quantity"));
                coin.setPrice(rs.getBigDecimal("price"));
                coin.setDateTime(rs.getTimestamp("datetime"));
                return coin;
            }
        }, attr);
    }

    public int remove(int id) {
        return jdbcTemplate.update(DELETE_BY_ID, id);
    }
}
