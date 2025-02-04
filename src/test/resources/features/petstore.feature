@ExecuteFeatureOrder
Feature: Order

  @createOrder
  Scenario Outline: Creacion de orden
    Given tengo una order con los siguientes datos:
      | id   | petId   | quantity   | shipDate   | status   | complete   |
      | <id> | <petId> | <quantity> | <shipDate> | <status> | <complete> |
    When creo la order con los datos requeridos
    Then el código de respuesta es 200

    Examples:
      | id | petId | quantity | shipDate                 | status    | complete |
      | 2  | 4     | 25       | 2025-02-03T23:39:29.808Z | placed    | true     |
      | 20 | 10    | 15       | 2025-02-03T22:22:15.808Z | delivered | false    |
      | 30 | 17    | 17       | 2025-02-03T19:17:14.870Z | placed    | true     |

  @getOrder
  Scenario Outline: Consulta de orden
    Given tengo una order con el siguiente id: "<id>"
    When consulto la orden con el id elegido
    Then el código de respuesta de la consulta es: <status>
    And el campo complete es: "<complete>"
    And la cantidad de la orden es: <quantity>

    Examples:
      | id     | status | complete | quantity |
      | 2      | 200    | true     | 25       |
      | 20     | 200    | false    | 15       |
      | 30     | 200    | true     | 17       |
      | abc    | 404    |          | 0        |
      | -930   | 404    |          | 0        |
      | 848145 | 404    |          | 0        |
