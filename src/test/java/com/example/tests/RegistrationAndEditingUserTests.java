package com.example.tests;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class RegistrationAndEditingUserTests extends TestBase{

    static TestData userData = new TestData();
    static TestData userDataAfterEdit = new TestData();
    static RegistrationPage registrationPage = new RegistrationPage();

    @Test
    @DisplayName("Регистрация нового пользователя и вход под ним")
    public void registrationTest() {
        registrationPage.registration(userData);
        registrationPage.login(userData);
        registrationPage.openPageWithAuth(userData);
    }

    @Test
    @DisplayName("Редактирование профиля с проверкой через UI")
    public void editProfileTest() {
        registrationPage.registration(userData);
        registrationPage.login(userData);
        registrationPage.openPageWithAuth(userData);
        registrationPage.editAndCheckProfile(userDataAfterEdit);
    }
}
