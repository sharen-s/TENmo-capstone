package com.techelevator.tenmo;

import com.techelevator.tenmo.controller.AuthenticationController;
import com.techelevator.tenmo.security.jwt.TokenProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication
public class TenmoApplication {

    private final ConsoleOutput consoleOutput = new ConsoleOutput();

    private final AuthenticationController authenticationController = new AuthenticationController();

    public static void main(String[] args) {
        SpringApplication.run(TenmoApplication.class, args);
    }
    private void run() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleOutput.printMainMenu();
            menuSelection = consoleOutput.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleLogin();
            } else if (menuSelection == 0) {
                continue;
            } else {
                // anything else is not valid
                System.out.println("Invalid Selection");
            }
            consoleOutput.pause();
        }
    }

    private void handleLogin() {
        String username = consoleOutput.promptForString("Username: ");
        String password = consoleOutput.promptForString("Password: ");
        String token = authenticationService.login(username, password);
        if (token != null) {
            hotelService.setAuthToken(token);
        } else {
            consoleOutput.printErrorMessage();
        }
    }
}
