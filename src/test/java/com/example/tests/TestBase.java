package com.example.tests;


import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.logevents.SelenideLogger;
import com.example.config.ConfigProvider;
import io.qameta.allure.Step;
import io.qameta.allure.selenide.AllureSelenide;
import io.restassured.RestAssured;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.remote.DesiredCapabilities;

import static com.example.helper.Attachments.*;

public class TestBase {

    @BeforeAll
    @Step("Установка настроек браузера")
    static void setup() {
        SelenideLogger.addListener("allureTest", new AllureSelenide());

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;

        ConfigProvider config = new ConfigProvider();
        config.setConfiguration();

//        RestAssured.baseURI = "https://demowebshop.tricentis.com/";
    }

    @AfterEach
    @Step("Получить аттачменты")
    void getAttachments() {
        takeScreenshot();
        addSource();
        addHTMLSource();
        addBrowserConsoleLog();
        addVideo();
    }
}
