package com.techelevator.tenmo.controller;
import com.techelevator.tenmo.dao.*;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.User;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.security.Principal;
import java.util.List;

@RestController
@PreAuthorize("isAuthenticated()")

public class AccountController {

    private AccountDao accountDao;
    private UserDao userDao;
    private TransferDao transferDao;

    public AccountController(AccountDao accountDao, UserDao userDao, TransferDao transferDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
        this.transferDao = transferDao;
    }

// get balance
    @RequestMapping(path = "/balance", method = RequestMethod.GET)
    public Double getAccountBalance(Principal principal) {

        int userId = userDao.findIdByUsername(principal.getName());
        return accountDao.findBalanceByUserId(userId);
    }

//    show all users to send money to
    @RequestMapping(path = "/users", method = RequestMethod.GET)
    public List<User> showAllUsers() {
        List<User> allUsers = accountDao.getAllUsers();

        return allUsers;
    }

//    find account
    @RequestMapping(path = "/account", method = RequestMethod.GET)
    public Integer account(Principal principal) {
        int accountId = accountDao.findAccount(Integer.valueOf(principal.getName()));
        return accountDao.findAccount(accountId);
    }

//    transfer money
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/transfer", method = RequestMethod.POST)
    public void transfer(@Valid @RequestBody Transfer newTransfer) {
        if (newTransfer.getSenderId() != newTransfer.getReceiverId() && accountDao.findBalanceByUserId(newTransfer.getSenderId()) >= newTransfer.getDepositAmount() && newTransfer.getDepositAmount() != 0) {
            if (!transferDao.transfer(newTransfer.getSenderId(), newTransfer.getReceiverId(), newTransfer.getDepositAmount())) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Transfer failed.");
            }

        }
    }

//     find transfer by user id
    @RequestMapping(path = "/get-transfer-by-userid/{userId}", method = RequestMethod.GET)
    public Transfer findTransferByUserId(@PathVariable String userId) {
        return transferDao.findTransferByUserId(String.valueOf(userId));
    }

    //     find transfer by transfer id
    @RequestMapping(path = "/get-transfer-by-transferid/{transferId}", method = RequestMethod.GET)
    public Transfer findTransferByTransferId(@PathVariable String transferId) {
        return transferDao.findTransferByTransferId(transferId);
    }

}
