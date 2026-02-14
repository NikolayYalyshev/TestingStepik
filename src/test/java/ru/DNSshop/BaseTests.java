package ru.DNSshop;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static helpers.Properties.testsProperties;
public class BaseTests {

    protected WebDriver chromeDriver;

    @BeforeEach
    public void openChromeDriver(){
        System.setProperty("webdriver.chrome.driver", testsProperties.endpointChromeDriver());

        chromeDriver = new ChromeDriver();
        chromeDriver.manage().window().maximize();
        chromeDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

    }


    @AfterEach
    public void closeTest(){
        chromeDriver.quit();
    }

}
