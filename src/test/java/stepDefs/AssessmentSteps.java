package stepDefs;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;

public class AssessmentSteps {
    WebDriver driver = Hooks.driver;
    Actions actions = new Actions(driver);
    JavascriptExecutor js = (JavascriptExecutor) driver;

    @Given("User is on the login screen")
    public void user_is_on_the_login_screen() {
        WebElement loginButton = driver.findElement(By.xpath("//div[@class='header__auth-btn']"));
        assertTrue(loginButton.isDisplayed());
    }
    @When("User clicks on Login option")
    public void user_clicks_on_login_signup_option() {
        driver.findElement(By.xpath("//div[@class='header__auth-btn']")).click();
    }
    @When("User clicks on Login button")
    public void user_clicks_on_login_button(){
        driver.findElement(By.xpath("//span[text()='Login']")).click();
    }
    @When("User enters the email {string}")
    public void user_enters_the_email(String email){
        driver.findElement(By.xpath("//span[text()='Use Email Address']")).click();
        driver.findElement(By.xpath("//input[@type='email']")).sendKeys(email);
    }
    @When("User tap on Next button")
    public void user_tap_on_next_button() {
        driver.findElement(By.xpath("//span[text()='Next']")).click();
    }
    @When("User enters the password {string}")
    public void user_enters_the_password(String password){
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);
    }
    @Then("User Should redirect to Home screen")
    public void user_should_redirect_to_home_screen() throws InterruptedException {

        WebElement element = driver.findElement(By.xpath("//div[@class='ml-1']"));
        assertTrue(element.isDisplayed());
    }

//     Scenario 2 - To verify whether the filter in product details page is working
    @Given("User is on the home page after login")
    public void user_is_on_the_home_page_after_login() {
    }

    @When("User hover on the option categories")
    public void user_hover_on_the_option_categories() throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//dt[text()='Categories']"));
        actions.moveToElement(element).build().perform();
//        Thread.sleep(3000);
    }

    @When("User select the option Fashion")
    public void user_select_the_option_fashion() {
        actions.moveToElement(driver.findElement(By.xpath("//a[@title='Fashion ']"))).build().perform();
    }

    @When("User select the sub category Jeans")
    public void user_select_the_sub_category_jeans() {
        actions.moveToElement(driver.findElement(By.xpath("//a[text()='Jeans']"))).click().build().perform();
    }

    @When("User click on the product named {string}")
    public void then_user_click_on_the_product_named(String product) throws InterruptedException {
        WebElement element = driver.findElement(By.xpath("//h3[text()='"+product+"']"));
        element.click();
//        Thread.sleep(2000);
    }

    @Then("User should be directed to the product details page")
    public void user_should_be_directed_to_the_product_details_page() {
        Set<String> windowIds = driver.getWindowHandles();
        List<String> windowList = new ArrayList(windowIds);
        driver.switchTo().window(windowList.get(1));
    }

    @Then("User clicks on filter option")
    public void user_clicks_on_filter_option() {
        driver.findElement(By.xpath("//span[text()='Filter']")).click();
    }

    @When("User select the availability check box for In Stock Only and click on Done button")
    public void user_select_the_availability_check_box_for_in_stock_only_and_click_on_done_button() throws InterruptedException {
        js.executeScript("window.scrollBy(0,350)", "");
        Thread.sleep(500);
        driver.findElement(By.xpath("//label[@for='in-stock-filter']")).click();
        driver.findElement(By.xpath("//button[text()='Done']")).click();
    }

    @Then("Products should be filtered with In Stock only")
    public void products_should_be_filtered_with_in_stock_only() {
        WebElement filterLabel = driver.findElement(By.xpath("//span[text()='In Stock Only']"));
        assertTrue(filterLabel.isDisplayed());
    }

    @When("User click on sort option and select price High to Low")
    public void user_click_on_sort_option_and_select_price_high_to_low() throws InterruptedException {
        driver.findElement(By.xpath("//span[text()='Sort by:']")).click();
//        Thread.sleep(3000);
        WebElement element1 = driver.findElement(By.xpath("//li[text()='Price High to Low']"));
        element1.click();
        Thread.sleep(500);
    }

    @Then("Products should be listed in the ascending order of price")
    public void products_should_be_listed_in_the_ascending_order_of_price() {
        List<WebElement> priceElements = driver.findElements(By.xpath("//*[@id=\"#offers\"]/div/table/tbody/tr/td[3]"));
        ArrayList<String> prices = new ArrayList<>();
        for(WebElement price: priceElements){
            prices.add((price.getText().substring(1)));
        }
        boolean isSorted = true;
        for(int i=1; i<prices.size(); i++){
            if(prices.get(i-1).compareTo(prices.get(i)) <0){
                isSorted = false;
                break;
            }
        }
        assertTrue(isSorted);
    }

    @When("User scroll down to the page and click on 2nd page button")
    public void user_scroll_down_to_the_page_and_click_on_2nd_page_button() throws InterruptedException {
        js.executeScript("window.scrollBy(0,650)", "");
        Thread.sleep(500);
        WebElement page2 = driver.findElement(By.xpath("//a[text()='2']"));
        page2.click();
        Thread.sleep(500);
    }

    @Then("User should be directed to the second page")
    public void user_should_be_directed_to_the_second_page() throws InterruptedException {
        WebElement page2 = driver.findElement(By.xpath("//a[text()='2']"));
        String classAttribute = page2.getAttribute("class");
        boolean contains = classAttribute.contains("active");
        assertTrue(contains);
    }

}
