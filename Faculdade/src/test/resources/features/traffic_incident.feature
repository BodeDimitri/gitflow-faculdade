Funcionalidade: Gestão de incidentes de trânsito

  Cenário: Cadastro de incidente com sucesso
    Dado que um incidente válido está disponível
    Quando o cliente envia uma requisição POST para criar o incidente
    Então o sistema deve retornar o status 201
    E o corpo da resposta deve conter o campo "location" com valor "Av. Brasil"

  Cenário: Consulta de incidente existente por ID
    Dado que existe um incidente com ID 1
    Quando o cliente envia uma requisição GET para o endpoint "/traffic-incidents/1"
    Então o sistema deve retornar o status 200
    E o corpo da resposta deve conter o campo "description" com valor "Batida leve"

  Cenário: Falha ao cadastrar incidente com dados inválidos
    Dado que um incidente inválido (sem location) está disponível
    Quando o cliente envia uma requisição POST para criar o incidente
    Então o sistema deve retornar o status 400
