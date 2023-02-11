package com.techelevator.dao;
import com.techelevator.tenmo.dao.JdbcTransferDao;
import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcTransferDaoTests extends BaseDaoTests {

    private static final Transfer TRANSFER_1 = new Transfer(1001, 1002, 500, 3001);

    private JdbcTransferDao jdbcTransferDao;
    private Transfer testTransfer;
    private Account testAccount;

    @Before
    public void setup() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        jdbcTransferDao = new JdbcTransferDao(jdbcTemplate);
        testTransfer = new Transfer(1001, 1002, 500, 3001);
        testAccount = new Account(2001, 1001, 1000);


    }

    @Test
    public void create_transfer_id_works_correctly () {
        Transfer createdTransfer = new Transfer();
        createdTransfer.setTransferId(3001);
        createdTransfer.setSenderId(1001);
        createdTransfer.setReceiverId(1002);
        createdTransfer.setDepositAmount(500);
    }


    @Test
    public void find_transfer_by_userId_works_correctly () {
        int findTransfer = testTransfer.getTransferId();
        Assert.assertEquals(TRANSFER_1.getTransferId(), findTransfer);
    }

    @Test
    public void update_balance_works_correctly(){
        Transfer update = new Transfer();
        update.setDepositAmount(500);
        double updateBalance = testTransfer.getDepositAmount();
        Assert.assertEquals(update.getDepositAmount(), updateBalance, 0.001);
    }
}
