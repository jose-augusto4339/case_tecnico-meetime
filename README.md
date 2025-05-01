# Case técnico - Meetime

Este projeto trata-se de uma API REST, em Java 21 com SpringBoot, integrada à API do CRM do HubSpot.
Esta API expões endpoints para autenticação via OAuth2.0, recebimento de notificações por Webhook e criação de contatos.

## Endpoints
### ✅ GET /authorization/autorization-url
Retorna a URL de autorização para redirecionamento ao servidor OAuth 2.0 do HubSpot.

#### Reposta
```
<url de autorização>
```

### ✅ GET /authorization/oauth-callback
Responsável por receber o código de autorização e o estado do usuário, como parametro, após a aprovação da requisição de acesso.


#### Reposta
```
{
  "state" : "codigo-identificador-state" 
}
```

#### Parametros
| Parametro     | Tipo          |
| ------------- |:-------------:|
| code          | String        |
| state         | String        |

### ➕ POST /webhook/events
Responsável por receber notificação de criação de contatos vindos do HubSpot.


#### Parametros
| Parametro     | Tipo          |
| ------------- |:-------------:|
| event         | String        |

### ➕ POST /contact/
Responsável pela criação de contatos, necessário passar como parametro o código state recebido como resposta à requisição em **/authorization/oauth-callback**.


#### Parametros
| Parametro     | Tipo          |
| ------------- |:-------------:|
| state         | String        |

## Instruções para execução

1. Para a execução da aplicação, primeiro, realizar o clone do projeto

```
https://github.com/jose-augusto4339/case_tecnico-meetime.git
```
2. Em seguida, dentro do mesmo diretório que contém o arquivo **start-app.sh** criar um arquivo com o nome de **application.properties** com o seguinte conteúdo
```
spring.application.name=case-tecnico
server.port=8080

hubspot.redirect-uri=http://localhost:8080/authorization/oauth-callback
hubspot.authorize-uri=https://app.hubspot.com/oauth/authorize
hubspot.api-url=https://api.hubapi.com/crm/v3/objects/

hubspot.client-id=
hubspot.client-secret=
hubspot.scope=
```
Sendo que os locais que estão em branco, devem ser preenchidos com as suas informações, obtidas no HubSpot

3. Feito isso, executar o seguinte comando para iniciar a aplicação

```
bash start-app.sh
```
