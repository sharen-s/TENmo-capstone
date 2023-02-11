package com.techelevator.tenmo.model;

public class Transfer {

    private int senderId;
    private int receiverId;
    private double depositAmount;
    private int transferId;

    public Transfer(int senderId, int receiverId, double depositAmount, int transferId) {
        this.senderId = senderId;
        this.receiverId = receiverId;
        this.depositAmount = depositAmount;
        this.transferId = transferId;
    }

    public Transfer() {

    }

    public int getSenderId() {
        return senderId;
    }

    public void setSenderId(int senderId) {
        this.senderId = senderId;
    }

    public int getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(int receiverId) {
        this.receiverId = receiverId;
    }

    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public int getTransferId() {
        return transferId;
    }

    public void setTransferId(int transferId) {
        this.transferId = transferId;
    }
}
