package guru.qa.homeWork;

import guru.qa.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;
import static guru.qa.testData.TestData.*;

public class PracticeFormTests extends TestBase {

    @Test
    void FillPracticeFormPositiveTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue(userEmail);
        $("#gender-radio-1").click();
        $("#userNumber").setValue(userNumber);
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption(month);
        $(".react-datepicker__year-select").selectOption(year);
        $(".react-datepicker__day--010").click();
        $("#subjectsInput").setValue(subjects).pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#uploadPicture").uploadFromClasspath(uploadPicture);
        $("#currentAddress").setValue(currentAddress);
        $("#state").click();
        $("#stateCity-wrapper").$(byText(state)).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText(city)).click();
        $("#submit").click();

        $("#resultModal")
                .shouldHave(text(userName + " " + lastName))
                .shouldHave(text(userEmail))
                .shouldHave(text("Male"))
                .shouldHave(text(userNumber))
                .shouldHave(text("1976-11-10"))
                .shouldHave(text(subjects))
                .shouldHave(text("Sports"))
                .shouldHave(text(uploadPicture))
                .shouldHave(text(currentAddress))
                .shouldHave(text(state + " " + city));
    }

    @Test
    void minimalRequiredFieldsPositiveTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#gender-radio-1").click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#resultModal")
                .shouldHave(text(userName + " " + lastName))
                .shouldHave(text("Male"))
                .shouldHave(text(userNumber));
    }

    @Test
    void lessMinimalRequiredFieldsNegativeTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }

    @Test
    void lessMinimalSignPhoneNegativeTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#gender-radio-1").click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);

    }

    @Test
    void emailWithoutTopLevelDomainNegativeTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("testmail@");
        $("#gender-radio-1").click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }

    @Test
    void emailNoAtInvalidNegativeTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue(userName);
        $("#lastName").setValue(lastName);
        $("#userEmail").setValue("testmail.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue(userNumber);
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }
}
