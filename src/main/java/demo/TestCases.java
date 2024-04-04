package demo;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    //EdgeDriver driver;
    ChromeDriver driver;
    WebDriverWait wait;

    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        // WebDriverManager.edgedriver().timeout(30).setup();
        // EdgeDriver options = new EdgeDriver();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);
        //driver = new EdgeDriver();

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        driver.get("https://www.makemytrip.com/");
        wait = new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    public void captchaRemove(){
        // Delete all cookies
        driver.manage().deleteAllCookies();
        // Refresh the page to clear settings
        driver.navigate().refresh();
    }
    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        boolean url = driver.getCurrentUrl().contains("makemytrip");
        System.out.println(driver.getCurrentUrl());
        if(url){
            System.out.println("The URL of the Make My Trip homepage contains \"makemytrip\"");
        }else{
            System.out.println("testcase01: Failed URL is not matching");
        }
        System.out.println("end Test case: testCase01");
    
    }

    public void testcase02() throws InterruptedException{
        System.out.println("\nTest Case 02: Get Flight Details from Bangalore to New Delhi");
        //Thread.sleep(7000);
       
        WebElement flightLink = driver.findElement(By.xpath("//span[text()='Flights']"));
        wait.until(ExpectedConditions.visibilityOf(flightLink)).click();
        
        WebElement departureLocation = driver.findElement(By.xpath("//input[@id='fromCity']"));
        wait.until(ExpectedConditions.visibilityOf(departureLocation)).sendKeys("blr");;
        
        WebElement bangaloreOption = driver.findElement(By.xpath("//span[text()='Bengaluru']"));
        wait.until(ExpectedConditions.visibilityOf(bangaloreOption)).click();
        
        WebElement arrivalLocation = driver.findElement(By.xpath("//input[@id='toCity']"));
        wait.until(ExpectedConditions.visibilityOf(arrivalLocation)).sendKeys("del");
        
        WebElement delhiOption = driver.findElement(By.xpath("//span[text()='New Delhi']"));
        wait.until(ExpectedConditions.visibilityOf(delhiOption)).click();
        
        WebElement departure = driver.findElement(By.xpath("//span[text()='Departure']"));
        wait.until(ExpectedConditions.visibilityOf(departure)).click();
         
        WebElement departuredate = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        wait.until(ExpectedConditions.visibilityOf(departuredate)).click();
        
        /*WebElement returning = driver.findElement(By.xpath("//span[text()='Return']"));
        wait.until(ExpectedConditions.visibilityOf(returning)).click();
        WebElement returndate = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        wait.until(ExpectedConditions.visibilityOf(returndate)).click();*/
        
        try {
            WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
        wait.until(ExpectedConditions.visibilityOf(searchButton)).click();
        //captchaRemove();
       
        WebElement flightPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("(//div[@class='blackText fontSize18 blackFont white-space-no-wrap clusterViewPrice'])[1]")));
        String pricePerAdult = flightPrice.getText();
        System.out.println("Price per adult: " + pricePerAdult);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }
        
    }

    public void testcase03() throws InterruptedException{
        System.out.println("\nTest Case 03: Get Train Details from Bangalore to New Delhi");
        // Thread.sleep(7000);

        WebElement flightLink = driver.findElement(By.xpath("//span[text()='Flights']"));
        wait.until(ExpectedConditions.visibilityOf(flightLink)).click();

        WebElement trainlink = driver.findElement(By.xpath("//span[text()='Trains']"));
        wait.until(ExpectedConditions.visibilityOf(trainlink)).click();
        
        WebElement from = driver.findElement(By.id("fromCity"));
        wait.until(ExpectedConditions.visibilityOf(from)).click();
        
        WebElement fromclick = driver.findElement(By.cssSelector("input[placeholder='From']"));
        wait.until(ExpectedConditions.visibilityOf(fromclick)).sendKeys("ypr");

        WebElement bangaloreOption = driver.findElement(By.xpath("//span[text()='YPR']"));
        wait.until(ExpectedConditions.visibilityOf(bangaloreOption)).click();
     
        WebElement toclick = driver.findElement(By.cssSelector("input[placeholder='To']"));
        wait.until(ExpectedConditions.visibilityOf(toclick)).sendKeys("ndls");
        
        WebElement delhiOption = driver.findElement(By.xpath("//span[text()='NDLS']"));
        wait.until(ExpectedConditions.visibilityOf(delhiOption)).click();
        
        WebElement date = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        date.click();

        WebElement options =  driver.findElement(By.cssSelector("li[data-cy='3A']"));
        wait.until(ExpectedConditions.visibilityOf(options)).click();
        
        WebElement searchButton = driver.findElement(By.xpath("//a[text()='Search']"));
        wait.until(ExpectedConditions.visibilityOf(searchButton)).click();

        WebElement flightPrice = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//div[@class='ticket-price justify-flex-end'])[1]")));
        String pricePerAdult = flightPrice.getText();
        System.out.println("Price per adult: " + pricePerAdult);
        System.out.println("The correct train details are obtained");
    }


    public void testcase04() throws InterruptedException{
        System.out.println("\nTest Case 04: Get Bus Details from Bangalore to Ranchi");
        //Thread.sleep(7000);

        WebElement flightLink = driver.findElement(By.xpath("//span[text()='Flights']"));
        wait.until(ExpectedConditions.visibilityOf(flightLink)).click();

        WebElement buslink = driver.findElement(By.xpath("//span[text()='Buses']"));//li[@data-cy='menu_Buses']//a)
        wait.until(ExpectedConditions.visibilityOf(buslink)).click();
        
        WebElement from = driver.findElement(By.id("fromCity"));
        wait.until(ExpectedConditions.visibilityOf(from)).click();
        
        WebElement fromtextbox = driver.findElement(By.id("fromCity"));
        wait.until(ExpectedConditions.visibilityOf(fromtextbox)).sendKeys("bangl");
        
        WebElement Bangalore = driver.findElement(By.xpath("//span[text()='Bangalore, Karnataka']"));
        wait.until(ExpectedConditions.visibilityOf(Bangalore)).click();
        
        WebElement to = driver.findElement(By.xpath("//input[@placeholder='To']"));
        wait.until(ExpectedConditions.visibilityOf(to)).sendKeys("ran");
        
        WebElement ranchi = driver.findElement(By.xpath("//span[text()='Ranchi, Jharkhand']"));
        wait.until(ExpectedConditions.visibilityOf(ranchi)).click();
        
        WebElement date = driver.findElement(By.xpath("//div[@aria-label='Mon Apr 29 2024']"));
        wait.until(ExpectedConditions.visibilityOf(date)).click();
        
        WebElement searchButton = driver.findElement(By.xpath("//button[text()='Search']"));
        wait.until(ExpectedConditions.visibilityOf(searchButton)).click();
        
        WebElement message = driver.findElement(By.className("error-title"));
        if(message.getText().contains("No buses found for 29 Apr")){
            System.out.println("The message \"No buses found\" is displayed for the specified route and date.");
        }


    }


}
