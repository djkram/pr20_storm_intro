package org.bdigital;

import org.apache.log4j.BasicConfigurator;

import backtype.storm.Config;
import backtype.storm.LocalCluster;
import backtype.storm.topology.TopologyBuilder;

public class Main00 {

    public static void main(String[] args) {
	BasicConfigurator.configure();

	Config conf = new Config();
	
	TopologyBuilder builder = new TopologyBuilder();
	builder.setSpout("tweetSpout", // ID
		new FakeTweetSpout(), // Tipus
		1 // # d'instancies - cardinalitat
		);
	builder.setBolt("echoBolt", // ID
		new EchoConsoleBolt(), // Tipus
		1) // Cardinalitat
		.allGrouping("tweetSpout"); // origen de les tuples
	
	LocalCluster cluster = new LocalCluster(); // Cluster de development
	cluster.submitTopology("tweetteater", conf, builder.createTopology());
	
    }

}
