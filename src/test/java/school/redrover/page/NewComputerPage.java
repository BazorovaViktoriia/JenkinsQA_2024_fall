package school.redrover.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import school.redrover.page.base.BasePage;

public class NewComputerPage extends BasePage {

    public NewComputerPage(WebDriver driver) {
        super(driver);
    }

    By inputName = By.id("name");
    WebElement radioButton = getDriver().findElement(By.id("hudson.slaves.DumbSlave"));

    By saveBtn = By.id("//*[@id=\"ok\"]");

    public void writeNodeName(String name) {
        getDriver().findElement(inputName).sendKeys(name);
    }

    public void radioBtnClick() {

        if (!radioButton.isSelected())
            radioButton.click();
    }

    public CreateNewNodePage clickSaveBtn(){
        getDriver().findElement(saveBtn).click();
        return new CreateNewNodePage(getDriver());
    }

}
