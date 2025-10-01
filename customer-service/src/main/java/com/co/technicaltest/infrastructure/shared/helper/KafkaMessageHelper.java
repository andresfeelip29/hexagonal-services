package com.co.technicaltest.infrastructure.shared.helper;

import com.co.technicaltest.infrastructure.exception.PayloaTransformException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.function.BiConsumer;

@Slf4j
@Component
public class KafkaMessageHelper {

    private final ObjectMapper objectMapper;

    public KafkaMessageHelper(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    public <T> T getEventPayload(String payload, Class<T> outputType) {
        try {
            return objectMapper.readValue(payload, outputType);
        } catch (JsonProcessingException e) {
            log.error("No se pudo leer {} objeto!", outputType.getName(), e);
            throw new PayloaTransformException("No se pudo leer " + outputType.getName() + " object!");
        }
    }

    public String createPayload(Object object) {
        try {
            return objectMapper.writeValueAsString(object);
        } catch (JsonProcessingException e) {
            throw new PayloaTransformException("No se pudo crear el objeto!");
        }
    }

    public BiConsumer<SendResult<String, String>, Throwable>
    getCallback(String topicName, String message) {

        return (result, ex) -> {
            if (ex == null) {
                RecordMetadata metadata = result.getRecordMetadata();
                log.info("Se recibe nueva data. Topic: {}; Partition {}; Offset {}; Timestamp {}, at time {}",
                        metadata.topic(),
                        metadata.partition(),
                        metadata.offset(),
                        metadata.timestamp(),
                        System.nanoTime());
            } else {
                log.error("Erro al enviar el mensaje {} al topic {}", message, topicName, ex);
            }
        };
    }

    public UUID uuidFromString(String uuid) {
        try {
            return UUID.fromString(uuid);
        } catch (IllegalArgumentException e) {
            throw new PayloaTransformException("No se pudo convertir el UUID!");
        }
    }
}
