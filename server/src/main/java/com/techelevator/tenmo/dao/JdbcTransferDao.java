package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.Transfer;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.client.ResourceAccessException;

@Component
public class JdbcTransferDao implements TransferDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao (JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean transfer (int senderId, int receiverId, double depositAmount) {

        try {
            createTransferId(senderId, receiverId, depositAmount);
            updateSenderBalance(senderId, depositAmount);
            updateReceiverBalance(receiverId, depositAmount);
            return true;

        } catch (ResourceAccessException e) {
            return false;
        }
    }


    public boolean createTransferId (int senderId, int receiverId, double depositAmount) {

        String sql2 = "INSERT INTO transfers (sender_id, receiver_id, transfer_amount) VALUES (?, ?, ?) RETURNING transfer_id";
        Integer newTransferId;
        try {
            newTransferId = jdbcTemplate.queryForObject(sql2, Integer.class, senderId, receiverId, depositAmount);
        } catch (DataAccessException e) {
            return false;
        }
        return true;
    }

    public Transfer findTransferByUserId(String userId) throws UsernameNotFoundException {
        String sql = "SELECT transfer_id, sender_id, receiver_id, transfer_amount FROM transfers WHERE sender_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, userId);
        Transfer transfer = null;
        if (rowSet.next()){
            transfer = mapRowTOTransfer(rowSet);
        }
        if (transfer == null) {
            throw new UsernameNotFoundException("UserId " + userId + " was not found.");
        } else {
            return transfer;
        }
    }

    public Transfer findTransferByTransferId(String transferId) throws UsernameNotFoundException {

        int transferIdInt = Integer.parseInt(transferId.trim());

        String sql = "SELECT transfer_id, sender_id, receiver_id, transfer_amount FROM transfers WHERE transfer_id = ?;";
        SqlRowSet rowSet = jdbcTemplate.queryForRowSet(sql, transferIdInt);
        Transfer transfer = null;
        if (rowSet.next()){
            transfer = mapRowTOTransfer(rowSet);
        }
        if(transfer == null) {
        throw new UsernameNotFoundException("TransferId " + transferId + " was not found.");
        }
        else {
            return transfer;
        }
    }


    public double updateSenderBalance (Integer senderId, double depositAmount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ?";
        Integer balance = jdbcTemplate.update(sql, depositAmount, senderId);
        if (senderId != null) {
            return balance - depositAmount;
        } else {
            return balance;
        }
    }

    public double updateReceiverBalance (Integer receiverId, double depositAmount) {
        String sql = "UPDATE account SET balance = balance - ? WHERE user_id = ?";
        Integer balance = jdbcTemplate.update(sql, depositAmount, receiverId);
        if (receiverId != null) {
            return balance + depositAmount;
        } else {
            return balance;
        }
    }

    private Transfer mapRowTOTransfer(SqlRowSet rs) {
        Transfer transfer = new Transfer();
        transfer.setTransferId(rs.getInt("transfer_id"));
        transfer.setReceiverId(rs.getInt("receiver_id"));
        transfer.setSenderId(rs.getInt("sender_id"));
        transfer.setDepositAmount(rs.getInt("transfer_amount"));
        return transfer;
    }

}
