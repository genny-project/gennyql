package life.genny.cluster;

import graphql.schema.idl.TypeDefinitionRegistry;
import io.vertx.core.AsyncResult;
import io.vertx.core.Future;
import io.vertx.core.Handler;
import io.vertx.core.Vertx;
import io.vertx.core.eventbus.EventBus;
import life.genny.channels.EBCHandlers;
import life.genny.channels.EBConsumers;
import life.genny.channels.EBProducers;
import life.genny.service.ClientServices;
import life.genny.service.GraphQLRouter;
//import life.genny.service.KieService;
//import life.genny.service.KieServiceImpl;

public class Cluster {

	static Handler<AsyncResult<Vertx>> registerAllChannels = vertx -> {
//	    ClientServices.setService(vertx.result());
		EventBus eb = vertx.result().eventBus();
		EBConsumers.registerAllConsumer(eb);
		EBProducers.registerAllProducers(eb);
		EBCHandlers.registerHandlers();
		
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
		System.out.println(fut.isComplete());
		return fut;
	}

}
