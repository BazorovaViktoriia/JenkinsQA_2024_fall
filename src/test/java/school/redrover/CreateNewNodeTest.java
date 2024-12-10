package school.redrover;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import school.redrover.page.ComputerPage;
import school.redrover.page.NewComputerPage;
import school.redrover.runner.BaseTest;

public class CreateNewNodeTest extends BaseTest {
    @Test
    public void testCreatingNode(){
        System.out.println("Нажимаем кнопку Настроить Jenkins");
        getDriver().findElement(By.xpath("//*[@id=\"tasks\"]/div[3]/span/a")).click();
        getDriver().findElement(By.xpath("//*[@id=\"main-panel\"]/section[2]/div/div[4]/a/div")).click();
        NewComputerPage page = new ComputerPage(getDriver()).clickNewNodeBtn();
        page.writeNodeName("Новый узел")
                .radioBtnClick()
                .clickSaveBtn();
       // Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/manage/", "URL после нажатия на НАстроить Jenkins правильный");
        Assert.assertEquals(getDriver().getCurrentUrl(), "http://localhost:8080/manage/computer/", "URL после нажатия на создание Node не правильный");

    }
}
