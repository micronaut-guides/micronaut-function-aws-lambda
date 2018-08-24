Guide: 

http://guides.micronaut.io/micronaut-function-aws-lambda/guide/index.html


cURL Command:

`$ curl -X "POST" "http://localhost:8080/invoice/vat" -H 'Content-Type: application/json' -d $'{"countryCode": "es","vatNumber": "B86412491","lines": [{"count": 2,"price": 19.99,"productId": "1491950358"},{"count": 1,"price": 15,"productId": "1680502395"}]}'`
