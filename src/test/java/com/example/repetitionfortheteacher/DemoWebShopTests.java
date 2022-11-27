package com.example.repetitionfortheteacher;

import com.codeborne.selenide.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class DemoWebShopTests {
    /*
    curl 'https://demowebshop.tricentis.com/addproducttocart/details/16/1' \
  -H 'Accept: *\/*' \
            -H 'Accept-Language: ru-RU,ru;q=0.9,en-US;q=0.8,en;q=0.7' \
            -H 'Connection: keep-alive' \
            -H 'Content-Type: application/x-www-form-urlencoded; charset=UTF-8' \
            -H 'Cookie: Nop.customer=5c93098d-962d-40c7-ba08-8f67f888e5ff; ARRAffinity=754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae; __utmc=78382081; __utmz=78382081.1666796717.1.1.utmcsr=(direct)|utmccn=(direct)|utmcmd=(none); ARRAffinitySameSite=754c43f3fb666b4689fd8452291c08520e941e1737ad17b31babd87059cc27ae; __utma=78382081.705551822.1666796717.1666814487.1666863506.4; NopCommerce.RecentlyViewedProducts=RecentlyViewedProductIds=16&RecentlyViewedProductIds=72; __utmb=78382081.8.10.1666863506; __atuvc=14%7C43; __atuvs=635a54d2d98ebf0f002' \
            -H 'Origin: https://demowebshop.tricentis.com' \
            -H 'Referer: https://demowebshop.tricentis.com/build-your-own-computer' \
            -H 'Sec-Fetch-Dest: empty' \
            -H 'Sec-Fetch-Mode: cors' \
            -H 'Sec-Fetch-Site: same-origin' \
            -H 'User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/106.0.0.0 Safari/537.36' \
            -H 'X-Requested-With: XMLHttpRequest' \
            -H 'sec-ch-ua: "Chromium";v="106", "Google Chrome";v="106", "Not;A=Brand";v="99"' \
            -H 'sec-ch-ua-mobile: ?0' \
            -H 'sec-ch-ua-platform: "Windows"' \
            -H 'sec-gpc: 1' \
            --data-raw 'product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1' \
            --compressed
     */

    @BeforeAll
    static void setUp() {
        Configuration.baseUrl = "http://demowebshop.tricentis.com";
        RestAssured.baseURI = "http://demowebshop.tricentis.com";
    }

    @Test
    void addToCardTest() {
        given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded")
                .cookie("Nop.customer=5c93098d-962d-40c7-ba08-8f67f888e5ff;")
                .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

    @Test
    void addNewUserToCardTest() {
        String quantity = "4";
        given()
                .log().uri()
                .log().body()
                .contentType("application/x-www-form-urlencoded")
                .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=" + quantity)
                .when()
                .post("https://demowebshop.tricentis.com/addproducttocart/details/16/1")
                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("success", is(true))
                .body("updatetopcartsectionhtml", is("(" + quantity + ")"))
                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"));
    }

//    @Test
//    void addToCardWithUITest() {
//
//        String authCookieValue = given()
//                .log().uri()
//                .log().body()
//                .contentType("application/x-www-form-urlencoded")
//                .cookie("Nop.customer=5c93098d-962d-40c7-ba08-8f67f888e5ff;")
//                .body("product_attribute_16_5_4=14&product_attribute_16_6_5=15&product_attribute_16_3_6=19&product_attribute_16_4_7=44&product_attribute_16_8_8=22&addtocart_16.EnteredQuantity=1")
//                .when()
//                .post("/addproducttocart/details/16/1")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .body("success", is(true))
//                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                .cookie("Nop.customer").toString();
//
//        open("/Themes/DefaultClean/Content/images/logo.png");
//        Cookie authCookie = new Cookie("Nop.customer", authCookieValue);
//        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
//        open("/Themes/DefaultClean/Content/images/logo.png");
//    }
//
//    @Test
//    void addToCardWithUISecondOptionTest() {
//        String authCookieName = "Nop.customer",
//                authCookieValue = "5c93098d-962d-40c7-ba08-8f67f888e5ff",
//                body = "product_attribute_16_5_4=14" +
//                        "&product_attribute_16_6_5=15&product_attribute_16_3_6=19" +
//                        "&product_attribute_16_4_7=44&product_attribute_16_8_8=22" +
//                        "&addtocart_16.EnteredQuantity=1";
//        given()
//                .log().uri()
//                .log().body()
//                .contentType("application/x-www-form-urlencoded")
//                .cookie(authCookieName, authCookieValue)
//                .body(body)
//                .when()
//                .post("/addproducttocart/details/16/1")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .body("success", is(true))
//                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                .cookie("Nop.customer").toString();
//
//        open("/Themes/DefaultClean/Content/images/logo.png");
//        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
//        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
//        open("/Themes/DefaultClean/Content/images/logo.png");
//    }

//    @Test
//    void addToCardWithUIWithAuthTest() {
//        String authCookieName = "NOPCOMMERCE.AUTH";
//
//        String authCookieValue = given()
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .body("Email=vbdv%40feferf.ru&Password=itLf7%40U%40Bf6khGH")
////                .log().all()
//                .when()
//                .post("/login")
//                .then()
////                .log().all()
//                .statusCode(302)
//                .extract()
//                .cookie(authCookieName);
//
//        String body = "product_attribute_16_5_4=14" +
//                "&product_attribute_16_6_5=15&product_attribute_16_3_6=19" +
//                "&product_attribute_16_4_7=44&product_attribute_16_8_8=22" +
//                "&addtocart_16.EnteredQuantity=1";
//
//        String cartSize = given()
//                .log().uri()
//                .log().body()
//                .contentType("application/x-www-form-urlencoded; charset=UTF-8")
//                .cookie(authCookieName, authCookieValue)
//                .body(body)
//                .when()
//                .post("/addproducttocart/details/16/1")
//                .then()
//                .log().status()
//                .log().body()
//                .statusCode(200)
//                .body("success", is(true))
//                .body("message", is("The product has been added to your <a href=\"/cart\">shopping cart</a>"))
//                .extract().path("updatetopcartsectionhtml");
//
//        open("/Themes/DefaultClean/Content/images/logo.png");
//        Cookie authCookie = new Cookie(authCookieName, authCookieValue);
//        WebDriverRunner.getWebDriver().manage().addCookie(authCookie);
//        open("");
//        $(".cart-qty").shouldHave(Condition.text(cartSize));
//    }
}
