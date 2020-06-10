#Seguradora-Api

## Descrição
API REST para uma seguradora de veículos.

## Tecnologias 
* Java 13;
* Springboot;
* MongoDb;
* Graddle;

## Passso a passo
Para rodar esta aplicação, você irá precisar de um banco de dados no mongoDb com o nome seguradoradb rodando na porta 27017. Depois disso, siga os passos:

1. Clone esse repositório;
2. Abra o projeto em sua IDE;
3. Verifique se as dependências foram importadas corretamente
4. Rode o projeto como uma aplicação springboot
5. Faça requisições HTTP nos endpoints http://localhost:8080/policy e http://localhost:8080/client

## Exemplo

### POST client Válido:
 {
 	"name": "paulo",
 	"cpf": "07713792422",
 	"city": "Natal",
 	"state": "RN"
 }
 
### POST client inválido:
{
	"name": "paulo",
	"cpf": "07712",
	"city": "Natal",
	"state": "RN"
}

### POST policy válido:

{
	"startTime": "2019-01-03",
	"endTime": "2019-02-03",
	"vehiclePlate": "21212",
	"value": 123.29
}

### POST policy inválido:

{
	"startTime": "2019-01-03",
	"endTime": "2019-02-03",
	"vehiclePlate": "21212",
}

## Regras

CRUD de clientes
- Dados: Nome Completo, CPF, Cidade e UF;
- Todos os dados são obrigatórios
CPF deve ser válido e deve ser único na base

CRUD de apólices
- Dados: Número da apólice, Início de vigência, Fim de vigência, Placa do veículo, Valor da apólice
- Todos os dados são obrigatórios
- O número da apólice deve ser gerado aleatoriamente e ser único

Consultar uma apólice por número
- Tela ou endpoint separado dos CRUDs
- Informar se a apólice venceu ou não
- Informar quantos dias para vencer, ou há quantos dias venceu
- Informar placa do veículo e valor da apólice