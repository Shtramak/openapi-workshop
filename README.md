# Volunteer Service

###OpenApi Generator

- source: https://github.com/OpenAPITools/openapi-generator
- jar: https://drive.google.com/file/d/1at4E8JuWi1OrtZxbnqlZTUmaZckHCe5S/view?usp=sharing

### Generators list:

https://openapi-generator.tech/docs/generators/

### Generate server part:

java -jar openapi-generator-cli.jar generate -g spring -i volunteer-openapi.yaml -c server-config.json -o volunteer-openapi-server

### Generate client part:

java -jar openapi-generator-cli.jar generate -g java -i volunteer-openapi.yaml -c client-config.json -o volunteer-openapi-client --library=resttemplate

https://openapi.tools/

https://github.com/OAI/OpenAPI-Specification/blob/main/versions/3.0.0.md

### Examples of requests:

Create Volunteer:

```
POST http://localhost:7777/volunteers
Content-Type: application/json

{
  "name": "Bryndushi",
  "date_of_creation": "2022-04-27",
  "address": {
    "city": "Ivano-Frankivsk",
    "zip_code": "5349"
  }
}
```

Create Volunteer Bank Account:

```
POST http://localhost:7777/volunteers/Bryndushi/bankAccount
Content-Type: application/json

{
    "bank_name": "Ukrsibbank",
    "account_number": "0987654321"
}
```

Donate to Volunteer:

```
POST http://localhost:7777/volunteers/Bryndushi/donate
Content-Type: application/json

{
"bank_name": "Ukrsibbank",
"currency": "EUR",
"amount": 5000
}
```

Get all Volunteers:

```
GET http://localhost:7777/volunteers
Accept: application/json
```

#For real donation:
https://www.facebook.com/CROCUS.31.03.2022/
```
Найменування організації: БО БФ БРИНДУШІ
Код отримувача: 44730655
Назва банку: ІВАНО-ФРАНКІВ.ФІЛІЯ АТ КБ “ПРИВАТБАНК”
Рахунок отримувача у
форматі IBAN: UA983366770000026006052552716
Валюта: UAH

КАРТА:
5169 3351 0068 7587
```