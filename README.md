# Module 2 Capstone - TEnmo

This is an online payment service for transferring "TE bucks" between friends.



### Bonus Challenge

If you complete all of the required and challenge use cases and are looking for yet another challenge, create a Command Line Interface (CLI) client application for TEnmo. The file CLI.md contains sample user experiences for inspiration. 

## How to set up the database

Create a new Postgres database called `tenmo`. Run the `database/tenmo.sql` script in pgAdmin to set up the database that you'll begin to work from. You should make structure changes in this script and not the database directly. Additionally, both you and your team members need to run this script each time after making changes to it. 

## Database schema

The following tables are created by the provided `tenmo.sql` script. 

### `tenmo_user` table

Stores the login information for users of the system.

| Field           | Description                                                                    |
| --------------- | ------------------------------------------------------------------------------ |
| `user_id`       | Unique identifier of the user                                                  |
| `username`      | String that identifies the name of the user; used as part of the login process |
| `password_hash` | Hashed version of the user's password                                          |

### `account` table

Stores the accounts of users in the system.

| Field           | Description                                                             |
| --------------- | ----------------------------------------------------------------------- |
| `account_id`    | Unique identifier of the account                                        |
| `user_id`       | Foreign key to the `tenmo_user` table; identifies user who owns account |
| `balance`       | The amount of TE bucks currently in the account                       |

