package com.grecar.serviceproducts.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "tbl_productos")
@Data
@AllArgsConstructor @NoArgsConstructor @Builder
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "El nombre no puede estar vacio")
    private String nombre;
    private String descripcion;
    @Positive (message = "El stock debe ser mayor a cero")
    private Double stock;
    private Double precio;
    private String status;

    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;

    @NotNull(message = "La Categoria no puede estar vacia")
    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn (name = "categoria_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
    private Categoria categoria;
    private String img;
}
