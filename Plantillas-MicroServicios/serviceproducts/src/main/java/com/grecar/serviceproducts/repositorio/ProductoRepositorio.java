package com.grecar.serviceproducts.repositorio;

import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepositorio extends JpaRepository<Producto,Long> {

    public List<Producto> findByCategoria(Categoria categoria);


}
