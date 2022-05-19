package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class CartPage extends PageObject {

    private By productNameLabel() {
        return By.xpath("//*[@class=\"v-list-item__title label-name\"]");
    }

    private By priceLabel() {
        return By.xpath("//*[@class=\"label-price\"]");
    }

    private By quantityLabel() {
        return By.id("label-total-quantity");
    }

    private By costLabel() {
        return By.id("label-total-bayar");
    }

    private By addButton() {
        return By.xpath("//*[@class=\"v-list-item__icon\"]//button[2]");
    }

    private By removeButton() {
        return By.xpath("//*[@class=\"v-list-item__icon\"]//button[1]");
    }

    private By payButton() {
        return By.xpath("//*[@class=\"v-btn__content\"][text()=\" Bayar \"]");
    }

    private By iconMessage() {
        return By.xpath("//*[@class=\"v-alert__wrapper\"]");
    }

    @Step
    public boolean productNameLabelAppears() {
        return $(productNameLabel()).isDisplayed();
    }

    @Step
    public boolean productNameLabelTextEqual(String product) {
        return $(productNameLabel()).getText().equals(product);
    }

    @Step
    public boolean quantityLabelAppears() {
        return $(quantityLabel()).isDisplayed();
    }

    @Step
    public boolean costLabelAppears() {
        return $(costLabel()).isDisplayed();
    }

    @Step
    public boolean iconMessageAppears() {
        return $(iconMessage()).isDisplayed();
    }

    @Step
    public boolean iconMessageTextEqual(String message) {
        return $(iconMessage()).getText().equals(message);
    }

    @Step
    public void clickAddButton() {
        $(addButton()).click();
    }

    @Step
    public void clickRemoveButton() {
        $(removeButton()).click();
    }

    @Step
    public void clickPayButton() {
        $(payButton()).click();
    }
}
