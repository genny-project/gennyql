package life.genny.channels;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
//import org.kie.api.KieBase;
//import org.kie.api.KieBaseConfiguration;
//import org.kie.api.KieServices;
//import org.kie.api.builder.KieBuilder;
//import org.kie.api.builder.KieFileSystem;
//import org.kie.api.builder.Message;
//import org.kie.api.io.ResourceType;
//import org.kie.api.runtime.KieContainer;
//import org.kie.api.runtime.KieSession;
import java.io.StringReader;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import io.vavr.Tuple2;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.rxjava.core.Vertx;
import io.vertx.rxjava.core.eventbus.EventBus;
import life.genny.qwanda.Answer;
import life.genny.qwanda.Ask;
import life.genny.qwanda.message.QDataAnswerMessage;
import life.genny.qwanda.message.QDataAskMessage;
import life.genny.qwanda.message.QDataRuleMessage;
import life.genny.qwanda.message.QEventMessage;
import life.genny.qwanda.rule.Rule;
import life.genny.qwandautils.KeycloakUtils;
import life.genny.service.ClientServices;
//import life.genny.service.KieClient;

public class EBCHandlers {

  private static final Logger logger = LoggerFactory.getLogger(EBCHandlers.class);

//  static Map<String, KieBase> kieBaseCache = null;
//  static {
//    kieBaseCache = new HashMap<String, KieBase>();
//  }

  static Gson gson = new GsonBuilder()
      .registerTypeAdapter(LocalDateTime.class, new JsonDeserializer<LocalDateTime>() {
        @Override
        public LocalDateTime deserialize(final JsonElement json, final Type type,
            final JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
          return ZonedDateTime.parse(json.getAsJsonPrimitive().getAsString()).toLocalDateTime();
        }

        public JsonElement serialize(final LocalDateTime date, final Type typeOfSrc,
            final JsonSerializationContext context) {
          return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME)); // "yyyy-mm-dd"
        }
      }).create();

//  static KieServices ks = KieServices.Factory.get();
//  static KieContainer kContainer;
  final static String qwandaApiUrl = System.getenv("REACT_APP_QWANDA_API_URL");
  final static String vertxUrl = System.getenv("REACT_APP_VERTX_URL");
  final static String hostIp = System.getenv("HOSTIP");
//  static KieSession kSession;
  static String token;



  public static void registerHandlers() {
    EBConsumers.getFromEvents().handler(arg -> {
      System.out.println("****************************\n\n\n***********************************");
      final JsonObject payload = new JsonObject(arg.body().toString());
      final String token = payload.getString("token");
      System.out.println(payload);
//      ClientServices.getService().fireRule(payload);
      System.out.println("&&&&&&&&&&&&&&&&&&&&&&&&&&&&\n\n\n***********************************");
    });

    EBConsumers.getFromData().handler(arg -> {

      logger.info("Received DATA :" + (System.getenv("PROJECT_REALM") == null ? "tokenRealm"
          : System.getenv("PROJECT_REALM")));
      final JsonObject payload = new JsonObject(arg.body().toString());
      payload.getString("token");
      System.out.println(payload);

      // final JsonObject a = Buffer.buffer(payload.toString()).toJsonObject();
      if (payload.getString("msg_type").equalsIgnoreCase("DATA_MSG")) {
        if (payload.getString("data_type").equals(Rule.class.getSimpleName())) {
          gson.fromJson(payload.toString(), QDataRuleMessage.class);
        } else {
//          allRules(payload, eventBus);
          
        }
      }
    });
  }

}
