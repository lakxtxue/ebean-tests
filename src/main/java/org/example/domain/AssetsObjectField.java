package org.example.domain;

import javax.persistence.*;

/**
 *
 */
@Entity(name = "AssetsObjectField")
@Table(name = "assets_object_field")
@IdClass(AssetsObjectFieldId.class)
public class AssetsObjectField extends BaseModel {

    @Id
    @Column(name = "TENANT_ID")
    private Long tenantId;


    @Id
    @Column(name = "SOURCE_ID")
    private Long sourceId;


    @Id
    @Column(name = "FULL_FIELD_NAME")
    private String fullFieldName;

    @Column(name = "FULL_OBJECT_NAME")
    private String fullObjectName;

    @Id
    @ManyToOne
    @JoinColumns({
            @JoinColumn(name = "TENANT_ID", referencedColumnName = "TENANT_ID"),
            @JoinColumn(name = "SOURCE_ID", referencedColumnName = "SOURCE_ID")
    })
    private AssetsObject assetsObject;

    @Column(name = "FIELD_NAME", length = 32, nullable = false)
    private String fieldName;

    @Column(name = "SHORT_NAME", length = 32, nullable = false)
    private String shortName;


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

    public AssetsObject getAssetsObject() {
        return assetsObject;
    }

    public void setAssetsObject(AssetsObject assetsObject) {
        this.assetsObject = assetsObject;
    }

    public String getFullFieldName() {
        return fullFieldName;
    }

    public void setFullFieldName(String fullFieldName) {
        this.fullFieldName = fullFieldName;
    }

    String getFieldName() {
        return this.fieldName;
    }

    void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    String getShortName() {
        return this.shortName;
    }

    void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullObjectName() {
        return fullObjectName;
    }

    public void setFullObjectName(String fullObjectName) {
        this.fullObjectName = fullObjectName;
    }
}
