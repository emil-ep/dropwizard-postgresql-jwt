# Foobar

Web Service developed with Dropwizard framework, Postgresql database, JWT authentication
## Installation

To build the application using maven run  
```bash
mvn clean package
```

The generated jar file 'target/bankservice-1.0-SNAPSHOT.jar' can be run using

```bash
java -jar bankservice-1.0-SNAPSHOT.jar server config.yml
```

The web service is authenticated using Json web token  
User Roles defined in the project's are  
username : RoleOneUser  
password : RoleOnePass  


## Usage

To get a new Auth token (5 days validity) :

```bash
curl -X GET \
  https://localhost:8080/api/login \
  -H 'Accept: application/json' \
  -H 'Authorization: Basic Um9sZU9uZVVzZXI6Um9sZU9uZVBhc3M=' \
  -H 'Postman-Token: 83da5283-c091-4a31-a640-d9f1f7da5bb2' \
  -H 'cache-control: no-cache'

```

Search bank details by ifsc code :

```bash
curl -X GET \
  'https://localhost:8080/api/bank/search/ABHY0065002?limit=5&offset=0' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer <token received from /login api>' \
  -H 'Postman-Token: 3c5b6124-1cca-4a71-87d4-f456ba1ce359' \
  -H 'cache-control: no-cache'

```

Search bank details by bank name and city :

```bash
curl -X GET \
  'https://localhost:8080/api/bank/search/ABHYUDAYA%20COOPERATIVE%20BANK%20LIMITED/MUMBAI?limit=5&offset=0' \
  -H 'Authorization: Bearer <token received from /login api>' \
  -H 'Postman-Token: 6f42f355-953d-471b-9af9-fb678cd3fe5c' \
  -H 'cache-control: no-cache'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
