package com.sursindmitry.reportservice.kafka.handler;

/**
 * Общий интерфейс хендлеров кафки.
 *
 * @param <T> объект приходящий в хендлер из консьюмера
 */
public interface EventHandler<T> {
    boolean isHandle(T t);

    void handle(T t);
}
