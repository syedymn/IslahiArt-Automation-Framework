package IslahiArt;


import java.io.IOException;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import IslahiArt.util.TestUtil;

public class TestListener implements ITestListener {

    public ExtentSparkReporter htmlReporter;
    public ExtentReports extent;
    public ExtentTest test;

    public void onStart(ITestContext context) {
        // 1. Configure the Report
        htmlReporter = new ExtentSparkReporter(System.getProperty("user.dir") + "/test-output/myReport.html");
        htmlReporter.config().setDocumentTitle("Islahi Art Automation Report");
        htmlReporter.config().setReportName("Functional Testing");
        htmlReporter.config().setTheme(Theme.DARK); // Cool Dark Mode

        extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        extent.setSystemInfo("Tester", "Your Name");
        extent.setSystemInfo("Environment", "Production");
    }

    public void onTestStart(ITestResult result) {
        // 2. Create a new entry in the report
        test = extent.createTest(result.getMethod().getMethodName());
    }

    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test Passed");
    }

    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test Failed: " + result.getThrowable());
        try {
            // 3. Take Screenshot on Failure
            String path = TestUtil.takeScreenshotAtEndOfTest();
            test.addScreenCaptureFromPath(path);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void onFinish(ITestContext context) {
        // 4. Save the Report
        extent.flush();
    }
}
