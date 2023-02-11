package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.User;

import java.util.List;

public interface AccountDao {

    double findBalanceByUserId(Integer userId);
    int findAccount(Integer accountId);
    List<User> getAllUsers();

}
