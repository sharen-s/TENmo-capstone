package com.techelevator.dao;
import com.techelevator.tenmo.dao.JdbcAccountDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class JdbcAccountDaoTests extends BaseDaoTests {

    private Account testAccount;
    private JdbcAccountDao dao;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        dao = new JdbcAccountDao(jdbcTemplate);
        testAccount = new Account(2001, 1001, 1000);
    }

    @Test

    public void list_returns_all_users(){
        List<User> allUsers = dao.getAllUsers();
        Assert.assertEquals(allUsers.size(), 2);
    }

    @Test
    public void find_balance_by_userid_works(){
        double findBalance = testAccount.getBalance();
        Assert.assertEquals(testAccount.getBalance(), findBalance, 0.001);
    }

}
