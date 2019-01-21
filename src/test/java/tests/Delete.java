package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.TestBases;

import java.util.List;
import java.util.Random;

public class Delete extends TestBases {

   /*       2. Login	to	Web	Orders application	using	“Tester” and	“test”
            3. Delete	a	random entry	from	the	table
            4. Verify	that	table	row	count	decreased by	1
            5. Verify	Name column does	not	contain	deleted	person’s name
   */
   @Test
   public void deleteElement() {
       driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
       driver.findElement(By.id("ctl00_MainContent_username")).sendKeys("Tester");
       driver.findElement(By.id("ctl00_MainContent_password")).sendKeys("test");
       driver.findElement(By.className("button")).click();

       //Delete	a	random entry	from	the	table
       Random random = new Random();
       int randomNumber = random.nextInt(8 +1);
       WebElement rnd = driver.findElement(By.xpath("(//input[@type='checkbox'])["+randomNumber+"]"));
       rnd.click();

       List<WebElement> allRows = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td[1]"));

       driver.findElement(By.className("btnDeleteSelected")).click();

       //Verify	that table	row	count decreased by 1
       List<WebElement> deleteRows = driver.findElements(By.xpath("//table[@class='SampleTable']/tbody/tr/td[1]"));

       Assert.assertFalse(allRows.size()==(deleteRows.size()));
   }
}
