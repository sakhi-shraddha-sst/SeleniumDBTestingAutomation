package com.framework.db.testcases;

import com.aventstack.extentreports.ExtentTest;
import com.framework.db.DBUtils;
import com.framework.reports.ExtentTestNGListener;
import org.testng.Assert;
import org.testng.annotations.Test;

public class DatabaseSmokeTest {

    @Test
    public void testInsertAndSelectUser() {
        ExtentTest test = ExtentTestNGListener.getTest();
        test.info("Starting DB insert + select test");

        // 1. Insert a user
        String insertQuery = "INSERT INTO USERS (ID, USERNAME, ROLE) VALUES (1001, 'Jass', 'QA_LEAD')";
        int rows = DBUtils.executeUpdate(insertQuery);
        test.info("Insert rows affected: " + rows);


        // 2. Validate the value from DB
        String selectQuery = "SELECT USERNAME FROM USERS WHERE ID = 1001";
        String username = DBUtils.getSingleValue(selectQuery);
        test.info("Fetched USERNAME from DB: " + username);

        Assert.assertEquals(username, "Jass", "Username did not match!");

        // 3. Optional: print whole table to logs
        DBUtils.printTableData("SELECT * FROM USERS");

        test.pass("DB insert + select test completed successfully");

    }
}