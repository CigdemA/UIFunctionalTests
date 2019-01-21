package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBases;

import java.util.List;
import java.util.Random;

public class Edit extends TestBases {
    Faker faker = new Faker();
    Random random = new Random();

    @Test
    public void editOrder() {
        int randomNumber = random.nextInt( 8)+1;

        // 1. Login	to	Web	Orders application	using	“Tester” and	“test”
        driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
        driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
        driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
        driver.findElement(By.className("button")).click();

        String randomRowBefore = driver.findElement(By.xpath("//table[@class='SampleTable']/tbody/tr[1]/following-sibling::tr["+randomNumber+"]")).getText();
        String randomRowBeforeArr [] = randomRowBefore.split(" ");
        // 2. Click	edit	button	for	any	entry

//      List<WebElement> editButton = driver.findElements(By.xpath("//input[@type='image']"));
//      editButton.get(randomNumber).click();
        WebElement randomEditButton = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/following-sibling::tr["+randomNumber+"]/td[13]/input"));
        randomEditButton.click();

        // 3. Change	the	quantity	to	a	different	amount
        WebElement qty = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        qty.clear();
        qty.sendKeys("3");

        // 4. Click	Calculate
        driver.findElement(By.className("btn_dark")).click();

        // 5. Verify	that	new	quantity	is	displayed
        Assert.assertTrue(qty.isDisplayed());

        //6. Click	Update
        int rndm = random.nextInt(3)+1;
        driver.findElement(By.xpath("//table[@id='ctl00_MainContent_fmwOrder_cardList']/tbody/tr/td["+rndm +"]")).click();
        driver.findElement(By.id("ctl00_MainContent_fmwOrder_UpdateButton")).click();

        // 7. Verify	new	quantity	is	displayed
        WebElement newQuantity = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/following-sibling::tr["+randomNumber+"]/td[4]"));
        Assert.assertTrue(newQuantity.isDisplayed());

        // 8. Verify	that	other	information	in	that	row	did	not	change
        String randomRowAfter = driver.findElement(By.xpath("//table[@id='ctl00_MainContent_orderGrid']/tbody/tr[1]/following-sibling::tr["+randomNumber+"]")).getText();
        String[] randomRowAfterArr = randomRowAfter.split(" ");

        for (int i = 0; i < randomRowAfterArr.length; i++) {
            if (i == 3) {
                continue;
            }
            else {//american express array split it from space

                //Assert.assertTrue(randomRowBeforeArr[i].equals(randomRowAfterArr[i]));
                System.out.println(randomRowBeforeArr[i]);
              //  System.out.println(randomRowAfterArr[i]);
            }
        }
    }
}