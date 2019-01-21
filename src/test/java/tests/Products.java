package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBases;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Products extends TestBases {

    @Test
    public void login() throws InterruptedException {

    driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
    driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
    driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
    driver.findElement(By.className("button")).click();

    //Click on View all productslink
        driver.findElement(By.linkText("View all products")).click();

        Thread.sleep(2000);

    //Remember all the product names from the table
        List<WebElement> allProductNames = driver.findElements(By.xpath("//table[@class='ProductsTable']//tr//td[1]"));
        List <String> allProducts = new ArrayList<>();
        for (int i = 0; i < allProductNames.size(); i++) {
            allProducts.add(allProductNames.get(i).getText());
        }

        //Click on View all orderslink
        driver.findElement(By.linkText("View all orders")).click();
        Thread.sleep(2000);

        //Verify that all the values in the Products column match the names from step 4.

        List<WebElement> tableNames = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td[3]"));
        List <String> allTableProducts = new ArrayList<>();
        for (int i = 0; i < tableNames.size() ; i++) {
            allTableProducts.add(tableNames.get(i).getText());
        }
        Assert.assertTrue(allTableProducts.containsAll(allProducts));
    }
}