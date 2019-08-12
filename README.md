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

The application uses this [dataset](https://github.com/snarayanank2/indian_banks) in Postgresql 


## Usage

To get a new Auth token (5 days validity) :

```bash
curl -X GET \
  https://localhost:8080/api/login \
  -H 'Accept: application/json' \
  -H 'Authorization: Basic Um9sZU9uZVVzZXI6Um9sZU9uZVBhc3M=' \
  -H 'cache-control: no-cache'

```

Search bank details by ifsc code :

```bash
curl -X GET \
  'https://localhost:8080/api/bank/search/ABHY0065002?limit=5&offset=0' \
  -H 'Accept: application/json' \
  -H 'Authorization: Bearer <token received from /login api>' \
  -H 'cache-control: no-cache'

```

Search bank details by bank name and city :

```bash
curl -X GET \
  'https://localhost:8080/api/bank/search/ABHYUDAYA%20COOPERATIVE%20BANK%20LIMITED/MUMBAI?limit=5&offset=0' \
  -H 'Authorization: Bearer <token received from /login api>' \
  -H 'cache-control: no-cache'
```

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[MIT](https://choosealicense.com/licenses/mit/)
