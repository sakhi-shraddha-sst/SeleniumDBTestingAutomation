package com.framework.db;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class  DBUtils {

    private static final Logger log = LogManager.getLogger(DBUtils.class);

    // SELECT single value
    public static String getSingleValue(String query) {
        log.info("Executing SELECT (single value): {}", query);
            try (Connection conn = DBConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            if (rs.next()) {
                String value = rs.getString(1);
                log.info("Query Result: " + value);
                return value;
            } else {
                log.warn("No rows returned for query: {}", query);
                return null;
            }

        } catch (Exception ex) {
            log.error("Error executing SELECT query", ex);
        }
        return null;
    }

    // SELECT multiple rows
    public static void printTableData(String query) {
        log.info("Executing SELECT (table dump): {}", query);
        try (Connection conn = DBConnectionManager.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            ResultSetMetaData meta = rs.getMetaData();
            int colCount = meta.getColumnCount();

            while (rs.next()) {
                StringBuilder row = new StringBuilder();
                for (int i = 1; i <= colCount; i++) {
                    row.append(meta.getColumnName(i))
                            .append("=")
                            .append(rs.getString(i))
                            .append(" ");
                }
                log.info("Row: {}",row);
            }

        } catch (Exception ex) {
            log.error("Error  printing table data ", ex);
        }
    }

    // INSERT or UPDATE
    public static int executeUpdate(String query) {
        log.info("Executing DML: " + query);
        try (Connection conn = DBConnectionManager.getConnection();
             Statement stmt = conn.createStatement()) {
            int result = stmt.executeUpdate(query);
            log.info("Rows affected: " + result);
            return result;
        } catch (Exception ex) {
            log.error("Error executing DML query", ex);
        }
        return 0;
    }
}
