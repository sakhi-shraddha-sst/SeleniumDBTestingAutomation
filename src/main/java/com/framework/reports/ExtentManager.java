package com.framework.reports;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

        private static ExtentReports extent;

        public synchronized static ExtentReports getReporter() {
            if (extent == null) {
                String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
                String reportPath = "logs/extent-report-" + timestamp + ".html";

                ExtentSparkReporter spark = new ExtentSparkReporter(reportPath);
                spark.config().setReportName("DB Automation Report");
                spark.config().setDocumentTitle("DB Automation Execution");

                extent = new ExtentReports();
                extent.attachReporter(spark);

                extent.setSystemInfo("Project", "DB Automation Oracle");
                extent.setSystemInfo("Environment", "Local Docker");
                extent.setSystemInfo("DB User", "AUTOMATION");
            }
            return extent;
        }

}
