package Com;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class DriverUtility {
    static WebDriver driver;
    private HashMap<String, String> urlKeys;
    private DriverUtility() throws Exception {
        System.setProperty("webdriver.chrome.driver", "chromedriver");
        Properties prop = new Properties();


         driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);


    }

    boolean performTest(SheetColumnHeader sheetColumnHeader)  {
        try {


            driver.get("https://demoqa.com/automation-practice-form");
            System.out.println("**Launching Chrome Browser**");

            //handle first name field
            driver.findElement(By.cssSelector("input[id='firstName']")).sendKeys(sheetColumnHeader.getFirstname());
            //handle last name field
            driver.findElement(By.cssSelector("input[id='lastName']")).sendKeys(sheetColumnHeader.getLastname());
            //handle number field
            takeScreenshot("Screenshot");
            if (sheetColumnHeader.getGender().equals("male")) {
                driver.findElement(By.xpath("//*[contains(text(),'Male')]")).click();
            } else {
                driver.findElement(By.xpath("//*[contains(text(),'Female')]")).click();
            }

            //handle number field
            driver.findElement(By.cssSelector("input[id='userNumber']")).sendKeys(String.valueOf(sheetColumnHeader.getNumber()));


            Thread.sleep(2000);
            //handle send button
            driver.findElement(By.cssSelector("button[class='btn btn-primary']")).submit();
            //display all details form
            driver.findElement(By.cssSelector("div[id='example-modal-sizes-title-lg']")).isDisplayed();
            Thread.sleep(2000);
        } catch (Exception p) {
            return false;
        }
        return true;


    }


    void shutdownDriver() {

        driver.quit();

        //driver.close();
        System.out.println("I am closing Browser ");
    }


    public static void takeScreenshot( String filename) throws IOException {
        File file = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(file, new File(System.getProperty("user.dir")+"/test-output/Screenshot/" + filename + ".png"));

    }

    public static DriverUtility getInstance() throws Exception {
        return new DriverUtility();
    }





}
