package br.com.jeftavares.ecommerce.entities;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "tb_products")
public class ProductEntity {

    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "price")
    private BigDecimal price;

    @ManyToMany
     //Tabela Associativa, basicamente cria uma tabela fazendo a amarração entre as entidades
    @JoinTable(
            name = "tb_products_tags",
            uniqueConstraints = @UniqueConstraint(columnNames = {"product_id", "tag_id"}), //criar um indice único para que o mesmo produto nao esteja amarrado a mesma tag
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")

    )
    private List<TagEntity> tags;
    
    public ProductEntity() {
        //Construtor vazio, pois é uma classe de banco de dados (entity)
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
    }
}