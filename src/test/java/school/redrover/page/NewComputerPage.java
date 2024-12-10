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
    WebElement radioButton = getDriver().findElement(By.className("jenkins-radio__label"));

    By saveBtn = By.id("//*[@id=\"ok\"]");

    public NewComputerPage writeNodeName(String name) {
        getDriver().findElement(inputName).sendKeys(name);
        System.out.println("Вводим название нового узла");
        return this;
    }

    public NewComputerPage radioBtnClick() {

        if (!radioButton.isSelected())
            radioButton.click();
        System.out.println("Нажала радиобаттон");
        return this;
    }

    public CreateNewNodePage clickSaveBtn(){
        getDriver().findElement(saveBtn).click();
        return new CreateNewNodePage(getDriver());
    }

}
