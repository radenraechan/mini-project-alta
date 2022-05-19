package starter.pages;

import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class DetailProductPage extends PageObject {

    private By detailProductHeader() {
        return By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div/p[1]");
    }

    private By priceLabel() {
        return By.xpath("//*[@id=\"app\"]/div/main/div/div/div/div/p[2]/b");
    }

    @Step
    public boolean headerAppears() {
        return $(detailProductHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual(String product_name) {
        return $(detailProductHeader()).getText().equals(product_name);
    }

    @Step
    public boolean validateOnDetailProductPage() {
        return $(priceLabel()).isDisplayed();
    }
}
