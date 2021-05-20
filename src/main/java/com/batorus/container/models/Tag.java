package com.batorus.container.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.NaturalId;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "tags")
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "tagName may not be null!")
    @Size(min = 2, max = 250, message = "tagName must be between {min} and {max} characters long")
    @Column(unique = true)
    private String tagName;

    //############ Start M-to-M inverse side
    //many tags belong to many posts
    //################################
    @ManyToMany(fetch = FetchType.LAZY,
                        cascade = {
                            CascadeType.PERSIST,
                            CascadeType.MERGE
                        },
                        mappedBy = "tags")
    @JsonIgnore
    private Set<Item> items = new HashSet<>();

    //############ End M-to-M inverse side
    //################################

    public Tag() {}

    public Tag(String tagName) {
        this.tagName = tagName;
    }

    public Long getId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }

    public void setTagName(String tagName) {
        this.tagName = tagName;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }
}