import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import pageobjcet.HomePage;
import pageobjcet.OrderPage;
import pageobjcet.RentPage;

import static org.junit.Assert.assertTrue;
import static pageobjcet.BASE_URL.BASE_URL;

@RunWith(Parameterized.class)
public class ScooterOrderTest {
    private DriverRule driverRule = new DriverRule();
    private final String orderButton;
    private final String newName;
    private final String newSurname;
    private final String newAddress;
    private final int stateNumber;
    private final String newTelephone;
    private final String newDateDelivery;
    private final String newRentalPeriod;
    private final String colorScooter;
    private final String newComment;

    public ScooterOrderTest(String orderButton, String newName, String newSurname, String newAddress, int stateNumber, String newTelephone, String newDateDelivery, String newRentalPeriod, String colorScooter, String newComment) {
        this.orderButton = orderButton;
        this.newName = newName;
        this.newSurname = newSurname;
        this.newAddress = newAddress;
        this.stateNumber = stateNumber;
        this.newTelephone = newTelephone;
        this.newDateDelivery = newDateDelivery;
        this.newRentalPeriod = newRentalPeriod;
        this.colorScooter = colorScooter;
        this.newComment = newComment;
    }

    @Rule
    public DriverRule driver = new DriverRule();

    @Parameterized.Parameters
    public static Object [][] getData(){
        return new Object[][] {
                {"Up", "Алексей", "Алекссев", "Алекссевич, 2", 64, "89666666666", "23.03.2024", "двое суток", "black", "Привет"},
                {"Down", "Петр", "Петров", "Петровка, 38", 156, "89222222222", "23.03.2024", "трое суток", "grey", "Пока"}
        };
    }


    @Test
    public void order (){
        WebDriver driver = driverRule.getDriver();
        driver.get(BASE_URL);

        new HomePage(driver)
                .clickCookie()
                .waitForLoadHeader()
                .clickOrderButton(orderButton);
        new OrderPage(driver)
                .waitForLoadOrderPage()
                .setName(newName)
                .setSurname(newSurname)
                .setAddress(newAddress)
                .setMetroStation(stateNumber)
                .setTelephone(newTelephone)
                .clickButtonNext();
        Boolean actual = new RentPage(driver)
                .waitForLoadRentPage()
                .setDateDelivery(newDateDelivery)
                .setRentalPeriod(newRentalPeriod)
                .setColorScooter(colorScooter)
                .setComment(newComment)
                .clickOrderButton()
                .waitOrderModal()
                .isOrderCreated();

        assertTrue("Заказ не оформлен", actual);

    }


}