package org.cbioportal.legacy.model;

import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

public class GroupStatistics implements Serializable {

  @NotNull private String name;
  @NotNull private BigDecimal meanExpression;
  @NotNull private BigDecimal standardDeviation;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public BigDecimal getMeanExpression() {
    return meanExpression;
  }

  public void setMeanExpression(BigDecimal meanExpression) {
    this.meanExpression = meanExpression;
  }

  public BigDecimal getStandardDeviation() {
    return standardDeviation;
  }

  public void setStandardDeviation(BigDecimal standardDeviation) {
    this.standardDeviation = standardDeviation;
  }
}
