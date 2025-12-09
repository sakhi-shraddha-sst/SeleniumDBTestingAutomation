package com.framework.reports;


import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import org.testng.*;

public class ExtentTestNGListener implements ISuiteListener, ITestListener {

    private static ThreadLocal<ExtentTest> testThread = new ThreadLocal<>();
    private ExtentReports extent;

    @Override
    public void onStart(ISuite suite) {
        extent = ExtentManager.getReporter();
    }

    @Override
    public void onFinish(ISuite suite) {
        if (extent != null) {
            extent.flush();
        }
    }
    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        ExtentTest test = extent.createTest(testName);
        testThread.set(test);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        testThread.get().pass("Test passed");
    }
    @Override
    public void onTestFailure(ITestResult result) {
        testThread.get().fail(result.getThrowable());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        testThread.get().skip("Test skipped");
    }

    // Not used but required by interface
    @Override public void onTestFailedButWithinSuccessPercentage(ITestResult result) {}
    @Override public void onStart(ITestContext context) {}
    @Override public void onFinish(ITestContext context) {}

    public static ExtentTest getTest() {
        return testThread.get();
    }

}
