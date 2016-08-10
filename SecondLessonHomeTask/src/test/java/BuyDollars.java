/**
 * Created by Shelty on 10.08.2016.
 */


import java.util.regex.Pattern;
import java.util.concurrent.TimeUnit;
import org.testng.annotations.*;
import static org.testng.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;

    public class BuyDollars {
        private WebDriver driver;
        private String baseUrl;
        private boolean acceptNextAlert = true;
        private StringBuffer verificationErrors = new StringBuffer();



      @BeforeClass(alwaysRun = true)
        public void setUp() throws Exception {
            driver = new FirefoxDriver();
            baseUrl = "http://finance.i.ua/";
            driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
        }

        @Test
        public void testBuyDollars() throws Exception {
            driver.get(baseUrl + "/");
            Float Sum = Float.valueOf("2000");
            assertTrue(isElementPresent(By.xpath("//span[@onclick='fn_changeSell(this)']")));
            driver.findElement(By.xpath("//span[@onclick='fn_changeSell(this)']")).click();
            assertTrue(isElementPresent(By.id("fn_s1")));
            driver.findElement(By.id("fn_s1")).clear();
            driver.findElement(By.id("fn_s1")).sendKeys("2000");
            assertTrue(isElementPresent(By.id("fn_c1")));
            new Select(driver.findElement(By.id("fn_c1"))).selectByVisibleText("USD");
            assertTrue(isElementPresent(By.id("fn_bank")));
            new Select(driver.findElement(By.id("fn_bank"))).selectByVisibleText("НБУ");
            assertTrue(isElementPresent(By.id("fn_i1_1")));
            Float Price = Float.valueOf(driver.findElement(By.id("fn_i1_1")).getAttribute("value"));
            // ERROR: Caught exception [ERROR: Unsupported command [getEval | storedVars['Sum']*storedVars['Price'] | ]]
            assertTrue(isElementPresent(By.id("fn_o1_1")));
            //Float calculated = Float.valueOf(driver.findElement(By.id("fn_o1_1")).getAttribute("value"));
            //Float result = calculated-Sum*Price;
            //System.out.println(result);
            try {
                assertEquals(driver.findElement(By.id("fn_o1_1")).getAttribute("value"), "49 636,53");
            } catch (Error e) {
                verificationErrors.append(e.toString());
            }
        }

        @AfterClass(alwaysRun = true)
        public void tearDown() throws Exception {
            driver.quit();
            String verificationErrorString = verificationErrors.toString();
            if (!"".equals(verificationErrorString)) {
                fail(verificationErrorString);
            }
        }

        private boolean isElementPresent(By by) {
            try {
                driver.findElement(by);
                return true;
            } catch (NoSuchElementException e) {
                return false;
            }
        }

        private boolean isAlertPresent() {
            try {
                driver.switchTo().alert();
                return true;
            } catch (NoAlertPresentException e) {
                return false;
            }
        }

        private String closeAlertAndGetItsText() {
            try {
                Alert alert = driver.switchTo().alert();
                String alertText = alert.getText();
                if (acceptNextAlert) {
                    alert.accept();
                } else {
                    alert.dismiss();
                }
                return alertText;
            } finally {
                acceptNextAlert = true;
            }
        }
    }


