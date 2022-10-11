package com.sun.collectiblestore.domain.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sun.collectiblestore.domain.enums.ProductType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "product")
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data // faz o get/set
@NoArgsConstructor // faz o construtor vazio
@AllArgsConstructor // faz os construtores
public class Product {
    //name description type price quantity provider last register date first register date

    //research *
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID productId;

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private Date registerDate;
    @Column(nullable = false)
    private Date lastUpdateDate;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private ProductType type;

    // quando queremos que o java a partir de uma informação primitiva crie uma tabela e faça uma relação usa o element collection quando tem uma classe pra isso usa o one to many
    // @ElementCollection @CollectionTable(name = "name", joinColumns = @JoinColumn(name = "product_id", foreignKey = @ForeignKey(name = "fkey_product_id")) se precisar de uma lista


}
