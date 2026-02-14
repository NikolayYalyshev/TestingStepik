package helpers;

import io.qameta.allure.Attachment;
import org.openqa.selenium.*;

public class ScreenshotManager {

    @Attachment(value = "Screenshot: {attachmentName}", type = "image/png")
    public static byte[] makeScreenshot(WebDriver driver, WebElement element) {
        // Скроллим к элементу, чтобы он был в центре
        ((JavascriptExecutor) driver).executeScript(
                "arguments[0].scrollIntoView({block: 'center', inline: 'nearest'});", element
        );
        ((JavascriptExecutor) driver).executeScript("arguments[0].style.border='3px solid red'", element);
        // Возвращаем скриншот для Allure
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
