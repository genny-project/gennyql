package life.genny.resolvers;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.config.NetworkConfig;
// import com.hazelcast.client.HazelcastClient;
// import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.core.ClientService;
import com.hazelcast.core.EntryView;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import graphql.schema.DataFetcher;
import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;
import graphql.Scalars;
import life.genny.cluster.Hazel;
import life.genny.qwanda.entity.BaseEntity;
import life.genny.qwandautils.JsonUtils;
// import life.genny.qwanda.entity.BaseEntity;
import life.genny.service.ClientServices;

public class QwandaResolver {

  public static DataFetcher baseEntityDataFetcher = environment -> {
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().save(i -> {
      fut.complete(i.result().getMap());
    });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  // public static DataFetcher<BaseEntity> inFooDataFetcher = environment -> {
  static Gson gson = new Gson();
  // BaseEntity base = gson.fromJson(environment.getArgument("code").toString(), BaseEntity.class);
  // return base;
  // };

  public static DataFetcher activeNodeInstancesFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().findActiveNodeInstances((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (int) args.get("page"), (int) args.get("pageSize"),
        res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher completedNodeInstancesFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().findCompletedNodeInstances((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (int) args.get("page"), (int) args.get("pageSize"),
        res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher findNodeInstancesFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().findNodeInstances((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (int) args.get("page"), (int) args.get("pageSize"),
        res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher findVariableHistoryFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().findVariableHistory((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (String) args.get("variableName"),
        (int) args.get("page"), (int) args.get("pageSize"), res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher findVariablesCurrentStateFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().findVariablesCurrentState((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getProcessDefinitionFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getProcessDefinition((String) args.get("containerId"),
        (String) args.get("processId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getProcessInstanceFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getProcessInstance((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getServiceTaskDefinitionsFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getServiceTaskDefinitions((String) args.get("containerId"),
        (String) args.get("processId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getUserTaskDefinitionsFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getUserTaskDefinitions((String) args.get("containerId"),
        (String) args.get("processId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getAssociatedEntityDefinitionsFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getAssociatedEntityDefinitions((String) args.get("containerId"),
        (String) args.get("processId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getWorkItemFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getWorkItem((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (Long) args.get("id"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher startProcessFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().startProcess((String) args.get("containerId"),
        (String) args.get("processId"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher startProcessWithVarsFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().startProcessWithVars((String) args.get("containerId"),
        (String) args.get("processId"), (JsonObject) args.get("variables"), res -> {
          fut.complete(res.result().getMap());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher completeWorkItemFetcher = env -> {
    Map args = env.getArguments();
    Map map = (Map) args.get("results");
    JsonObject ob = new JsonObject().mapFrom(args.get("results"));
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().completeWorkItem((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), (Long) args.get("id"), ob, res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getWorkItemByProcessInstanceFetcher = env -> {
    Map args = env.getArguments();
    CompletableFuture fut = new CompletableFuture<>();
    ClientServices.getService().getWorkItemByProcessInstance((String) args.get("containerId"),
        (Long) args.get("processInstanceId"), res -> {
          fut.complete(res.result().getList());
        });
    try {
      fut.get();
    } catch (InterruptedException | ExecutionException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }
    return fut;
  };

  public static DataFetcher getBaseEntity = env -> {
    Map args = env.getArguments();
    HazelcastInstance client = Hazel.getHazel().getClientInstance();
    IMap map = client.getMap("BaseEntitys");
    System.out.println("baseEntity with code" +  map.get(args.get("code")));
    BaseEntity be = JsonUtils.fromJson((String) map.get(args.get("code")), BaseEntity.class);
    return be;
  };
  
}
