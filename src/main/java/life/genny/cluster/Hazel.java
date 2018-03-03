package life.genny.cluster;

import java.util.ArrayList;
import java.util.List;
import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientNetworkConfig;
import com.hazelcast.core.HazelcastInstance;
import life.genny.service.KieClient;

public class Hazel {
  private HazelcastInstance clientInstance;
  private static Hazel hazel;

  /**
   * @param client the client to set
   */
  public HazelcastInstance getClientInstance() {
    return this.clientInstance;
  }

  public static Hazel getHazel() {
    
    if (hazel == null) {
 
      hazel = new Hazel();
    }
    return hazel;
  }

  public Hazel() {
    ClientConfig clientConfig = new ClientConfig();
    clientConfig.setInstanceName("gennyql");
    clientConfig.getGroupConfig().setName("clusterwidemap");
    clientConfig.getGroupConfig().setPassword("cluster");
    ClientNetworkConfig network = clientConfig.getNetworkConfig();
    List<String> addresses =  new ArrayList<String>();
    addresses.add("qwanda-service");
    addresses.add("localhost");
    network.setAddresses(addresses);
    this.clientInstance = HazelcastClient.newHazelcastClient(clientConfig);
  }
  


}
