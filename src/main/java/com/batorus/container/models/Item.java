package com.batorus.container.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "items")
public class Item extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "itemName may not be null!")
    @Size(min = 2, max = 100, message = "itemName must be between {min} and {max} characters long")
    @Column(unique = true)
    private String itemName;

    @NotNull(message = "itemCode may not be null!")
    @Size(min = 2, max = 60, message = "itemCode must be between {min} and {max} characters long")
    @Column(unique = true)
    private String itemCode;

    @NotNull(message = "itemDescription may not be null!")
    @Size(min = 2, max = 250, message = "itemDescription must be between {min} and {max} characters long")
    private String itemDescription;

    private float itemPrice;

    @Column(columnDefinition = "integer default 1")
    private int enabled = 1;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "container_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Container container;

    public Container getContainer() {
        return container;
    }

    public void setContainer(Container container) {
        this.container = container;
    }

    public Item() {
    }

    public Item(String itemName, String itemCode, String itemDescription, float itemPrice) {
        this.itemName = itemName;
        this.itemCode = itemCode;
        this.itemDescription = itemDescription;
        this.itemPrice = itemPrice;
    }

    public Long getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public float getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(float itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
