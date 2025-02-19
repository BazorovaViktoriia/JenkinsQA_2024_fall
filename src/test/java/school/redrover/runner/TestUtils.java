package school.redrover.runner;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;

public class TestUtils {

    public static class ExpectedConditions {
        public static ExpectedCondition<WebElement> elementIsNotMoving(final By locator) {
            return new ExpectedCondition<>() {
                private Point location = null;

                @Override
                public WebElement apply(WebDriver driver) {
                    WebElement element;
                    try {
                        element = driver.findElement(locator);
                    } catch (NoSuchElementException e) {
                        return null;
                    }

                    if (element.isDisplayed()) {
                        Point location = element.getLocation();
                        if (location.equals(this.location)) {
                            return element;
                        }
                        this.location = location;
                    }

                    return null;
                }
            };
        }

        public static ExpectedCondition<WebElement> elementIsNotMoving(final WebElement element) {
            return new ExpectedCondition<>() {
                private Point location = null;

                @Override
                public WebElement apply(WebDriver driver) {
                    if (element.isDisplayed()) {
                        Point location = element.getLocation();
                        if (location.equals(this.location)) {
                            return element;
                        }
                        this.location = location;
                    }

                    return null;
                }
            };
        }
    }

    public static void moveAndClickWithJavaScript(WebDriver driver, WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('mouseenter'));", element);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('click'));", element);
    }

    public static void scrollToBottom(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
    }

    public static void createPipeline(BaseTest baseTest, String name) {
        baseTest.getDriver().findElement(By.xpath("//a[@href='/view/all/newJob']")).click();

        baseTest.getDriver().findElement(By.xpath("//input[@name='name']")).sendKeys(name);
        baseTest.getDriver().findElement(By.xpath("//li[@class='org_jenkinsci_plugins_workflow_job_WorkflowJob']")).click();
        baseTest.getDriver().findElement(By.xpath("//button[@type='submit']")).click();

        baseTest.getDriver().findElement(By.xpath("//button[@name='Submit']")).click();
        baseTest.getDriver().findElement(By.id("jenkins-home-link")).click();
    }

    public static void newItemsData(BaseTest baseTest, String itemName, String itemXpath) {
        baseTest.getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        baseTest.getDriver().findElement(By.id("name")).sendKeys(itemName);
        baseTest.getDriver().findElement(By.xpath(itemXpath)).click();
        baseTest.getDriver().findElement(By.id("ok-button")).click();
    }

    public static void pasteTextWithJavaScript(WebDriver driver, WebElement element, String text) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].value = arguments[1];", element, text);
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].dispatchEvent(new Event('input', { bubbles: true }));", element);
    }

    public static String readFile(String fileName) {
        try (FileInputStream fileInputStream = new FileInputStream(Paths.get("test_data", fileName).toString())) {
            return new String(fileInputStream.readAllBytes(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            ProjectUtils.log("File not found");
            return null;
        }
    }


}
