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

public class Product {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Long id;
    private String name;
    private int quantity;
    private double prix;
    private String categorie;
    @ManyToMany(
            fetch = FetchType.EAGER
    )
    Collection<Fournisseure> fournisseures;}


