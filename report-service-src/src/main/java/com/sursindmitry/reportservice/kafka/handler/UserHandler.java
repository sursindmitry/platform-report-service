package com.sursindmitry.reportservice.kafka.handler;

/**
 * Общий интерфейс {@link com.sursindmitry.commonmodels.kafka.UserEvent}.
 *
 * @param <T> объект приходящий в хендлер из консьюмера
 */
public interface UserHandler<T> extends EventHandler<T> {
}
