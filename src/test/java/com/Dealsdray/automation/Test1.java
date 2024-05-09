package com.Dealsdray.automation;



import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.time.Duration;

    public class Test1 {
        public static void main(String[] args) throws Exception{
            ChromeOptions options = new ChromeOptions();
            options.setBrowserVersion("122");
            options.addArguments("--remote-allow-origins=*");

            WebDriver driver = new ChromeDriver(options);
            // Navigate to the website
            driver.get("https://demo.dealsdray.com/");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            // Define the path to the XLSX file
            String xlsxFilePath = "C:\\Users\\Indira\\Downloads\\demo-data (1).xlsx";

            //  login with valid credentials
            driver.findElement(By.name("username")).sendKeys("prexo.mis@dealsdray.com");
            driver.findElement(By.name("password")).sendKeys("prexo.mis@dealsdray.com");
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='Login']"))).click();
            driver.manage().window().maximize();
            // Wait for the page to get load
            // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//span[text()='chevron_right']")));

            // Navigate to the orders section
            driver.findElement(By.xpath("//span[text()='chevron_right']")).click();
            wait.until((ExpectedConditions.elementToBeClickable(By.xpath("//div[@class='expansion-panel submenu']/descendant::span[1]")))).click();

            // Wait for the page to load and then click on the add bulk order button
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div[@id='root']/descendant::button[2]")));
            JavascriptExecutor js=(JavascriptExecutor)driver;
            //and then click on the add bulk order button
            Thread.sleep(5000);

            WebElement bulkorder=driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[2]/div[2]/button"));
            bulkorder.click();
            //js.executeScript("arguments[0].click();",bulkorder);



            WebElement uploadButton = driver.findElement(By.id("upload-button"));
            uploadButton.sendKeys(new File(xlsxFilePath).getAbsolutePath());

            // Wait for the upload to complete
            wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.id("upload-status")), "Completed"));

            // Take a screenshot of the final output page
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshotFile, new File("xlsxFilePath"));

            // Close the WebDriver
            driver.quit();
        }
    }



