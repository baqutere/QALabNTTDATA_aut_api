package com.nttdata.steps;

import com.nttdata.model.Order;
import net.serenitybdd.rest.SerenityRest;

import static net.serenitybdd.rest.SerenityRest.restAssuredThat;
import static org.hamcrest.CoreMatchers.equalTo;

public class OrderStep {

    private static String URL_BASE = "https://petstore.swagger.io/v2/";

    public void crearOrden(Order order) {

        SerenityRest.given()
                .baseUri(URL_BASE)
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .body(order)
                .log().all()
                .post("store/order")
                .then()
                .log().all();

    }

    public void consultarOrden(Object orderId) {

        SerenityRest.given()
                .baseUri(URL_BASE)
                .relaxedHTTPSValidation()
                .contentType("application/json")
                .get("store/order/" + orderId.toString());
    }

    public void validarHttpStatus(int statusCode) {
        restAssuredThat(response -> response.statusCode(statusCode));
    }

    public void validarOrdenCompletada(boolean complete) {
        restAssuredThat(response -> response.body("'complete'", equalTo(complete)));
    }

    public void validarCantidadDeOrden(int quantity) {
        restAssuredThat(response -> response.body("'quantity'", equalTo(quantity)));
    }
}
