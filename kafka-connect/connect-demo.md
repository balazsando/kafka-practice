# Connect demo

move to kafka home dir

/bin/connect-distributed.sh
/bin/connect-standalone.sh

`nano /config/connect-standalone.properties`

- bootstrap.servers=localhost:9092,localhost:9094
- key.converter=org.apache.kafka.connect.json.JsonConverter
- key.converter.schemas.enable=true
- value.converter=org.apache.kafka.connect.json.JsonConverter
- value.converter.schemas.enable=true
- plugins.path=/usr/local/share/kafka/plugins

`nano /config/mysql-connector.properties`

- name=msql-connector
- connector.class=io.confluent.connect.jdbc.JdbcSinkConnector
- tasks.max=1
- topics=orders
- connection.url=jdbc:mysqlL//localhost:3306/balazsando
- connection.user=root
- connection.password=admin

`cd /usr/local/share/kafka/plugins`
`mkdir kafka-connect-jdbc`
`cp ~/kafka-connect-jdbc-version.jar kafka-connect-jdbc/`
`cp ~/mysql-connector-java-version.jar kafka-connect-jdbc/`

move to kafka homedir

`bin/connect-standalone.sh config/connect-standalone.properties config/mysql-connector.properties`

populate topic
`bin/kafka-console-producer.sh --broker-list localhost:9093 --topic orders`
json formatted

schema + payload

validate in db

`SELECT * FROM orders;`