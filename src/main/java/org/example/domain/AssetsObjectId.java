package org.example.domain;

import javax.persistence.AttributeOverride;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.util.Objects;

@Embeddable
public class AssetsObjectId {
  @AttributeOverride(name = "tenantId", column = @Column(name = "TENANT_ID"))
  @Column(name = "TENANT_ID")
  private Long tenantId;

  @AttributeOverride(name = "sourceId", column = @Column(name = "SOURCE_ID"))
  @Column(name = "SOURCE_ID")
  private Long sourceId;

  @AttributeOverride(name = "fullObjectName", column = @Column(name = "FULL_OBJECT_NAME"))
  @Column(name = "FULL_OBJECT_NAME")
  private String fullObjectName;

  Long getTenantId() {
    return this.tenantId;
  }

  void setTenantId(Long tenantId) {
    this.tenantId=tenantId;
  }

  Long getSourceId() {
    return this.sourceId;
  }

  void setSourceId(Long sourceId) {
    this.sourceId=sourceId;
  }

  String getFullObjectName() {
    return this.fullObjectName;
  }

  void setFullObjectName(String fullObjectName) {
    this.fullObjectName=fullObjectName;
  }

  @Override
  public int hashCode() {
    int result = 17 * 31;
    result = 31 * result + Objects.hashCode(tenantId);
    result = 31 * result + Objects.hashCode(sourceId);
    result = 31 * result + Objects.hashCode(fullObjectName);
    return result;
  }

  @Override
  public boolean equals(Object that) {
    if (this == that) {
      return true;
    }
    if (!(that instanceof AssetsObjectId)) {
      return false;
    }
    AssetsObjectId other = (AssetsObjectId) that;
    if (!Objects.equals(tenantId, other.tenantId)) return false;
    if (!Objects.equals(sourceId, other.sourceId)) return false;
    if (!Objects.equals(fullObjectName, other.fullObjectName)) return false;
    return true;
  }
}
