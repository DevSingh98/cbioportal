package org.cbioportal.legacy.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class ExpressionEnrichment implements Serializable {

  @NotNull private List<GroupStatistics> groupsStatistics;
  @NotNull private BigDecimal pValue;

  public List<GroupStatistics> getGroupsStatistics() {
    return groupsStatistics;
  }

  public void setGroupsStatistics(List<GroupStatistics> groupsStatistics) {
    this.groupsStatistics = groupsStatistics;
  }

  @JsonProperty("pValue")
  public BigDecimal getpValue() {
    return pValue;
  }

  public void setpValue(BigDecimal pValue) {
    this.pValue = pValue;
  }
}
