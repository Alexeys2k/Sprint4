package pageobjcet;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static org.openqa.selenium.By.className;
import static org.openqa.selenium.By.cssSelector;


public class HomePage {
    //Кнопка Куки
    private By cookie = By.id("rcc-confirm-button");
    //Заголовок главной страницы
    private By homeHeader = className("Home_Header__iJKdX");
    //Button заказа сверху
    private By orderButtonUp = className("Button_Button__ra12g");
    //Button заказа внизу
    private By orderButtonDown = cssSelector(".Button_Middle__1CSJM");
    //Раздел «Вопросы о важном»
    private By importantQuestionsSection = className("accordion");
    //Список ответов к разделу «Вопросы о важном»
    private final String faqQuestions = "accordion__heading-";
    private final String faqResponses = "accordion__panel-";
    private By getFaqQuestions(int number) {return By.id(faqQuestions + number);};
    private By getFaqResponses(int number) {return By.id(faqResponses + number);};


    private WebDriver driver;

    public HomePage(WebDriver driver)
    {
        this.driver = driver;
    }

    //Ожидания загрузки главной страницы
    public HomePage waitForLoadHeader(){
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(homeHeader));

        return this;
    }

    //Нажатие на кнопку "Куки"
    public HomePage clickCookie () {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(cookie));
        driver.findElement(cookie).click();

        return this;
    }

    //Нажатие на кнопку "Заказать"
    public HomePage clickOrderButton (String orderButton){
        switch (orderButton) {
            case "Up":
                driver.findElement(orderButtonUp).click();
                break;
            case "Down":
                WebElement element = driver.findElement(orderButtonDown);
                ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
                driver.findElement(orderButtonDown).click();
                break;
        }
        return new HomePage(driver);
    }

    //Прокрутка страницы к разделу «Вопросы о важном»
    public HomePage  scrollOnTheQuestion () {
        WebElement element = driver.findElement(importantQuestionsSection);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", element);
        return this;
    }
    //Нажатие на вопрос
    public HomePage clickOnTheQuestion(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(getFaqQuestions(number)));
        driver.findElement(getFaqQuestions(number)).click();

        return this;
    }

    //Получить текста ответа
    public String gettingTheResponseText(int number) {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(getFaqResponses(number)));
        return driver.findElement(getFaqResponses(number)).getText();
    }
}
