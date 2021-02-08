package com.database.table;

import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "almonds")
@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
public class AlmondsTbl extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 8906230891732131915L;

    @Column(name = "index",  nullable = false, length = 32)
    String index;

    @Type(type = "jsonb")
    @Column(name = "index_one_hot",  columnDefinition = "jsonb", nullable = false)
    List<Integer> indexOneHot;

    @Column(name = "title",  nullable = false, length = 32)
    String title;

    public String getIndex() {
        return index;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public List<Integer> getIndexOneHot() {
        return indexOneHot;
    }

    public void setIndexOneHot(List<Integer> indexOneHot) {
        this.indexOneHot = indexOneHot;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}