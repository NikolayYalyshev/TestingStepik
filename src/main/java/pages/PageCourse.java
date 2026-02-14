package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PageCourse {

    protected WebDriver chromeDriver;

    protected WebDriverWait wait;

    public PageCourse(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    public boolean searchButton() {
            // WebDriverWait при возникновении StaleElementReferenceException
            // просто попробует найти элемент заново в течение таймаута
            return wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//button[contains(., 'Поступить на курс')] | //button[contains(., 'Продолжить')]"))).isDisplayed();


    }
}
