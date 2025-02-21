package br.com.jeftavares.ecommerce.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_billing_address")
public class BillingAddressEntity {

    @Id
    @Column(name = "billing_address_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long billingAddressId;

    @Column(name = "address")
    private String address;

    @Column(name = "number")
    private String number;

    @Column(name = "complement")
    private String complement;

    //na minha tabela tb_billing_address não quero adicionar uma coluna indicando qual usuario esse endereço é referente
    @OneToOne(mappedBy = "billingAddress")
    private UserEntity user;

    public BillingAddressEntity() {
        //Como é uma classe de banco de dados (entity) o construtor é vazio
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }
}