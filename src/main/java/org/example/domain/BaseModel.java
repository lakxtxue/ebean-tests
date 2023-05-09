package org.example.domain;

import io.ebean.Model;
import io.ebean.annotation.WhenCreated;
import io.ebean.annotation.WhenModified;
import io.ebean.annotation.WhoCreated;
import io.ebean.annotation.WhoModified;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.util.Date;


@MappedSuperclass
public abstract class BaseModel extends Model {

    @Column(name = "CREATOR", length = 32)
    @WhoCreated
    protected String creator;

    @Column(name = "MODIFIER", length = 32)
    @WhoModified
    protected String modifier;


    @Column(name = "CREATED_STAMP", length = 26)
    @WhenCreated
    protected Date createdStamp;

    @Column(name = "LAST_UPDATED_STAMP", length = 26)
    @WhenModified
    protected Date lastUpdatedStamp;

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public Date getCreatedStamp() {
        return createdStamp;
    }

    public void setCreatedStamp(Date createdStamp) {
        this.createdStamp = createdStamp;
    }

    public Date getLastUpdatedStamp() {
        return lastUpdatedStamp;
    }

    public void setLastUpdatedStamp(Date lastUpdatedStamp) {
        this.lastUpdatedStamp = lastUpdatedStamp;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }
}
