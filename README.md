# Module 2 Capstone - TEnmo

TEnmo is an online payment service for transferring TE bucks between friends. This repository contains the RESTful API server and command-line application for TEnmo.

Functionality
TEnmo provides the following functionality:

Register a user with a username and password. A new user starts with an initial balance of 1,000 TE bucks.
Log in using a registered username and password. This returns an Authentication Token to be included in subsequent interactions with the system.
View the current account balance.
Send a transfer of a specific amount of TE bucks to a registered user. The sender selects the recipient from a list of users and the transfer amount. The receiver's balance increases and the sender's decreases by the transfer amount.
View transfers that the user has sent or received.
Retrieve details of a transfer based on the transfer ID.
Request a transfer of a specific amount of TE bucks from another registered user. The requester selects the requestee from a list of users and the transfer amount. The request has an initial status of "pending."
View pending transfer requests.
Approve or reject a pending transfer request. If approved, the requester's balance increases and the requestee's decreases by the transfer amount. If rejected, no account balance changes occur.

The Tenmo program was built as a capstone project with Austin Smith during a 14 week coding bootcamp at Tech Elevator.
 
 
tyhtythtyjyujuy
