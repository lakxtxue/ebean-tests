package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.Cache;

import javax.persistence.*;

@Entity(name = "ConfCpCon")
@Table(name = "CONF_CP_CON")
@Cache
public class ConfCpCon extends Model {

    @Id
    @Column(name="ID",nullable=false,length=32)
    private String id;

    @Column(name="ENABLED",nullable=false,length=1)
    private String enabled;

    @ManyToOne
    @JoinColumn(name="CP_ID",referencedColumnName="CP_ID")
    private ConfCp confCp;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEnabled() {
        return enabled;
    }

    public void setEnabled(String enabled) {
        this.enabled = enabled;
    }

    public ConfCp getConfCp() {
        return confCp;
    }

    public void setConfCp(ConfCp confCp) {
        this.confCp = confCp;
    }
}