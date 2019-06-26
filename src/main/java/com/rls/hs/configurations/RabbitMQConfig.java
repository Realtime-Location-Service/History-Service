package com.rls.hs.configurations;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListenerConfigurer;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.RabbitListenerEndpointRegistrar;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.messaging.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.handler.annotation.support.DefaultMessageHandlerMethodFactory;
import org.springframework.messaging.handler.annotation.support.MessageHandlerMethodFactory;
import org.springframework.validation.SmartValidator;

@Configuration
public class RabbitMQConfig implements RabbitListenerConfigurer {

    @Value("${application.rabbitmq.queue}")
    private String QUEUE_LOCATION_DATA;
    @Value("${application.rabbitmq.data-exchange}")
    private String EXCHANGE_LOCATION_DATA;
    @Value("${application.rabbitmq.dead-queue}")
    private String QUEUE_DEAD_LOCATION_DATA;
    @Value("${application.rabbitmq.message-ttl}")
    private int QUEUE_MESSAGE_TTL;

    @Autowired
    SmartValidator validator;

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory) {
        final RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(producerJackson2MessageConverter());
        return rabbitTemplate;
    }

    @Bean
    @Primary
    Queue locationDataQueue() {
        return QueueBuilder.durable(QUEUE_LOCATION_DATA)
                .withArgument("x-message-ttl", QUEUE_MESSAGE_TTL)
                .build();
    }


    @Bean
    Queue deadLetterQueue() {
        return QueueBuilder.durable(QUEUE_DEAD_LOCATION_DATA).build();
    }

    @Bean
    Exchange locationDataExchange() {
        return ExchangeBuilder.directExchange(EXCHANGE_LOCATION_DATA).build();
    }

    @Bean
    Binding binding(Queue theQueue, DirectExchange locationDataExchange) {
        return BindingBuilder.bind(theQueue).to(locationDataExchange).with(QUEUE_LOCATION_DATA);
    }

    @Bean
    public Jackson2JsonMessageConverter producerJackson2MessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public void configureRabbitListeners(RabbitListenerEndpointRegistrar rabbitListenerEndpointRegistrar) {
        rabbitListenerEndpointRegistrar.setMessageHandlerMethodFactory(messageHandlerMethodFactory());
    }

    @Bean
    MessageHandlerMethodFactory messageHandlerMethodFactory() {
        DefaultMessageHandlerMethodFactory messageHandlerMethodFactory = new DefaultMessageHandlerMethodFactory();
        messageHandlerMethodFactory.setMessageConverter(consumerJackson2MessageConverter());
        messageHandlerMethodFactory.setValidator(this.validator);
        return messageHandlerMethodFactory;
    }

    @Bean
    public MappingJackson2MessageConverter consumerJackson2MessageConverter() {
        return new MappingJackson2MessageConverter();
    }

}
