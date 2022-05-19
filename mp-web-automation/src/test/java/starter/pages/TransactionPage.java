package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class TransactionPage extends PageObject {

    private By transactionHeader() {
        return By.xpath("//*[@id=\"app\"]/div/main/div/div/h1");
    }

    private By interactionSign() {
        return By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div/div[2]/div[4]");
    }

    @Step
    public boolean headerAppears() {
        return $(transactionHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual() {
        return $(transactionHeader()).getText().equals("Transactions");
    }

    @Step
    public boolean validateOnTransactionPage() {
        return $(interactionSign()).isDisplayed();
    }
}
