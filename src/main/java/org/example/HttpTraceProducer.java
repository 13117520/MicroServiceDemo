package org.example;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class HttpTraceProducer {

    @Value("${kafka.topic.http-trace}")
    private String httpTraceTopic;

    private final KafkaTemplate<String, HttpTraceData> kafkaTemplate;

    public HttpTraceProducer(KafkaTemplate<String, HttpTraceData> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendHttpTrace(HttpTraceData httpTraceData) {
        kafkaTemplate.send(httpTraceTopic, httpTraceData);
    }
}
