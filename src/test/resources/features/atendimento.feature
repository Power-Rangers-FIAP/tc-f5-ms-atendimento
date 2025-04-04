Feature: Atendimento

  Scenario: Iniciar um novo atendimento
    When enviado os dados corretos para iniciar atendimento
    Then o atendimento é iniciado com sucesso

#  Scenario: Avaliar um atendimento
#    Given o sistema está disponível
#    When enviado os dados da avaliação
#    Then o atendimento é avaliado com sucesso


