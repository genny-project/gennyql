package life.genny.service;

import java.util.List;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.ext.web.Router;
import life.genny.handlers.RouterHandlers;

public class GraphQLRouter {

	private static int serverPort = 8084;
	
	protected static void routers(Vertx vertx) {
		Router router = Router.router(vertx);
		router.route().handler(RouterHandlers.cors());
		router.route(HttpMethod.POST, "/graphql").handler(RouterHandlers::apiHandler);
		vertx.createHttpServer().requestHandler(router::accept).listen(serverPort);
	}
}


//JsonObject obj = new JsonObject();
//obj.put("input", "{ name:\"andresssss\", code:\"colombia\" }");
// System.out.println(obj.);
// Map<String,Object> params = new HashMap<String, Object>();
// params.put("input", "{ name:\"andresssss\", code:\"colombia\" }");
//ExecutionInput executionInput = ExecutionInput.newExecutionInput()
//		.query("mutation CreateMessage($input: InBase){ base (code: $input){ name, code } }")
//		.variables(params).build();
