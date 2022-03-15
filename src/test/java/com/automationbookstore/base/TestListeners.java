package com.automationbookstore.base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import java.util.Arrays;


public class TestListeners implements ITestListener {

    private static ExtentReports extent = ExtentManager.createInstance();
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<ExtentTest>();

    public void onTestStart(ITestResult result){
        ExtentTest test = extent.createTest(result.getMethod().getMethodName());
        extentTest.set(test);
    }

    public void onTestSuccess(ITestResult result){
        String logText = "<b>" + result.getMethod().getMethodName() + " Passed<b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.GREEN);
        extentTest.get().log(Status.PASS, m);
    }

    public void onTestSkipped(ITestResult result){
        String logText = "<b>" + result.getMethod().getMethodName() + " Skipped<b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.YELLOW);
        extentTest.get().log(Status.SKIP, m);
    }

    public void onTestFailure(ITestResult result){
        String methodName = result.getMethod().getMethodName();
        String exceptionMessage = Arrays.toString(result.getThrowable().getStackTrace());
        extentTest.get().fail("<details><summary><b><font color=red>" +
                "Exception Occurred, click to see details:" + "</font></b></summary>" +
                exceptionMessage.replaceAll(",", "<br>") + "</details> \n");
        ITestContext context = result.getTestContext();
        WebDriver driver = (WebDriver) context.getAttribute("WebDriver");
        String base64 = takeScreenshotAsBase64(driver);
        try{
            extentTest.get().fail("<b><font color=red>" + "Screenshot of failure" + "</font></b>",
                    MediaEntityBuilder.createScreenCaptureFromBase64String(base64).build());
        }catch (Exception e){
            extentTest.get().fail("Test Failed, cannot attach screenshot");
        }
        String logText ="<b>" + methodName + " Failed</b>";
        Markup m = MarkupHelper.createLabel(logText, ExtentColor.RED);
        extentTest.get().log(Status.FAIL, m);
    }

    public static String takeScreenshotAsBase64(WebDriver driver){
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BASE64);
    }

    public void onFinish(ITestContext context){
        if(extent != null)
            extent.flush();
    }

}
