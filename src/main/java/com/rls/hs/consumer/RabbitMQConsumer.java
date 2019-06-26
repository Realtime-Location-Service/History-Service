package com.rls.hs.consumer;

import com.rls.hs.models.DBLocationData;
import com.rls.hs.models.RabbitLocationData;
import com.rls.hs.repositories.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RabbitMQConsumer {
    private final LocationDataRepository locationDataRepository;

    @Autowired
    public RabbitMQConsumer(LocationDataRepository theLocationDataRepository) {
        this.locationDataRepository = theLocationDataRepository;
    }

    static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${application.rabbitmq.queue}")
    public void receiveMessage(@Valid @Payload List<RabbitLocationData> theRabbitLocationData) {
        try {
            locationDataRepository.saveAll(
                    theRabbitLocationData.stream().map(l -> new DBLocationData(l)).collect(Collectors.toList())
            );
        } catch ( Exception e) {
            logger.error(e.getMessage());
        }
    }
}