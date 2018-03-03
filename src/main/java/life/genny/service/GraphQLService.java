package life.genny.service;

import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.core.Future;
import io.vertx.core.Vertx;
import life.genny.resolvers.QwandaResolver;
import life.genny.scalar.CustomScalars;
import life.genny.utils.Utils;

public class GraphQLService {

  private static GraphQL graphQL;

  /**
   * @return the graphQL
   */
  public static GraphQL getGraphQL() {
    System.out.println(graphQL);
    return graphQL;
  }

  /**
   * @param graphQL the graphQL to set
   */
  public static void setGraphQL(GraphQL graphQL) {
    GraphQLService.graphQL = graphQL;
  }

  static RuntimeWiring buildRuntimeWiring() {
		return RuntimeWiring.newRuntimeWiring()
				.scalar(CustomScalars.CustGQLMap)
				.type("QueryType",
						typeWiring -> typeWiring
							.dataFetcher("baseEntity", QwandaResolver.baseEntityDataFetcher)
							.dataFetcher("baseEntityByCode", QwandaResolver.baseEntityDataFetcher)
							.dataFetcher("findActiveNodeInstances", QwandaResolver.activeNodeInstancesFetcher)
							.dataFetcher("findCompletedNodeInstances", QwandaResolver.completedNodeInstancesFetcher)
							.dataFetcher("findNodeInstances", QwandaResolver.completedNodeInstancesFetcher)
							.dataFetcher("findVariablesCurrentState", QwandaResolver.findVariablesCurrentStateFetcher)
							.dataFetcher("findVariableHistory", QwandaResolver.findVariableHistoryFetcher)
							.dataFetcher("getProcessDefinition", QwandaResolver.getProcessDefinitionFetcher)
							.dataFetcher("getProcessInstance", QwandaResolver.getProcessInstanceFetcher)
							.dataFetcher("getServiceTaskDefinitions", QwandaResolver.getServiceTaskDefinitionsFetcher)
							.dataFetcher("getUserTaskDefinitions", QwandaResolver.getUserTaskDefinitionsFetcher)
							.dataFetcher("getAssociatedEntityDefinitions", QwandaResolver.getAssociatedEntityDefinitionsFetcher)
							.dataFetcher("getWorkItem", QwandaResolver.getWorkItemFetcher)
							.dataFetcher("getWorkItemByProcessInstance", QwandaResolver.getWorkItemByProcessInstanceFetcher)
							.dataFetcher("getbaseEntityByCode", QwandaResolver.getBaseEntity))
				.type("Mutation", 
						typeWiring -> typeWiring
//							.dataFetcher("baseentity", QwandaResolver.inFooDataFetcher)
							.dataFetcher("startProcess", QwandaResolver.startProcessFetcher)
							.dataFetcher("startProcessWithVars", QwandaResolver.startProcessWithVarsFetcher)
							.dataFetcher("completeWorkItem", QwandaResolver.completeWorkItemFetcher))
				.build();
	}

  public static Future<TypeDefinitionRegistry> prepareQLServer() {
    Future<TypeDefinitionRegistry> fut = Future.future();
    SchemaParser schemaParser = new SchemaParser();
    TypeDefinitionRegistry typeRegistry = new TypeDefinitionRegistry();
    Utils.recursiveFilesReader("schemas").forEach(file -> {
      System.out.println(file.toString());
      typeRegistry.merge(schemaParser.parse(file.toString()));
    });
    fut.tryComplete(typeRegistry);
    return fut;
  }

  public static void startQLServer(TypeDefinitionRegistry typeRegistry) {
    SchemaGenerator schemaGenerator = new SchemaGenerator();
    RuntimeWiring wiring = buildRuntimeWiring();
    GraphQLSchema graphQLSchema = schemaGenerator.makeExecutableSchema(typeRegistry, wiring);
    graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    System.out.println(graphQL);
    setGraphQL(graphQL);
  }
}
