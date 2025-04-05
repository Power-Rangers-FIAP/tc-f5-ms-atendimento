package br.com.powerprogramers.atendimento.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@RequiredArgsConstructor
@AutoConfigureTestDatabase
public class AtendimentoStepDefinition extends StepDefsDefault {

    @LocalServerPort
    private int port;

    private Response response;

    @When("enviado os dados corretos para iniciar atendimento")
    public void enviado_os_dados_corretos_para_iniciar_atendimento() {

        response =
                given()
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .when()
                        .get("http://localhost:%d/atendimento/iniciar?pagina=0&porPagina=10".formatted(port));
    }

    @Then("o atendimento é iniciado com sucesso")
    public void o_atendimento_eh_iniciado_com_sucesso() {
        response.then().statusCode(HttpStatus.OK.value());
        assertNotNull(response.getBody());
    }




}

