package steps;

import helpers.ScreenshotManager;
import io.qameta.allure.Step;
import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import pages.OfficialSiteChooseCourse;
import pages.PageCourse;
import pages.WorkFilterAfterSearchCourses;
import pages.WorkWithCoursesAfterFilter;

import java.util.List;

public class BaseSteps {
    private final WebDriver chromeDriver;
    private final WorkWithCoursesAfterFilter workWithCourse;

    public BaseSteps(WebDriver chromeDriver) {
        this.chromeDriver = chromeDriver;
        this.workWithCourse = new WorkWithCoursesAfterFilter(chromeDriver);
    }

    @Step("Переходим на сайт {url}")
    public void openSite(String url){
        chromeDriver.get(url);
    }

    @Step("Ввести в строку поиска ключевое слово {course}")
    public void enterKeywordSearchbar(String course){
        new OfficialSiteChooseCourse(chromeDriver).searchCourse(course);
    }

    @Step("Применяем фильтр по типу {type} и фильтр по языку {language}")
    public void filteringByParameters(String type, String language){
        new WorkFilterAfterSearchCourses(chromeDriver).filteringCourses(type, language);
    }

    @Step("Проверить что в первых пяти курсах есть слово {course}")
    public void checkFiveCoursesContainWord(SoftAssertions softly, String course){
        List<WebElement> courseTitle = workWithCourse.selectFiveCourseTitle();
        for (WebElement el :
                courseTitle) {
            String title = el.getText();
            if (!title.toLowerCase().contains(course)) {
                ScreenshotManager.makeScreenshot(chromeDriver, el);
            }
            softly.assertThat(title.toLowerCase()).as("Курс \'" + title + "\' не имеет ключевое слово: " + course).contains(course);
        }
    }

    @Step("Кликнуть на курс с самым большим количеством студентов")
    public void clickTopCourse (String course){
        workWithCourse.clickMostPopularFromFiveCourse(course);
    }

    @Step("проверить наличие кнопки")
    public void searchButtonInCourseCard(SoftAssertions softly){
        PageCourse courseCard = new PageCourse(chromeDriver);
        softly.assertThat(courseCard.searchButton()).as("Ни одна из кнопок ('Поступить' или 'Продолжить') не найдена на странице").isTrue();
    }
}
