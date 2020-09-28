package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.*;
import java.util.List;

@Entity(name = "Config")
@Table(name = "CONFIG")
@Cache
public class Config extends Model {

    @Id
    @Column(name="CONFIG_ID",nullable=false,length=32)
    private String configId;

    @Column(name="CONFIG_NAME",nullable=false,length=1)
    private volatile String configName;


    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="CONFIG_ID",referencedColumnName="CONFIG_ID")
    @OrderBy("displayName ASC")
    private List<ConfCp> confCpList;

    public String getConfigId() {
        return configId;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return configName;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public List<ConfCp> getConfCpList() {
        return confCpList;
    }

    public void setConfCpList(List<ConfCp> confCpList) {
        this.confCpList = confCpList;
    }
}