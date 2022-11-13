package com.example.config;

import org.aeonbits.owner.Config;

@WebConfig.Sources({
        "classpath:${env}.properties"
})
public interface WebConfig extends Config {

    @Key("browserName")
    String getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("browserSize")
    String getBrowserSize();

    @Key("browserPosition")
    String getBrowserPosition();

    @Key("baseUrl")
    String getBaseUrl();

    @Key("baseURI")
    String getBaseURI();

    @Key("remoteUrl")
    String getRemoteUrl();
}
