package br.com.jeftavares.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_tags")
public class TagEntity {

    @Id
    @Column(name = "tag_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //auto increment
    private Long tagId;

    @Column(name = "name", unique = true)
    private String name;

    public TagEntity() {
    }

    public Long getTagId() {
        return tagId;
    }

    public void setTagId(Long tagId) {
        this.tagId = tagId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}