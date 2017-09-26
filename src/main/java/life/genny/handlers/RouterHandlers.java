package life.genny.handlers;

import java.util.Map;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.json.Json;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.CorsHandler;
import life.genny.service.GraphQLService;

public class RouterHandlers {

	static Object data = null;
	static ExecutionInput executionInput;
	public static CorsHandler cors() {
		return CorsHandler.create("*").allowedMethod(HttpMethod.GET).allowedMethod(HttpMethod.POST)
				.allowedMethod(HttpMethod.OPTIONS).allowedHeader("X-PINGARUNER").allowedHeader("Content-Type")
				.allowedHeader("X-Requested-With");
	}

	public static void apiHandler(RoutingContext routingContext) {
		routingContext.request().bodyHandler(body -> {
				String query = body.toJsonObject().getString("query");
				try {
					Map<String, Object> variables = body.toJsonObject().getJsonObject("variables").getMap();
					executionInput = ExecutionInput.newExecutionInput().query(query).variables(variables).build();
				}catch(NullPointerException e) {
					executionInput = ExecutionInput.newExecutionInput().query(query).build();
				}
				Vertx.vertx().executeBlocking(blocking->{
					ExecutionResult executionResult = GraphQLService.getGraphQL().execute(executionInput);
					data = executionResult.getData();
					System.out.println(data+"+++++++++++++\n\n");
					routingContext.response().end(Json.encodePrettily(data));
				}, result->{
					
				});
		});	
	}
}
