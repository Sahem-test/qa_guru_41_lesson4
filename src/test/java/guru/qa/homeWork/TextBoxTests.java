package guru.qa.homeWork;

import guru.qa.TestBase;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class TextBoxTests extends TestBase {



    @Test
    void successfulFillFormTest()  {
        open("/text-box");
        $("#userName").setValue("Ivanov Ivan");
        $("#userEmail").setValue("test@gmail.com");
        $("#currentAddress").setValue("Moscow, st.Plehanova");
        $("#permanentAddress").setValue("Moscow, st.Bratskaya");
        $("#submit").click();

        $("#output #name").shouldHave(text("Ivanov Ivan"));
        $("#output #email").shouldHave(text("test@gmail.com"));
        $("#output #currentAddress").shouldHave(text("Moscow, st.Plehanova"));
        $("#output #permanentAddress").shouldHave(text("Moscow, st.Bratskaya"));
    }

    @Test
    void submitWithMinimalDataTest(){
        open("/text-box");
        $("#userName").setValue("test");
        $("#submit").click();
        $("#output #name").shouldHave(text("test"));
    }




}
