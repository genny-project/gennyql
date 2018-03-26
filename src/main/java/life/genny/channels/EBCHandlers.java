package life.genny.channels;

import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import life.genny.qwanda.message.QDataRuleMessage;
import life.genny.qwanda.rule.Rule;
import life.genny.qwandautils.JsonUtils;

public class EBCHandlers {

  private static final Logger logger = LoggerFactory.getLogger(EBCHandlers.class);



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
          JsonUtils.fromJson(payload.toString(), QDataRuleMessage.class);
        } else {
//          allRules(payload, eventBus);
          
        }
      }
    });
  }

}
