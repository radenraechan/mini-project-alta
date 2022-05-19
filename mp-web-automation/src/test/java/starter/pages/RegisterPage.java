package starter.pages;

import Utils.General;
import net.serenitybdd.core.pages.PageObject;
import net.thucydides.core.annotations.Step;
import org.openqa.selenium.By;

public class RegisterPage extends PageObject {

    General general = new General();
    private String email;

    private By registerHeader() {
        return By.xpath("//*[@class=\"v-card__title\"]");
    }

    private By fullNameField() {
        return By.xpath("//*[label[text()='Nama Lengkap']]/input");
    }

    private By emailField() {
        return By.xpath("//*[label[text()='Email']]/input");
    }

    private By passwordField() {
        return By.xpath("//*[label[text()='Password']]/input");
    }

    private By registerButton() {
        return By.xpath("//*[@class=\"v-btn v-btn--is-elevated v-btn--has-bg theme--light v-size--default primary\"]");
    }

    private By iconWarning() {
        return By.xpath("//*[@class=\"v-alert__wrapper\"]");
    }

    private By textWarning() {
        return By.xpath("//*[@class=\"v-alert__content\"]");
    }

    @Step
    public void openUrl() {
        openAt("/auth/register");
    }

    @Step
    public boolean headerAppears() {
        return $(registerHeader()).isDisplayed();
    }

    @Step
    public boolean headerTextEqual() {
        return $(registerHeader()).getText().equals("Register");
    }

    @Step
    public void inputFullName(String fullName) {
        $(fullNameField()).type(fullName);
    }

    @Step
    public void inputEmail(String email) {
        if (email.equals("new")) {
            this.email = general.randomEmail();
//            try (FileWriter file = new FileWriter("src/test/resources/filejson/email.json")) {
//                file.write(this.email);
//                file.flush();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
        } else if (email.equals("same")) {
            this.email = "email123@gmail.com";
        } else {
            this.email = email;
        }
        $(emailField()).type(this.email);
    }

    @Step
    public void inputPassword(String password) {
        $(passwordField()).type(password);
    }

    @Step
    public void clickRegisterButton() {
        $(registerButton()).click();
    }

    @Step
    public boolean iconWarningAppears(){
        return $(iconWarning()).isDisplayed();
    }

    @Step
    public boolean textWarningEquals(String text) {
        return $(textWarning()).getText().equals(text);
    }
}
