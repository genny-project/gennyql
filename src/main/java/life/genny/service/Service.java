package life.genny.service;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import life.genny.cluster.Cluster;

public class Service extends AbstractVerticle {

	@Override
	public void start() {
		GraphQLService.prepareQLServer().compose(typeRegistry->{
			Future<Void> fut = Future.future();
			Cluster.joinCluster(vertx).compose(k->{
				Future<Void> fut2 = Future.future();
				GraphQLService.startQLServer(typeRegistry);
				GraphQLRouter.routers(vertx);
				fut2.complete();
				return fut2;
			});
			fut.complete();
			return fut;
		});
	}
}
