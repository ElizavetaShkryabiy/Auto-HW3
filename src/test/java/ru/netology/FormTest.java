package ru.netology;

import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.appear;
import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;


public class FormTest {

    @Test
    public void shouldTestHappyPath() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий Пупкин");
        form.$("[data-test-id=phone] input").setValue("+79271112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $("[data-test-id=order-success]").shouldHave(exactText("Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    public void shouldReturnErrorWhenWrongDataIn1stField() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Vasya");
        form.$("[data-test-id=phone] input").setValue("+79271112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid[data-test-id=name]").shouldBe(appear);
        $(".input__sub", 0).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    public void shouldReturnErrorWhenWrongDataIn2ndField() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+7992112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(appear);
        $(".input__sub", 1).shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));
    }

    @Test
    public void shouldReturnErrorWhenNothingInCheckbox() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("+79271112233");
        form.$("[data-test-id=agreement]");
        form.$(".button").click();
        $(".input_invalid[data-test-id=agreement]").shouldBe(appear);

    }

    @Test
    public void shouldReturnErrorIn1stWhenWrongDataIn1stAnd2ndField() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Vasya");
        form.$("[data-test-id=phone] input").setValue("+7992112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid[data-test-id=name]").shouldBe(appear);
        $(".input__sub", 0).shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));
    }

    @Test
    public void shouldReturnErrorWhenEmpty1stField() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("");
        form.$("[data-test-id=phone] input").setValue("+79271112233");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid[data-test-id=name]").shouldBe(appear);
        $(".input__sub", 0).shouldHave(exactText("Поле обязательно для заполнения"));
    }

    @Test
    public void shouldReturnErrorWhenEmpty2ndField() {

        open("http://localhost:9999");
        SelenideElement form = $(".form");
        form.$("[data-test-id=name] input").setValue("Василий");
        form.$("[data-test-id=phone] input").setValue("");
        form.$("[data-test-id=agreement]").click();
        form.$(".button").click();
        $(".input_invalid[data-test-id=phone]").shouldBe(appear);
        $(".input__sub", 1).shouldHave(exactText("Поле обязательно для заполнения"));
    }


}
