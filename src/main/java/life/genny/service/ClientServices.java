package life.genny.service;

import io.vertx.core.Vertx;

public class ClientServices {
	
	private static SomeDatabaseService service;

	/**
	 * @return the service
	 */
	public static SomeDatabaseService getService() {
		return service;
	}

	/**
	 * @param service the service to set
	 */
	public static void setService(Vertx vertx) {
		ClientServices.service = SomeDatabaseService.createProxy(vertx, "database-service-address");
	}
	
}
