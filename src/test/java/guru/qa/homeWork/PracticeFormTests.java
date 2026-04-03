package guru.qa.homeWork;

import guru.qa.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class PracticeFormTests extends TestBase {

    @Test
    void FillPracticeFormPositiveTest() {
        open("/automation-practice-form");
        executeJavaScript("document.getElementById('fixedban')?.remove();");
        executeJavaScript("document.querySelector('footer')?.remove();");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("test@mail.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("8985426455");
        $("#dateOfBirthInput").click();
        $(".react-datepicker__month-select").selectOption("November");
        $(".react-datepicker__year-select").selectOption("1976");
        $(".react-datepicker__day--010").click();
        $("#subjectsInput").setValue("Maths").pressEnter();
        $("#hobbies-checkbox-1").click();
        $("#uploadPicture").uploadFromClasspath("test.txt");
        $("#currentAddress").setValue("new address");
        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click();
        $("#city").click();
        $("#stateCity-wrapper").$(byText("Delhi")).click();
        $("#submit").click();

        $("#resultModal").shouldHave(text("Ivan Ivanov"));
        $("#resultModal").shouldHave(text("test@mail.com"));
        $("#resultModal").shouldHave(text("Male"));
        $("#resultModal").shouldHave(text("8985426455"));
        $("#resultModal").shouldHave(text("1976-11-10"));
        $("#resultModal").shouldHave(text("Maths"));
        $("#resultModal").shouldHave(text("Sports"));
        $("#resultModal").shouldHave(text("test.txt"));
        $("#resultModal").shouldHave(text("new address"));
        $("#resultModal").shouldHave(text("NCR Delhi"));
    }

    @Test
    void minimalRequiredFieldsPositiveTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("8985426455");
        $("#submit").click();

        $("#resultModal").shouldHave(text("Ivan Ivanov"));
        $("#resultModal").shouldHave(text("Male"));
        $("#resultModal").shouldHave(text("8985426455"));
    }

    @Test
    void lessMinimalRequiredFieldsNegativeTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }

    @Test
    void lessMinimalSignPhoneNegativeTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("89854264");
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }

    @Test
    void emailWithoutTopLevelDomainNegativeTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("testmail@");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("8985426455");
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }

    @Test
    void emailNoAtInvalidNegativeTest() {
        open("/automation-practice-form");
        $("#firstName").setValue("Ivan");
        $("#lastName").setValue("Ivanov");
        $("#userEmail").setValue("testmail.com");
        $("#gender-radio-1").click();
        $("#userNumber").setValue("8985426455");
        $("#submit").click();

        $("#resultModal").shouldNotBe(visible);
    }
}
