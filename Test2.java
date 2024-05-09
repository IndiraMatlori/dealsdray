package com.Dealsdray.automation;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

    public class Test2{

        public static void main(String[] args) {
            // Define resolutions for desktop
            Map<String, Dimension> resolutions = new HashMap<>();
            resolutions.put("1920x1080", new Dimension(1920, 1080));
            resolutions.put("1366x768", new Dimension(1366, 768));
            resolutions.put("1536x864", new Dimension(1536, 864));
            //Define resolution for destop
            resolutions.put("360x640", new Dimension(360, 640));
            resolutions.put("414x896", new Dimension(414, 896));
            resolutions.put("375x667", new Dimension(375, 667));

            // Define browsers
            Map<String, WebDriver> drivers = new HashMap<>();
            drivers.put("Chrome", new ChromeDriver());
            drivers.put("Firefox", new FirefoxDriver());
            // drivers.put("Safari", new SafariDriver());

            // Define URL and list of pages from sitemap
            String baseUrl = "https://www.getcalley.com/";
            String[] pages = {"page1", "page2", "page3","page4","page5"};

            // Loop through each browser
            for (String browserName : drivers.keySet()) {
                WebDriver driver = drivers.get(browserName);

                // Loop through resolutions
                for (String resolutionName : resolutions.keySet()) {
                    Dimension resolution = resolutions.get(resolutionName);

                    // Set browser window size
                    driver.manage().window().setSize(resolution);

                    // Create folder for screenshots
                    File browserFolder = new File("screenshots/" + browserName);
                    browserFolder.mkdirs();
                    File resolutionFolder = new File(browserFolder, resolutionName);
                    resolutionFolder.mkdirs();

                    // Loop through pages
                    for (String page : pages) {
                        driver.get(baseUrl + page);

                        // Take screenshot
                        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                        try {
                            String screenshotPath = resolutionFolder.getAbsolutePath() + "/" + page + "_" + resolutionName + ".png";
                            org.apache.commons.io.FileUtils.copyFile(screenshot, new File(screenshotPath));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                // Close browser
                driver.quit();
            }
        }
    }

