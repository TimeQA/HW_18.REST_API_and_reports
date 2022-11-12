package com.example.testData;

import com.example.tests.TestBase;
import com.github.javafaker.Faker;

public class TestData extends TestBase {
    static Faker faker = new Faker();
    public static String
            firstName = faker.name().firstName(),
            lastName = faker.name().lastName(),
            email = faker.internet().emailAddress(),
            password = faker.internet().password();
}
