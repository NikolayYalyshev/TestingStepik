package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class WorkWithCoursesAfterFilter extends WorkFilterAfterSearchCourses {
    private WebDriverWait wait;

    private By courseTitle = By.xpath("//div[contains(@class, 'catalog__search-results')]//li[@class='course-cards__item']");

    private By courseCards = By.xpath("//li[@class='course-cards__item']");

    private By studentCount = By.xpath(".//span[contains(@data-type, 'learners')]//span[2]");


    public WorkWithCoursesAfterFilter(WebDriver chromeDriver) {
        super(chromeDriver);
        this.wait = new WebDriverWait(chromeDriver, Duration.ofSeconds(10));
    }

    public List<WebElement> selectFiveCourseTitle() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(courseTitle));
        return chromeDriver.findElements(courseTitle).stream().filter(el -> !el.getText().isEmpty()).limit(5).collect(Collectors.toList());
    }

    private double parseStudentCountFromCard(WebElement card) {
        String rawText = card.findElement(studentCount).getText()
                .toUpperCase()
                .replace(",", ".").trim(); // на случай "1,5K"
        // Извлекаем число и множитель (регуляркой убираем всё лишнее)
        double multiplier = 1.0;
        if (rawText.endsWith("K")) {
            multiplier = 1_000.0;
            rawText = rawText.replace("K", "");
        } else if (rawText.endsWith("M")) {
            multiplier = 1_000_000.0;
            rawText = rawText.replace("M", "");
        }
        try {
            return Double.parseDouble(rawText) * multiplier;
        } catch (NumberFormatException e) {
            return 0; // Если число не распарсилось
        }
    }

    private WebElement searchPopularFromFiveCourse(String course){
        wait.until(ExpectedConditions.visibilityOfElementLocated(courseCards));
        List<WebElement> cards = chromeDriver.findElements(courseCards);
        WebElement topCourse = cards.stream()
                .limit(5)
                .filter(card -> card.findElement(courseTitle).getText().toLowerCase().contains(course))
                // 2. Создаем пару: сам блок карточки + его распарсенное число
                .map(card -> Map.entry(card, parseStudentCountFromCard(card)))
                // 3. Ищем максимум
                .max(Comparator.comparingDouble(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElseThrow(() -> new NoSuchElementException("Курс не найден"));
        System.out.println("ИТОГОВЫЙ ВЫБОР: " + topCourse.findElement(courseTitle).getText());
        return topCourse;
    }

    public void clickMostPopularFromFiveCourse(String course) {
       WebElement titleLink = searchPopularFromFiveCourse(course).findElement(courseTitle);

        ((JavascriptExecutor) chromeDriver).executeScript("arguments[0].click();", titleLink);
        chromeDriver.switchTo().window(
                chromeDriver.getWindowHandles().stream().reduce((first, second) -> second).get()
        );
    }


}
