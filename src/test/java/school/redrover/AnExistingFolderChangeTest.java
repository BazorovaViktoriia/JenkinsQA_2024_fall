package school.redrover;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.runner.BaseTest;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class AnExistingFolderChangeTest extends BaseTest {

    private void createNewFolder () {
        getDriver().findElement(By.xpath("//*[@id='tasks']/div[1]/span/a")).click();
        getDriver().findElement(By.id("name")).sendKeys("TestFolder");
        getDriver().findElement(By.xpath("//*[@id='j-add-item-type-nested-projects']/ul/li[1]/div[2]/div")).click();
        getDriver().findElement(By.id("ok-button")).click();
        getDriver().findElement(By.name("Submit")).click();

        getDriver().findElement(By.xpath("//*[@id='tasks']/div[6]/span/a")).click();
    }

    @Test
    public void testNoChangesWarning () {
        createNewFolder();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[3]/div")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[3]/div")).getText(),
                "The new name is the same as the current name.");
    }

    @Test
    public void testSavingWithEmptyName () {
        createNewFolder();
        getDriver().findElement(By.name("newName")).clear();

        getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[3]/div")));

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='main-panel']/form/div[1]/div[1]/div[3]/div")).getText(),
                "No name is specified");
    }

    @Test
    public void testRenameFromFoldersPage () {
        createNewFolder();
        getDriver().findElement(By.name("newName")).sendKeys("2");
        getDriver().findElement(By.name("Submit")).click();

        Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='main-panel']/h1")).getText(), "TestFolder2");
    }

    @Test
    public void testNotAllowedSymbols () {
        createNewFolder();

        List<String> setOfSymbols = new ArrayList<>(List.of("$", "%", "#", "&amp;", "[", "]", "@", "!", "^", "/", ":", "*", "?", "|"));

        for (String symbols : setOfSymbols) {

            getDriver().findElement(By.name("newName")).clear();
            getDriver().findElement(By.name("newName")).sendKeys(symbols);
            getDriver().findElement(By.name("Submit")).click();

            getWait5().until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#main-panel > p")));

            Assert.assertEquals(getDriver().findElement(By.xpath("//*[@id='main-panel']/p")).getText(),
                    "‘" + symbols + "’ is an unsafe character");

            getDriver().navigate().back();
        }
    }

}
