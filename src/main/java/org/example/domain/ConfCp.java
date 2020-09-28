package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.Cache;
import javax.persistence.*;
import java.util.List;

@Entity(name = "ConfCp")
@Table(name = "CONF_CP")
@Cache
public class ConfCp extends Model {

    @Id
    @Column(name="CP_ID",nullable=false,length=32)
    private String id;


    @Column(name="DISPLAY_NAME",nullable=false,length=1)
    private volatile String displayName;

    @OneToMany(cascade=CascadeType.ALL, orphanRemoval=true)
    @JoinColumn(name="CP_ID",referencedColumnName="CP_ID")
    private List<ConfCpCon> conList;

    @ManyToOne
    @JoinColumn(name="CONFIG_ID",referencedColumnName="CONFIG_ID")
    private Config config;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public Config getConfig() {
        return config;
    }

    public void setConfig(Config config) {
        this.config = config;
    }

    public List<ConfCpCon> getConList() {
        return conList;
    }

    public void setConList(List<ConfCpCon> conList) {
        this.conList = conList;
    }
}