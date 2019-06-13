package com.rls.hs.consumer;

import com.rls.hs.models.LocationData;
import com.rls.hs.repositories.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;
import javax.validation.Valid;

@Component
public class RabbitMQConsumer {
    private final LocationDataRepository locationDataRepository;

    @Autowired
    public RabbitMQConsumer(LocationDataRepository theLocationDataRepository) {
        this.locationDataRepository = theLocationDataRepository;
    }

    static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);
    @RabbitListener(queues = "${application.rabbitmq.queue}")
    public void receiveMessage(@Valid @Payload LocationData theLocationData) {
        try {
            logger.info("Saving location data into db: " + theLocationData.toString());
            locationDataRepository.save(theLocationData);
        } catch ( Exception e) {
            logger.error(e.getMessage());
        }
    }
}