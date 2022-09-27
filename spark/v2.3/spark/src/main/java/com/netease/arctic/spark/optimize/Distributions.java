package com.netease.arctic.spark.optimize;

import org.apache.spark.annotation.Experimental;

@Experimental
public class Distributions {
  private Distributions() {
  }

  public static ClusteredDistribution clustered(Expression[] clustering) {
    return new ClusterDistributionImpl(clustering);
  }

}
