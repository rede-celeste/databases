package com.celeste.databases.messenger.model.database.dao;

import com.celeste.databases.core.model.database.dao.Dao;
import com.celeste.databases.core.model.database.provider.exception.FailedConnectionException;
import com.celeste.databases.messenger.model.entity.Listener;
import java.util.Map.Entry;

public interface MessengerDao extends Dao {

  void publish(final String channel, final String message) throws FailedConnectionException;

  void subscribe(final String channel, final Listener listener) throws FailedConnectionException;

  void subscribeAll(final String prefix, final Class<?> clazz, final Object instance)
      throws FailedConnectionException;

  @SuppressWarnings("unchecked")
  void subscribeAll(final String prefix, final Entry<Class<?>, Object>... entries)
      throws FailedConnectionException;

}
