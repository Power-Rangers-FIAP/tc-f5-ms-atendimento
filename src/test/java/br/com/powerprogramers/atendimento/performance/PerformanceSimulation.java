package br.com.powerprogramers.atendimento.performance;

import static io.gatling.javaapi.core.CoreDsl.constantUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.global;
import static io.gatling.javaapi.core.CoreDsl.rampUsersPerSec;
import static io.gatling.javaapi.core.CoreDsl.scenario;
import static io.gatling.javaapi.http.HttpDsl.http;
import static io.gatling.javaapi.http.HttpDsl.status;

import io.gatling.javaapi.core.ActionBuilder;
import io.gatling.javaapi.core.ScenarioBuilder;
import io.gatling.javaapi.core.Simulation;
import io.gatling.javaapi.http.HttpProtocolBuilder;
import java.time.Duration;

public class PerformanceSimulation extends Simulation {

    private final HttpProtocolBuilder httpProtocolBuilder =
            http.baseUrl("http://localhost:8080")  // Altere para a URL correta do seu serviço de atendimento
                    .header("Content-Type", "application/json");


    ActionBuilder iniciarAtendimento =
            http("iniciar atendimento")
                    .get("/atendimento/iniciar")
                    .check(status().is(200));

    ActionBuilder confirmarChegada =
            http("confirmar chegada")
                    .get("atendimento/confirmar/chegada/%s".formatted("#{atendimentoId}"))
                    .check(status().is(202));

    ActionBuilder finalizarAtendimento =
            http("finalizar atendimento")
                    .put("/atendimento/finalizar/%s".formatted("#{atendimentoId}"))
                    .check(status().is(200));

    ScenarioBuilder scenarioAtendimento =
            scenario("atendimento crud")
                    .exec(iniciarAtendimento)
                    .exec(confirmarChegada)
                    .exec(finalizarAtendimento);

    {
        setUp(
                scenarioAtendimento.injectOpen(
                        rampUsersPerSec(1).to(5).during(Duration.ofSeconds(5)),
                        constantUsersPerSec(5).during(Duration.ofSeconds(15)),
                        rampUsersPerSec(5).to(1).during(Duration.ofSeconds(5))))
                .protocols(httpProtocolBuilder)
                .assertions(
                        global().responseTime().max().lt(200), global().failedRequests().count().is(0L));
    }
}
