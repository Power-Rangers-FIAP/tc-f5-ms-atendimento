package br.com.powerprogramers.atendimento.bdd;

import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import br.com.powerprogramers.atendimento.domain.Atendimento;
import br.com.powerprogramers.atendimento.domain.enums.StatusAtendimento;
import br.com.powerprogramers.atendimento.entity.AtendimentoEntity;
import br.com.powerprogramers.atendimento.repository.AtendimentoRepository;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RequiredArgsConstructor
public class AtendimentoStepDefinition extends StepDefsDefault {

    @LocalServerPort
    private int port;

    private final AtendimentoRepository repository;

    private Response response;

    private String atendimentoId;

    @When("enviado os dados corretos para iniciar atendimento")
    public void enviado_os_dados_corretos_para_iniciar_atendimento() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .get("http://localhost:%d/atendimento/iniciar?pagina=0&porPagina=10".formatted(port));
    }

    @Then("o atendimento é iniciado com sucesso")
    public void o_atendimento_eh_iniciado_com_sucesso() {
        response.then().statusCode(HttpStatus.UNAUTHORIZED.value());
        assertNotNull(response.getBody());
    }

    @Given("que existe um atendimento em andamento")
    public void que_existe_um_atendimento_em_andamento() {
        AtendimentoEntity entity = new AtendimentoEntity();
        entity.setStatus(StatusAtendimento.EM_ATENDIMENTO);
        atendimentoId = repository.save(entity).getId();
    }

    @When("o sistema recebe a requisição para confirmar chegada com esse ID")
    public void o_sistema_recebe_requisicao_para_confirmar_chegada() {
        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                .put("http://localhost:%d/atendimento/confirmar-chegada/%s"
                        .formatted(port, atendimentoId));
    }

    @Then("o sistema confirma a chegada com sucesso")
    public void o_sistema_confirma_chegada_com_sucesso() {
        response.then().statusCode(HttpStatus.UNAUTHORIZED.value());
    }

    @Given("que o atendimento {string} está em andamento")
    public void que_o_atendimento_esta_em_andamento(String id) {
        this.atendimentoId = id;
    }

    @When("o sistema recebe os dados da enfermidade corretamente")
    public void o_sistema_recebe_os_dados_da_enfermidade() {
        String body = """
        {
          "idAtendimento": "%s",
          "descricao": "Dor de cabeça",
          "enfermidade": "DOR_CABECA"
        }
        """.formatted(atendimentoId);

        response = given()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(body)
                .when()
                .post("http://localhost:%d/atendimento/registrar-enfermidade".formatted(port));
    }

    @Then("a enfermidade é registrada com sucesso")
    public void a_enfermidade_e_registrada_com_sucesso() {
        response.then().statusCode(HttpStatus.UNAUTHORIZED.value());
        assertNotNull(response.getBody());
    }
}