package framework;

import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.concurrent.TimeUnit;

public class TestListener implements ITestListener {

        public void onTestStart(ITestResult iTestResult) {
            System.out.println((String.format("======================================== STARTING TEST %s ========================================", iTestResult.getName())));
        }

        public void onTestSuccess(ITestResult iTestResult) {
            System.out.println(String.format("======================================== FINISHED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                    getExecutionTime(iTestResult)));
            takeScreenshot(iTestResult);
        }

        public void onTestFailure(ITestResult iTestResult) {
            System.out.println(String.format("======================================== FAILED TEST %s Duration: %ss ========================================", iTestResult.getName(),
                    getExecutionTime(iTestResult)));
            takeScreenshot(iTestResult);
        }

        public void onTestSkipped(ITestResult iTestResult) {
            System.out.println(String.format("======================================== SKIPPING TEST %s ========================================", iTestResult.getName()));
            takeScreenshot(iTestResult);
        }

        @Attachment(value = "Last screen state", type = "image/png")
        private byte[] takeScreenshot(ITestResult iTestResult) {
                    return ((TakesScreenshot)Browser.driver).getScreenshotAs(OutputType.BYTES);
        }

        private long getExecutionTime(ITestResult iTestResult) {
            return TimeUnit.MILLISECONDS.toSeconds(iTestResult.getEndMillis() - iTestResult.getStartMillis());
        }
    }

