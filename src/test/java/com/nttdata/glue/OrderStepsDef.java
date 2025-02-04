package com.nttdata.glue;

import com.nttdata.model.Order;
import com.nttdata.steps.OrderStep;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Map;

public class OrderStepsDef {

    OrderStep orderStep = new OrderStep();
    private Order order;
    private Object orderId;

    @Given("tengo una order con los siguientes datos:")
    public void tengoUnaOrderConLosSiguientesDatos(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        for (Map<String, String> row : rows) {
            int id = Integer.parseInt(row.get("id"));
            int petId = Integer.parseInt(row.get("petId"));
            int quantity = Integer.parseInt(row.get("quantity"));
            String shipDate = row.get("shipDate");
            String status = row.get("status");
            boolean complete = Boolean.parseBoolean(row.get("complete"));

            this.order = new Order(id, petId, quantity, shipDate, status, complete);
        }
    }

    @When("creo la order con los datos requeridos")
    public void creoLaOrderConLosDatosDeLaOrden() {
        orderStep.crearOrden(this.order);
    }

    @Then("el código de respuesta es {int}")
    public void elCodigoDeRespuestaEs(int httpStatus) {
        orderStep.validarHttpStatus(httpStatus);
    }

    @Given("tengo una order con el siguiente id: {string}")
    public void tengoUnaOrderConElSiguienteId(String arg0) {
        this.orderId = arg0;
    }

    @When("consulto la orden con el id elegido")
    public void consultoLaOrdenConElIdElegido() {
        orderStep.consultarOrden(this.orderId);
    }


    @Then("el código de respuesta de la consulta es: {int}")
    public void elCodigoDeRespuestaDeLaConsultaEs(int httpStatus) {
        orderStep.validarHttpStatus(httpStatus);
    }


    @And("el campo complete es: {string}")
    public void elCampoCompleteEs(String arg0) {
        if (!arg0.isEmpty()) {
            boolean completeValue = Boolean.parseBoolean(arg0);
            orderStep.validarOrdenCompletada(completeValue);
        }
    }

    @And("la cantidad de la orden es: {int}")
    public void laCantidadDeLaOrdenEs(Integer quantity) {
        if (quantity > 0) {
            orderStep.validarCantidadDeOrden(quantity);
        }
    }
}
