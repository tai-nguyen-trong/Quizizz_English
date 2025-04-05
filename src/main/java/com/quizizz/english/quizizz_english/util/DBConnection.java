package com.quizizz.english.quizizz_english.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {
    private static final Logger logger = Logger.getLogger(DBConnection.class.getName());
    private static DataSource dataSource;

    static {
        try {
            initializeDataSource();
        } catch (NamingException e) {
            logger.log(Level.SEVERE, "Failed to initialize DataSource: " + e.getMessage(), e);
            throw new ExceptionInInitializerError("Database initialization failed: " + e.getMessage());
        }
    }

    private static void initializeDataSource() throws NamingException {
        logger.info("Initializing database connection pool");
        Context initContext = new InitialContext();
        Context envContext = (Context) initContext.lookup("java:/comp/env");
        dataSource = (DataSource) envContext.lookup("jdbc/quizizz_english_DB");
        logger.info("Database connection pool initialized successfully");
    }

    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            logger.severe("DataSource is null, attempting to reinitialize");
            try {
                initializeDataSource();
            } catch (NamingException e) {
                logger.log(Level.SEVERE, "Failed to reinitialize DataSource", e);
                throw new SQLException("DataSource initialization failed", e);
            }
        }

        long startTime = System.nanoTime();
        Connection conn = null;

        try {
            conn = dataSource.getConnection();
            long endTime = System.nanoTime();
            long connectionTime = (endTime - startTime) / 1000000;

            if (connectionTime > 500) { // Log warning if connection takes more than 500ms
                logger.warning("Slow connection acquisition: " + connectionTime + " ms");
            } else {
                logger.fine("Connection Pool Time: " + connectionTime + " ms");
            }

            return conn;
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Failed to get database connection: " + e.getMessage(), e);
            throw e;
        }
    }

    public static void closeConnection(Connection conn) {
        if (conn != null) {
            try {
                conn.close();
                logger.fine("Database connection closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error closing database connection", e);
            }
        }
    }

    // Utility method to close connections with transaction handling
    public static void closeConnection(Connection conn, boolean commitTransaction) {
        if (conn != null) {
            try {
                if (commitTransaction) {
                    if (!conn.getAutoCommit()) {
                        conn.commit();
                        logger.fine("Transaction committed successfully");
                    }
                } else {
                    if (!conn.getAutoCommit()) {
                        conn.rollback();
                        logger.fine("Transaction rolled back");
                    }
                }
                conn.close();
                logger.fine("Database connection closed successfully");
            } catch (SQLException e) {
                logger.log(Level.WARNING, "Error during connection close/transaction handling", e);
            }
        }
    }

    // For testing purposes
    public static boolean testConnection() {
        try (Connection conn = getConnection()) {
            return conn != null && !conn.isClosed();
        } catch (SQLException e) {
            logger.log(Level.SEVERE, "Connection test failed", e);
            return false;
        }
    }
}
