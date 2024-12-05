package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import school.redrover.page.base.BasePage;

public class ComputerPage extends BasePage {

    public ComputerPage(WebDriver driver) {
        super(driver);
    }

    By newNodeBtn = By.xpath("//*[@id=\"main-panel\"]/div[1]/div[2]/a[1]");

    public NewComputerPage clickNewNodeBtn(){
        getDriver().findElement(newNodeBtn).click();

        return new NewComputerPage(getDriver());
    }
}
