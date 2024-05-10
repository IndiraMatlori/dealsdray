package com.Dealsdray.automation;



import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

    public class Test1uploadfile {
        public static void main(String[] args) throws Exception {
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
            wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[2]/div[2]/button"))));

            WebElement bulkorder=driver.findElement(By.xpath("//*[@id='root']/div/div/div[2]/div/div/div[2]/div[2]/button"));
            bulkorder.click();

              wait.until(ExpectedConditions.presenceOfElementLocated((By.xpath("//*[@id='mui-7']"))));


             WebElement upload=driver.findElement(By.xpath("//*[@id='mui-7']"));
            js.executeScript("arguments[0].click();",upload);
            Thread.sleep(5000);

             Runtime.getRuntime().exec("C:/Users/Indira/Desktop/Autoit/upload.exe");
             Thread.sleep(5000);

            WebElement load= driver.findElement(By.xpath("//div[@id='root']//following::button[2]"));
            js.executeScript("arguments[0].click();",load);
             Thread.sleep(5000);
             driver.findElement(By.xpath("//div[@id='root']//following::button[2]")).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();






        }
    }



