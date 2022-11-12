package com.example.tests;

import com.codeborne.selenide.WebDriverRunner;
import com.example.testData.TestData;
import io.qameta.allure.Step;
import org.openqa.selenium.Cookie;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.value;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;

public class RegistrationPage {

    private final String headerAndParamName = "__RequestVerificationToken";
    private final String headerValue = "gY9gtRtdsUz0eNXTT5kxEuwyUP-V2AN8WXXQ4CMp5cD4_ZGXfOHMj84DQOigK2P_XbA-f52HkjZOnPoIN29SUNoKr6TuI6ZGrgMZoluvv2s1";
    private final String paramValue = "tQeQN9lbb0AwzYxfh7DROfB5qZdRUNjaFPu43rib4-VFGUc_g9NUPcqoMb2B8v-tfhWaTNKRi4eMoea9M98uc98aqqJjpcIbYl6doqAu5tc1";
    private final String authCookieName = "NOPCOMMERCE.AUTH";


    @Step("Регистрация через API")
    public void registration(TestData testData) {
        given()
                .when()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam(headerAndParamName, paramValue)
                .formParam("FirstName", testData.firstName)
                .formParam("LastName", testData.lastName)
                .formParam("Email", testData.email)
                .formParam("Password", testData.password)
                .formParam("ConfirmPassword", testData.password)
                .cookie(headerAndParamName, headerValue)
                .post("/register")
                .then();
    }

    @Step("Вход по API")
    public String login(TestData testData) {
        String authToken = given()
                .when()
                .contentType("application/x-www-form-urlencoded; charset=utf-8")
                .formParam("Email", testData.email)
                .formParam("Password", testData.password)
                .post("/login")
                .then()
                .extract()
                .cookie(authCookieName);
        return authToken;
    }

    @Step("Открытие UI страницы с авторизацией")
    public void openPageWithAuth(TestData testData) {
        open("/Themes/DefaultClean/Content/images/logo.png");
        Cookie authCookie = new Cookie(authCookieName, login(testData));
        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
        open("");
        $(".account").shouldHave(text(testData.email));
    }

    @Step("Редактирование профиля и проверка отредактированных данных")
    public void editAndCheckProfile(TestData testDataAfterEdit) {
        open("/customer/info");
        $("#FirstName").setValue(testDataAfterEdit.firstName);
        $("#LastName").setValue(testDataAfterEdit.lastName);
        $("#Email").setValue(testDataAfterEdit.email);
        $("[value='Save']").click();
        refresh();
        $("#FirstName").shouldHave(value(testDataAfterEdit.firstName));
        $("#LastName").shouldHave(value(testDataAfterEdit.lastName));
        $("#Email").shouldHave(value(testDataAfterEdit.email));
    }
}

