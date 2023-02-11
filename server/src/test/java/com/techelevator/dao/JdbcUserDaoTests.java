package com.techelevator.dao;
import com.techelevator.tenmo.dao.JdbcUserDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;


public class JdbcUserDaoTests extends BaseDaoTests{

    private JdbcUserDao sut;

    private static final Account TEST_ACCOUNT = new Account(2007, 1007, 1000);

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        sut = new JdbcUserDao(jdbcTemplate);
    }

    @Test
    public void createNewUserAndAccount() {
        boolean userCreated = sut.create("TEST_USER","test_password");
        Assert.assertTrue(userCreated);
        User user = sut.findByUsername("TEST_USER");
        Assert.assertEquals("TEST_USER", user.getUsername());

        int account = TEST_ACCOUNT.getAccountId();
        Assert.assertEquals(TEST_ACCOUNT.getAccountId(), account);

    }

}
