package com.example.tests;

import com.example.config.web.Browser;
import com.example.config.web.WebConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class CheckWebConfig {

    @Test
    public void testBrowser() {
        System.setProperty("browser", "CHROME");

        WebConfig config = ConfigFactory.create(WebConfig.class, System.getProperties());
        assertThat(config.browser()).isEqualTo(Browser.CHROME);
    }
}
