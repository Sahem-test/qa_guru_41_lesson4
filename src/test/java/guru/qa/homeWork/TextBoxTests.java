package guru.qa.homeWork;

import guru.qa.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static guru.qa.testData.TestData.*;

public class TextBoxTests extends TestBase {



    @Test
    void successfulFillFormTest() {
        open("/text-box");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#currentAddress").setValue(currentAddress);
        $("#permanentAddress").setValue(permanentAddress);
        $("#submit").click();

        $("#output #name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
        $("#output #currentAddress").shouldHave(text(currentAddress));
        $("#output #permanentAddress").shouldHave(text(permanentAddress));
    }

    @Test
    void successfulFillFormWithoutAddressTest() {
        open("/text-box");
        $("#userName").setValue(userName);
        $("#userEmail").setValue(userEmail);
        $("#submit").click();

        $("#output #name").shouldHave(text(userName));
        $("#output #email").shouldHave(text(userEmail));
    }

    @Test
    void submitWithMinimalDataTest() {
        open("/text-box");
        $("#userName").setValue(userName);
        $("#submit").click();
        $("#output #name").shouldHave(text(userName));
    }

}
