package com.kb.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rdyyak on 20.05.15.
 */
@Entity
@Table(name = "T_PRODUCT_HISTORY")
public class ProductHistory extends AbstractAuditingEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "changed_field")
    private String changedField;

    @Column(name = "old_value")
    private String oldValue;

    @Column(name = "new_value")
    private String newValue;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChangedField() {
        return changedField;
    }

    public void setChangedField(String changedField) {
        this.changedField = changedField;
    }

    public String getOldValue() {
        return oldValue;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return newValue;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductHistory{" +
            "id=" + id +
            ", changedField='" + changedField + '\'' +
            ", oldValue='" + oldValue + '\'' +
            ", newValue='" + newValue + '\'' +
            ", product=" + product +
            '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ProductHistory that = (ProductHistory) o;

        if (changedField != null ? !changedField.equals(that.changedField) : that.changedField != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (newValue != null ? !newValue.equals(that.newValue) : that.newValue != null) return false;
        if (oldValue != null ? !oldValue.equals(that.oldValue) : that.oldValue != null) return false;
        if (product != null ? !product.equals(that.product) : that.product != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (changedField != null ? changedField.hashCode() : 0);
        result = 31 * result + (oldValue != null ? oldValue.hashCode() : 0);
        result = 31 * result + (newValue != null ? newValue.hashCode() : 0);
        result = 31 * result + (product != null ? product.hashCode() : 0);
        return result;
    }

}
