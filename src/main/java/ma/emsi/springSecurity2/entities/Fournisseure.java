package ma.emsi.springSecurity2.entities;

//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fournisseure {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String prenom;
    private String name;
    private int quantity;
    private long phone;
    private String productname;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    Collection<Product> products;}


