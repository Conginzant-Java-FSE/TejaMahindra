package org.example;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.example.dao.DBConnection;
import org.example.ui.MainMenu;

public class Main {
    private static final Logger logger = LogManager.getLogger(Main.class);

    public static void main(String[] args) {
        logger.info("Initializing RevHire...");

        if (DBConnection.getInstance().getConnection() == null) {
            logger.fatal("CRITICAL ERROR: Could not connect to database. Please check configuration.");
            return;
        }

        new MainMenu().show();
    }
}