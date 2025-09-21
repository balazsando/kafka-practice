# Event driven architecture

- Microservices
- Serverless
- FaaS
- Streaming
- Event sourcing
- CQRS

Pros

- decoupling (broker middle man)
- encapsulation (clear boundaries)
- optimization (its fast)
- scalibility

Cons

- steep learning curve (source of truth, duplicate events)
- complexity
- loss of transactionality
- lineage

### Event Storming & DDD

- EDA revolves around events
- coloring scheme
    - domain = orange
    - policy = purple
    - external system = pink
    - command = blue
- aggregate (events & commands)
- bounded context

---

## Kafka
- open source
- java/scala (classes - compiler - jvm)
- high throughput (no serialization, zero copy for non-TLS connections)
- more than a messaging system (pub/sub, distributed storage, data processing)

### components
- broker (cluster)
- zookeeper (manages cluster)

### AVRO
- binary
- not human readable
- uses schema

---

## Kafka Stream
- consumer (source) -> transformations (topology) -> producer (sink)
- table -> processing evolving events -> compaction topic
- stream -> processing independent events -> delete topic

### Processor
- stateless
    - branch
    - filter
    - inverse 
    - filter
    - map
    - flatmap
    - foreach
    - peek
    - groupby
    - merge
- stateful - state store
    - aggregations
    - count
    - joins
    - windowing
    - custom processors

---

## KSQL
- KCLI - KSQL - Kafka stream
- DDL
    - CREATE STREAM
    - CREATE TABLE
    - DROP STREAM
    - DROP TABLE
    - CREATE STREAM AS SELECT
    - CREATE TABLE AS SELECT
- DML
    - SELECT
    - INSERT
    - DELETE
    - CREATE STREAM AS SELECT
    - CREATE TABLE AS SELECT

---

## Kafka connect

why use connect?
- reuse code
    - many intergration
- avoid reinventing the wheel
    - batch operations to db
    - connection overhead is skipped
- scalability
    - standalone
    - distribute

### Architecture

- Connect
    - workers (working on the tasks)
    - connectors (RDB, graphDB, searchEngine)
        - source connector (JDBC source connector)
        - sync connector (AWS S3 Sink connector)
    - task
- Kafka
- Database

Task
- connector (from/to storage)
- smt (single message transform)
- converter (from/to kafka)

