package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import sun.awt.windows.WEmbeddedFrame;
import utilities.TestBases;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CreateOrder extends TestBases {
/*
1.Login to Web Orders application using “Tester”and “test””
2.Click on Order link
3.Select a product (Select different product every time)
4.Enter data to all the required fields(Enter different data every time)
5.Click Proceed
6.Click on link View all orders
7.Verify that all the order details are correct
 */
@Test
public void createOrder() throws InterruptedException {
    Faker faker = new Faker();
    driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
    driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
    driver.findElement(By.className("button")).click();

    driver.findElement(By.linkText("Order")).click();

    //Select a product (Select different product every time)
    WebElement selectElement = driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct"));
    Select product = new Select(selectElement);
    Random random = new Random();
    int productNames = random.nextInt(3);
    product.selectByIndex(productNames);

    //Enter data to all the required fields(Enter different data every time)
    //If zero what can I do?
    //What should I different ?

    int qty = random.nextInt(100);
    WebElement quantity = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
    String quant = quantity.getText();
    quantity.sendKeys(String.valueOf(qty));

    WebElement customerName = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtName"));
    String avb= customerName.getText();
    customerName.sendKeys(faker.name().firstName());

    WebElement street = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox2"));
    String strt = street.getText();
    street.sendKeys(faker.address().streetAddress());

    WebElement city = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox3"));
    String cty = city.getText();
    city.sendKeys(faker.address().city());

    WebElement zipcode = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox5"));
    String zCode = zipcode.getText();
    zipcode.sendKeys(faker.address().zipCode().substring(0,5));

    List <WebElement> selectCard = driver.findElements(By.xpath("//table[@class='RadioList']/tbody/tr/td"));

        for (int i = 0; i < selectCard.size(); i++) {
                selectCard.get(i).click();
        }

        WebElement cardNmb = driver.findElement(By.id("ctl00_MainContent_fmwOrder_TextBox6"));
        String cNum = cardNmb.getText();
        cardNmb.sendKeys(faker.finance().creditCard().replace("-", ""));

        WebElement day = driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1"));
        String dayMonth = day.getText();
        day.sendKeys("12/11");

        driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();

        List <String> list = new ArrayList<>();
        list.add(avb);
        list.add(cty);
        list.add(strt);
        list.add(cNum);
        list.add(dayMonth);
        list.add(zCode);
        list.add(quant);

        List <WebElement> tableList = driver.findElements(By.xpath("//table[@class='SampleTable']//tbody/tr[2]/td"));
    for (int i = 0; i < tableList.size(); i++) {
        Assert.assertTrue( tableList.get(i).getText().contains(list.get(i)));
    }
}
}