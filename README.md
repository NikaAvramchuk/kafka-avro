# Usage:

* Build the project:
```
mvn clean install
```
* Run app:
```
mvn spring-boot:run
```
* Test avro producer and consumer communication
```
Post http://localhost:8080/api/book with body as json:
{
"name": "Sto lat samotnosci",
"year": 1967
}
```
* Verify Communication. Check the logs in terminal:
```
AvroProducer sent a message about book {}
Avro Consumer consumed: {}
```
