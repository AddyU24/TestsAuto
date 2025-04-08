package RegressionPagesTestCase;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentClassReport implements ITestListener {

    private ExtentSparkReporter sparkReporter;
    private ExtentReports extent;
    private ExtentTest parentTest;
    private ExtentTest test; // ❌ plus besoin de ThreadLocal ici

    @Override
    public void onStart(ITestContext context) {
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String reportPath = System.getProperty("user.dir") + "/reports/ExtentReport_" + timestamp + ".html";

        sparkReporter = new ExtentSparkReporter(reportPath);
        try {
            sparkReporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        extent = new ExtentReports();
        extent.attachReporter(sparkReporter);

        extent.setSystemInfo("Computer Name", "localhost");
        extent.setSystemInfo("Environment", "QA");
        extent.setSystemInfo("Testeurs", "Addy et Magali");
        extent.setSystemInfo("OS", "Windows 11");
        extent.setSystemInfo("Navigateur", "Chrome");

        parentTest = extent.createTest(context.getName()); // Classe de test comme parent
    }

    @Override
    public void onTestStart(ITestResult result) {
        test = parentTest.createNode(result.getMethod().getMethodName());
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        test.log(Status.PASS, "Test PASSED : " + result.getName());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        test.log(Status.FAIL, "Test FAILED : " + result.getName());
        test.log(Status.FAIL, "Cause : " + result.getThrowable());

        // Capture d'écran
        String screenshotPath = captureScreenshot(result.getName());
        if (screenshotPath != null) {
            test.addScreenCaptureFromPath(screenshotPath);
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        test.log(Status.SKIP, "Test SKIPPED : " + result.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        extent.flush();
    }

    // Capture de screenshot
    public String captureScreenshot(String testName) {
        try {
            File src = ((TakesScreenshot) BaseClassTest.getDriver()).getScreenshotAs(OutputType.FILE);
            String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
            String screenshotName = testName + "_" + timestamp + ".png";
            String screenshotPath = System.getProperty("user.dir") + "/screenshots/" + screenshotName;
            File dest = new File(screenshotPath);
            FileUtils.copyFile(src, dest);
            return screenshotPath;
        } catch (Exception e) {
            System.out.println("Erreur lors de la capture d'écran : " + e.getMessage());
            return null;
        }
    }
}
