package com.automationbookstore.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtentManager {

    private static ExtentReports extent;

    public static ExtentReports createInstance(){
        String filename = getReportName();
        String directory = System.getProperty("user.dir") + "/reports/";
        new File(directory).mkdirs();
        String path = directory + filename;
        ExtentSparkReporter htmlReporter = new ExtentSparkReporter(path);
        htmlReporter.config().setEncoding("utf-8");
        htmlReporter.config().setDocumentTitle("Automation Reports");
        htmlReporter.config().setReportName("Automation Test Results");
        htmlReporter.config().setTheme(Theme.STANDARD);
        extent = new ExtentReports();
        extent.setSystemInfo("Browser", "Chrome");
        extent.attachReporter(htmlReporter);
        return extent;
    }

    public static String getReportName(){
        Date d = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
        String filename = "report_" + formatter.format(d) + ".html";
        return filename;
    }
}
