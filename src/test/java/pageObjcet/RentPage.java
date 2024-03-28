package pageObjcet;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RentPage {

    //Заголовок "Про аренду"
    private By aboutRent = By.className("Order_Header__BZXOb");
    //Поле когда привезти самокат
    private By dateDelivery  = By.xpath(".//input[@placeholder = '* Когда привезти самокат']");
    //Поле срок аренды
    private By rentalPeriod = By.className("Dropdown-placeholder");
    //Чекбокс цвет самоката черный
    private By blackColor = By.id("black");
    //Чекбокс цвет самоката серый
    private By grayColor = By.id("grey");
    //Поле Комментарий
    private By comment = By.xpath(".//input[@placeholder = 'Комментарий для курьера']");
    //Кнопка Заказать
    private By orderButton = By.xpath(".//div[@class = 'Order_Buttons__1xGrp']/button[text()='Заказать']");
    //Окно "Хотите оформить заказ?"
    private By orderModal = By.className("Order_Modal__YZ-d3");
    //Кнопка Да в окне оформления заказа
    private By yesOrderWindow = By.xpath(".//*[text() = 'Да']");
    //Окно "Заказ оформлен"
    private  By orderCreated = By.xpath(".//*[text() = 'Заказ оформлен']");

    private final WebDriver driver;


    public RentPage(WebDriver driver) {
        this.driver = driver;
    }

    //Метод загрузки страницы "Для кого самокат"
    public RentPage  waitForLoadRentPage() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(driver1 -> (driver.findElement(aboutRent).getText() !=null && !driver.findElement(aboutRent).getText().isEmpty()));

        return this;
    }

    //Метод заполнения поля "Когда привезти самокат"
    public RentPage setDateDelivery(String newDateDelivery) {
        driver.findElement(dateDelivery).sendKeys(newDateDelivery);
        driver.findElement(dateDelivery).sendKeys(Keys.ENTER);

        return this;
    }

    //Метод заполнения поля "Срок аренды"
    public RentPage setRentalPeriod(String newRentalPeriod) {
        driver.findElement(rentalPeriod).click();
        driver.findElement(By.xpath("//*[text() = '"+ newRentalPeriod + "']")).click();

        return this;
    }

    //Метод заполняния поля "Цвет самоката"
    public RentPage setColorScooter(String colorScooter) {
        driver.findElement(By.id(colorScooter)).click();

        return this;
    }

    //Метод заполнения поля "Комментарий"
    public RentPage setComment(String newComment) {
        driver.findElement(comment).sendKeys(newComment);
        return this;
    }

    //Метод нажатия на кнопку "Далее"
    public RentPage clickOrderButton() {
        driver.findElement(orderButton).click();

        return this;
    }

    //Метод нажатия на кнопку "Да" в окне "Хотите оформить заказ?"
    public RentPage waitOrderModal() {
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.presenceOfElementLocated(orderModal));
        driver.findElement(yesOrderWindow).click();

        return this;
    }

    //Метод проверки создания заказа
    public boolean isOrderCreated() {
        return driver.findElement(orderCreated).isDisplayed();
    }

}