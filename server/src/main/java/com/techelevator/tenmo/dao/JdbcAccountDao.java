package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


@Override
    public double findBalanceByUserId(Integer userId) {
        String sql = "SELECT balance FROM account WHERE user_id = ?";
        double balance = jdbcTemplate.queryForObject(sql, double.class, userId);
        if (userId != null) {
            return balance ;
        } else {
            return -1 ;
        }
    }

    public int findAccount(Integer accountId) {
        String sql = "SELECT * FROM account WHERE account_id = ?";
        int account = jdbcTemplate.queryForObject(sql, Integer.class, accountId);
        if (accountId != null) {
            return account;
        } else {
            return -1;
        }
    }

    public List<User> getAllUsers () {
        List<User> users = new ArrayList<>();
        String sql = "SELECT username FROM tenmo_user;";
        SqlRowSet results = jdbcTemplate.queryForRowSet(sql);
        while(results.next()) {
            User user = mapRowToUser(results);
            users.add(user);
        }
        return users;
    }

    private User mapRowToUser(SqlRowSet rs) {
        User user = new User();
        user.setUsername(rs.getString("username"));
        return user;
    }


}
