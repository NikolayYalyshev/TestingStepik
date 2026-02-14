package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class OfficialSiteChooseCourse {

  protected WebDriver chromeDriver;

  private WebDriverWait wait;

  private WebElement fieldSearch;


    public OfficialSiteChooseCourse(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
        this.fieldSearch = chromeDriver.findElement(By.xpath("//input[@aria-label='Search' and @placeholder='Поиск…']"));
    }

    public void searchCourse(String course){
        fieldSearch.click();
        fieldSearch.sendKeys(course);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//ul[contains(@class, 'menu menu_theme_popup')]")));
        fieldSearch.sendKeys(Keys.ENTER);
    }



}
