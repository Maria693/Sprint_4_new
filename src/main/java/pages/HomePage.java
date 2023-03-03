package pages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.junit.Assert.assertTrue;
public class HomePage {
    WebDriver driver;

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    // Locators
    By AccordionItem = By.xpath(".//div[@class='accordion__item']");
    By BlockAboutImportant = By.xpath(".//div[contains(text(), 'Вопросы о важном')]");

    public void sclTillBlockAboutImp(){
        WebElement element = driver.findElement(BlockAboutImportant);
        ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", element);
    }

    public void clickAccordionItem(int index) {
        driver.findElements(AccordionItem).get(index).click();
    }

    public void checkAccordionItemPanelDisplayed(int index) {
        WebElement accordionItemPanel = driver.findElements(AccordionItem).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']"));

        assertTrue(accordionItemPanel.isDisplayed());
    }
    public void checkAccordionItemTextMatchesExpectedText(int index, String expectedText) {
        String accordionItemText = driver.findElements(AccordionItem).get(index)
                .findElement(By.xpath(".//div[@class='accordion__panel']/p"))
                .getText();

        assertTrue(accordionItemText.equals(expectedText));
    }

    // Step
    public void checkAccordionItemOpensAndShowsExpectedText(int index, String expectedText) {
        clickAccordionItem(index);
        checkAccordionItemPanelDisplayed(index);
        checkAccordionItemTextMatchesExpectedText(index, expectedText);
    }
}
