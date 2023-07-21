package ru.netology;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class DebitCartTest {

    @BeforeEach
    void setUp () { open("http://localhost:9999/");}

    @Test
    void formSuccessfully() {
       SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79211234567");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();

        $(".paragraph").shouldHave(exactText(
                "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время."));
    }

    @Test
    void formNameValidationSpace() {
        SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("  ");
        form.$("[data-test-id=phone] input").setValue("+79211234567");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        form.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void formNameValidationLatinSymbol() {
        SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("Teddy Westsize");
        form.$("[data-test-id=phone] input").setValue("+79211234567");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        form.$(".input__sub").shouldHave(exactText("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы."));

    }

    @Test
    void formPhoneValidation() {
        SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue(" ");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Поле обязательно для заполнения"));

    }

    @Test
    void formPhoneValidation2() {
        SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("987654345678");
        form.$(".checkbox__box").click();
        form.$(".button_view_extra").click();
        SelenideElement phone = $("[data-test-id=phone]");
        phone.$(".input__sub").shouldHave(exactText("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678."));

    }

    @Test
    void formPhoneValidation2131() {
        SelenideElement form = $(".form_size_m");
        form.$("[data-test-id=name] input").setValue("Иванов Иван");
        form.$("[data-test-id=phone] input").setValue("+79211234567");
        form.$(".button_view_extra").click();
        form.$(".checkbox__text").shouldHave(exactText("Я соглашаюсь с условиями обработки и использования моих персональных данных и разрешаю сделать запрос в бюро кредитных историй"));
    }
}

