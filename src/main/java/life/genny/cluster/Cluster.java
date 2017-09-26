package life.genny.cluster;

import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import life.genny.service.ClientServices;
import life.genny.service.GraphQLRouter;
import life.genny.service.SomeDatabaseService;
import life.genny.service.SomeDatabaseServiceImpl;

public class Cluster {

	static Handler<AsyncResult<Vertx>> registerAllChannels = vertx -> {
//		EventBus eb = vertx.result();
//		EBConsumers.registerAllConsumer(eb);
//		EBProducers.registerAllProducers(eb);
//		EBCHandlers.registerHandlers();
		ClientServices.setService(vertx.result());
//		ClientServices.getService().save(i->{
//			System.out.println(i.result());
//		});
//		GraphQLHandler.maini();
//		vertx.result().setPeriodic(1000, id -> {
//			ClientServices.getService().save("", null, a->a.cause());
//		});
	};

	public static Future<Void> joinCluster(Vertx vertx) {
		Future<Void> fut = Future.future();
		vertx.factory.clusteredVertx(ClusterConfig.configCluster(),registerAllChannels);
		fut.complete();
		return fut;
	}

}
