package com.demoqa;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.*;

public class AutomationPracticeForm {

    @BeforeAll
    static void setUp(){
        Configuration.baseUrl="https://demoqa.com";
        //Configuration.browserSize="1920x1080";
        Configuration.holdBrowserOpen= true;
    }

    @Test
    void fillFormTest() {
        // открываем нужную страницу
        open("/automation-practice-form");
        // проверяем загрузку страницы с помощью проверки наличия заголовка
        $("[.practice-form-wrapper]").shouldHave(text("Student Registration Form"));
        // удаляем баннеры (вызов скрипта на стороне браузера)
        executeAsyncJavaScript("$('footer').remove()");
        executeAsyncJavaScript("$('fixedban').remove()");
        // вводим значения
        $("[id=firstName]").setValue("Ivan");         // ввод текстовых значений
        $("[id=lastName]").setValue("Ivanov");        // ввод текстовых значений
        $("[id=userEmail]").setValue("Ivanov@gmail.com");     // ввод текстовых значений
        $("[id=genterWrapper]").$(byText("Male")).click();    // нажатие на кнопке (выбор пола)
        $("[id=userNumber]").setValue("8903911223"); // ввод текстовых значений
        $("[id=dateOfBirthInput]").click(); //кликаем на календарь
        $(".react-datepicker__month-select").selectOption("May"); //выбираем месяц из выпадающего списка
        $(".react-datepicker__year-select").selectOption("2008"); //выбираем год из выпадающего списка
        $(".react-datepicker__day--029:not(.react-datepicker__day--outside-month").click() ; //Поправить выбираем день из календаря (не равно прошлый месяц)
        $("[id=subjectsInput]").setValue("English").pressEnter(); //вводим текс и нажимаем ентер
        $("[id=hobbiesWrapper]").$(byText("Reading")).click();
        //$("[id=uploadPicture]").uploadFromClasspath("img/1.png"); // вариант загрузки сразу с папки ресурсы
        $("[id=uploadPicture]").uploadFile(new File("src/test/resources/foto.jpg"));
        $("[id=currentAddress]").setValue("ul.Lenina,d.101,kv.74");

        $("#state").click();
        $("#stateCity-wrapper").$(byText("NCR")).click(); // выбираем штат из списка
        $("[id=city]").click(); //кликаем на город
        $("[id=stateCity-wrapper]").$(byText("Delhi")).click();    // выбираем город из списка
        $("[id=submit]").click();
        // проверка наличия шапки окна
        $("#example-modal-sizes-title-lg").shouldHave(text("Thanks for submitting the form"));
        // проверка наличия введенного нами текста
        $(".table-responsive").shouldHave(text("Ivan"), text("Ivanov"),
                text("Ivanov@gmail.com"),
                text("Male"),
                text("8903911223"),
                text("29 May,2008"),
                text("English"),
                text("Reading"),
                text("foto.jpg"),
                text("ul.Lenina,d.101,kv.74"),
                text("NCR Delhi"));
    }

}
