package life.genny.channels;

import io.vertx.core.eventbus.EventBus;
import io.vertx.core.eventbus.Message;
import io.vertx.core.eventbus.MessageConsumer;
import io.vertx.core.Vertx;

public class EBConsumers {

  private static MessageConsumer<Object> fromEvents;
  private static MessageConsumer<Object> fromData;


  public static void registerAllConsumer(EventBus eb) {
    setFromData(eb.consumer("data"));
    setFromEvents(eb.consumer("events"));
  }


  /**
   * @return the fromEvents
   */
  public static MessageConsumer<Object> getFromEvents() {
    return fromEvents;
  }


  /**
   * @param fromEvents the fromEvents to set
   */
  public static void setFromEvents(MessageConsumer<Object> fromEvents) {
    EBConsumers.fromEvents = fromEvents;
  }


  /**
   * @return the fromData
   */
  public static MessageConsumer<Object> getFromData() {
    return fromData;
  }


  /**
   * @param fromData the fromData to set
   */
  public static void setFromData(MessageConsumer<Object> fromData) {
    EBConsumers.fromData = fromData;
  }




}
