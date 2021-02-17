package com.grecar.serviceproducts;

import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;
import com.grecar.serviceproducts.repositorio.ProductoRepositorio;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Temporal;
import java.util.Date;
import java.util.List;

@DataJpaTest
public class ProductoRepositorioMockTest {
    @Autowired
    private ProductoRepositorio productoRepositorio;

    @Test
    public void whenFindByCategoria_thenReturnListProducto(){
        Producto producto01 = Producto.builder()
                .nombre("lapto")
                .categoria(Categoria.builder().Id(1L).build())
                .descripcion("")
                .stock(Double.parseDouble("10"))
                .precio(Double.parseDouble("1240.99"))
                .status("creado")
                .createAt(new Date()).build();

        productoRepositorio.save(producto01);

        List<Producto> founds = productoRepositorio.findByCategoria(producto01.getCategoria());

        Assertions.assertThat(founds.size()).isEqualTo(3);



    }


}
