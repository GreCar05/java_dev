package com.grecar.serviceproducts.entity;


import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "tbl_categorias")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Categoria {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long Id;
    private String nombre;
    private String descripcion;
}
