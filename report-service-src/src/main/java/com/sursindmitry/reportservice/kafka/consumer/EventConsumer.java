package com.sursindmitry.reportservice.kafka.consumer;

/**
 * Общий интерфейс консьюмеров.
 *
 * @param <E> евент кафки
 */
public interface EventConsumer<E> {

    void handle(E event);
}
