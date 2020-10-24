# deputados-job :clock12:

<h2>Projeto com Spring Batch para obter todas as informações referentes a deputados e suas despesas no ano corrente</h2>

![](https://atitudereflexiva.files.wordpress.com/2019/10/spring-batch-reference-model.png)

<b>Descrição</b>

O projeto possuí um Job com os seguintes steps:

:heavy_check_mark: Capturar informações referentes aos 513 deputados

:heavy_check_mark: Capturar as informações de despesas de todos os deputados no ano corrente

:information_source: OBS: Os dados são publicos e podem ser obtidos pelo link: <a href="https://dadosabertos.camara.leg.br/swagger/api.html">Dados públicos câmara dos deputados</a>

<b>Funcionalidades</b>

:white_check_mark: Batch

O projeto foi elaborado para rodar um Job todos os dias a meia noite, afim de atualizar a base de dados, e ao iniciar a aplicação.

:white_check_mark: API REST

Foi disponibilizado no mesmo projeto uma API rest para obter os dados tratados, e disponibilizar informações como quantidade total de gastos de um determinado deputado, total de gastos de um determinado partido, gastos totais, etc.
Para acessar a documentação da API e a base de dados:

```
Para consultar a documentação de produtos acesse:
- http://localhost:8080/swagger-ui/

Para consultar o banco de dados acess:
- http://localhost:8080/h2/
```

<h3>Ferramentas e tecnologias</h3>

:ballot_box_with_check: Java 11

:ballot_box_with_check: Spring Boot

:ballot_box_with_check: Spring Batch

:ballot_box_with_check: Spring Data JPA

:ballot_box_with_check: Lombok

:ballot_box_with_check: Swagger

:ballot_box_with_check: Hikari

:ballot_box_with_check: Banco de dados H2

:ballot_box_with_check: Modelo arquitetural REST

