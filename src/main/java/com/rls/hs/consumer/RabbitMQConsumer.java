package com.rls.hs.consumer;

import com.rls.hs.models.DBLocationData;
import com.rls.hs.models.RabbitLocationData;
import com.rls.hs.repositories.LocationDataRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class RabbitMQConsumer {
    private final LocationDataRepository locationDataRepository;
    private List<RabbitLocationData> myRabbitLocationData = new ArrayList<>();

    @Autowired
    public RabbitMQConsumer(LocationDataRepository theLocationDataRepository) {
        this.locationDataRepository = theLocationDataRepository;
    }

    static final Logger logger = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "${application.rabbitmq.queue}")
    public void receiveMessage(@Valid @Payload List<RabbitLocationData> theRabbitLocationData) {
        try {
            myRabbitLocationData.addAll(theRabbitLocationData);
        } catch ( Exception e) {
            logger.error(e.getMessage());
        }
    }

    @Scheduled(fixedDelayString = "${application.mysql.write.delay:1000}")
    private void saveToDb() {
        if (myRabbitLocationData.size() == 0) {
            return;
        }
        try {
            logger.debug("Saving To DB: {} entries", myRabbitLocationData.size());
            long startTime = System.currentTimeMillis();

            locationDataRepository.saveAll(
                    myRabbitLocationData.stream().map(DBLocationData::new).collect(Collectors.toList())
            );
            long endTime = System.currentTimeMillis();

            logger.debug("Time taken to save: {}ms", (endTime - startTime));
        } catch (Exception e) {
            logger.error(e.getMessage());
        } finally {
            myRabbitLocationData.clear();
        }
    }
}