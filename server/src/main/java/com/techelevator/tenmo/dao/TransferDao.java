package com.techelevator.tenmo.dao;
import com.techelevator.tenmo.model.Transfer;


public interface TransferDao {

    boolean transfer(int senderId, int receiverId, double depositAmount);

    boolean createTransferId (int senderId, int receiverId, double depositAmount);

    Transfer findTransferByUserId(String userId);

    Transfer findTransferByTransferId(String transferId);

    double updateSenderBalance (Integer senderId, double depositAmount);

    double updateReceiverBalance (Integer receiverId, double depositAmount);


}
