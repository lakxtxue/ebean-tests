package org.example.domain;

import javax.persistence.*;


@Entity(name = "AssetsObject")
@Table(name = "assets_object")
@IdClass(AssetsObjectId.class)
public class AssetsObject extends BaseModel {

    @Id
    @Column(name = "TENANT_ID")
    private Long tenantId;


    @Id
    @Column(name = "SOURCE_ID")
    private Long sourceId;


    @Id
    @Column(name = "FULL_OBJECT_NAME")
    private String fullObjectName;


    @Column(name = "OBJECT_NAME", length = 32, nullable = false)
    private String objectName;


    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public String getFullObjectName() {
        return fullObjectName;
    }

    public void setFullObjectName(String fullObjectName) {
        this.fullObjectName = fullObjectName;
    }

    String getObjectName() {
        return this.objectName;
    }

    void setObjectName(String objectName) {
        this.objectName = objectName;
    }

}
