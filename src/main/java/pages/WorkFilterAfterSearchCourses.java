package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WorkFilterAfterSearchCourses {

    protected WebDriver chromeDriver;

    private WebDriverWait wait;

    private int appliedFilters;

    public WorkFilterAfterSearchCourses(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    private WebElement getType(String typeName){
        if (typeName.equals("Бесплатно")){
            return chromeDriver.findElement(By.xpath("//div[@class='search-form-filter__range-presets']/button[1]"));
        } else if (typeName.equals("от 5000")) {
            return chromeDriver.findElement(By.xpath("//div[@class='search-form-filter__range-presets']/button[2]"));
        }else {
            return chromeDriver.findElement(By.xpath("//div[@class='search-form-filter__range-presets']/button[3]"));
        }
    }

    private WebElement getLanguage(String language){
        if (language.equals("Русский")){
            return chromeDriver.findElement(By.xpath("//label[@data-qa-field='language']//span[contains(., 'Русский')]"));
        } else if (language.equals("Английский")) {
            return chromeDriver.findElement(By.xpath("//label[@data-qa-field='language']//span[contains(., 'Английский')]"));
        }else {
            return chromeDriver.findElement(By.xpath("//label[@data-qa-field='language']//span[contains(., 'Любой')]"));
        }
    }

    public void filteringCourses(String typeName, String language){
        getType(typeName).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class, 'applied-filters-badge')]")));
        appliedFilters = chromeDriver.findElements(By.xpath("//div[contains(@class, 'applied-filters-badge')]")).size();
        getLanguage(language).click();
        wait.until(d->d.findElements(By.xpath("//div[contains(@class, 'applied-filters-badge')]")).size() !=appliedFilters);
    }
}
