How to run kafki in PC with Mac OS 
====================

### 1.Download kafka and zookeeper server. Run two server.
`<link>` : https://github.com/michalk933/reservation-service

### 2.Implement Producer Kafka 

- ##### Add dependency in pom.xml
```xml
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-stream-binder-kafka-streams</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-stream-binder-kafka</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.kafka</groupId>
  <artifactId>spring-kafka</artifactId>
</dependency>
<dependency>
  <groupId>org.springframework.cloud</groupId>
  <artifactId>spring-cloud-stream</artifactId>
</dependency>
```

- ##### Create binding interface 
RESERVATION_OUT is kafka topic.
```java
@Output is method into sending event to kafka(producer), always must return MessageChannel.

interface ReservationBinding {
   String RESERVATION_OUT = "reservations";

   @Output(RESERVATION_OUT)
   MessageChannel reservationOut();
}
```

- ##### Add annotation in start context spring class (main method)
ReservationBinding.class this is interface with point ahead.
```java
@EnableBinding(ReservationBinding.class)
```

- ##### According when and how to send event to kafka you must make:
autowired MessageChannel and attribute our interface binder.
```java
private final MessageChannel messageChannel;
public ReservationSource(ReservationBinding reservationBinding) {
   this.messageChannel = reservationBinding.reservationOut();
}
```

- ##### Create Object Message<> by MessageBuilder()
```java
Message<NameOurObjectToSend> build = MessageBuilder
       .withPayload(OurObjectToSend)
       .build();
```

- ##### End last step, send event to kafka:
Using method from out binder and send event.
Make try/catch because during sent event maeby throw exception. 
E.g Claster dead,somebody change port kafka ect. 
```java
try {
   this.messageChannel.send(build);
} catch (Exception e) {
   // throw Exception
}
```


- ##### Add posts to application.properties.
```java
spring.cloud.stream.kafka.streams.binder.configuration.commit.interval.mms=1000
spring.cloud.stream.kafka.streams.binder.configuration.default.key.serde=org.apache.kafka.common.serialization.Serdes$StringSerde
spring.cloud.stream.kafka.streams.binder.configuration.default.value.serde=org.apache.kafka.common.serialization.Serdes$StringSerde

spring.cloud.stream.bindings.reservations.destination=reservations
spring.cloud.stream.bindings.reservations.producer.header-mode=raw

spring.cloud.stream.bindings.reservationsin.destination=reservations
spring.cloud.stream.bindings.reservationsin.consumer.header-mode=raw
spring.cloud.stream.kafka.streams.bindings.output.producer.keySerde==org.apache.kafka.common.serialization.StringSerializer
spring.cloud.stream.kafka.streams.bindings.output.producer.valueSerde==org.apache.kafka.common.serialization.StringSerializer
spring.cloud.stream.bindings.output.producer.use-native-encoding=true
```


### 3.Implement kafka consumer. `<link>` : https://github.com/michalk933/reservation-service















