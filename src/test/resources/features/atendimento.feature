Feature: Atendimento

  Scenario: Iniciar um novo atendimento
    When enviado os dados corretos para iniciar atendimento
    Then o atendimento é iniciado com sucesso

  Scenario: Confirmar chegada do paciente
    Given que existe um atendimento em andamento
    When o sistema recebe a requisição para confirmar chegada com esse ID
    Then o sistema confirma a chegada com sucesso

  Scenario: Registrar enfermidade de um paciente
    Given que o atendimento "12345" está em andamento
    When o sistema recebe os dados da enfermidade corretamente
    Then a enfermidade é registrada com sucesso