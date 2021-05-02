package io.github.bhhan.example;

import cucumber.api.java.en.Given;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.springframework.boot.test.context.SpringBootTest;

import static io.restassured.RestAssured.given;

/**
 * Created by hbh5274@gmail.com on 2021-04-28
 * Github : http://github.com/bhhan5274
 */

@SpringBootTest(classes = ExampleServiceComponentTest.class, webEnvironment = SpringBootTest.WebEnvironment.NONE)
public class ExampleServiceComponentTestStepDefinitions {

    private String baseUrl = "http://localhost:9000";

    @Given("(.*) 롯데리아를 오픈한다.")
    public void helloWorld(String shopName){
        final ShopDto.ShopReq shopReq = ShopDto.ShopReq
                .builder()
                .name(shopName)
                .open(false)
                .minOrderAmount(Money.wons(10000L))
                .commissionRate(0.2)
                .build();

        final Response response = given()
                .contentType(ContentType.JSON)
                .body(shopReq)
                .when()
                .post(baseUrl + "/v1/shops");

        final Long shopId = response.then()
                .statusCode(HttpStatus.SC_CREATED)
                .extract()
                .body()
                .as(Long.class);

        System.out.println(String.format("Shop Added: ShopId[%d]", shopId));
    }
}
