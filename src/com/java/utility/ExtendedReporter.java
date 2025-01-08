package com.java.utility;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ExtendedReporter {

	private WebDriver driver;
	private ExtentReports extent;
	private ExtentTest test;

	public ExtendedReporter(String reportPath) {
		// Initialize ExtentReports
		ExtentSparkReporter sparkReporter = new ExtentSparkReporter(reportPath);
		sparkReporter.config().setDocumentTitle("Automation Report");
		sparkReporter.config().setReportName("Functional Testing");
		sparkReporter.config().setTheme(Theme.STANDARD);
		extent = new ExtentReports();
		extent.attachReporter(sparkReporter);
		String SystemName;
		try {
			SystemName = InetAddress.getLocalHost().getHostName();
			extent.setSystemInfo("Computer name", SystemName);
			extent.setSystemInfo("Environment", "UAT");

			String name = System.getProperty("user.name");
			extent.setSystemInfo("Tester Name", name);
			extent.setSystemInfo("Browser Name", "Chrome");

		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void setDriver(WebDriver driver) {
		this.driver = driver;
	}

	public void startTest(String testName) {

		test = extent.createTest(testName);
		logStep("Test \"" + testName + "\" started.");
	}

	public void logStep(String stepDescription) {
		String screenshotPath = captureScreenshot(stepDescription);
		if (screenshotPath != null) {
			test.info(stepDescription, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			test.info(stepDescription);
		}
	}

	public void logTestSuccess(String testName) {
		String screenshotPath = captureScreenshot(testName);
		if (screenshotPath != null) {
			test.pass(testName, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
		} else {
			test.pass(testName);
		}
		//test.pass("Test \"" + testName + "\" passed successfully.");
	}

	public void logTestFailure(String testName) {
		test.fail("Test \"" + testName + "\" failed.");
	}

	public void logTestSkipped(String testName) {
		test.skip("Test \"" + testName + "\" was skipped.");
	}

	public void flushReports() {
		extent.flush();
		System.out.println("Execution got completed ");
		System.out.println("Report flush ");

	}
	

	private String captureScreenshot(String description) {
		if (driver == null) {
			return null;
		}

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd_HHmmss");
		String timestamp = formatter.format(new Date());
		String fileName = description.replaceAll("[\\/:*?\"<>|]", "_") + "_" + timestamp + ".png";
		String filePath = System.getProperty("user.dir") + "/screenshots/" + fileName;

		File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
		try {
			FileUtils.copyFile(screenshot, new File(filePath));
			return filePath;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
}
