package com.example.config;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.aeonbits.owner.ConfigFactory;

public class ConfigProvider {

    public ConfigProvider setConfiguration() {
        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        Configuration.remote = config.getRemoteUrl();
        Configuration.browser = config.getBrowserName();
        Configuration.browserVersion = config.getBrowserVersion();
        Configuration.browserSize = config.getBrowserSize();
        Configuration.browserPosition = config.getBrowserPosition();
        Configuration.baseUrl = config.getBaseUrl();
        RestAssured.baseURI = config.getBaseURI();
        return this;
    }
}