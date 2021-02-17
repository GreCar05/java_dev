package com.grecar.serviceproducts;

import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;
import com.grecar.serviceproducts.repositorio.ProductoRepositorio;
import com.grecar.serviceproducts.servicio.ProductoServicio;
import com.grecar.serviceproducts.servicio.ProductoServicioImpl;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
public class ProductoServicioMockTest {
    @Mock
    private ProductoRepositorio productoRepositorio;

    private ProductoServicio productoServicio;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.initMocks(this);
        productoServicio = new ProductoServicioImpl(productoRepositorio);

        Producto lapto =  Producto.builder()
                .id(1L)
                .nombre("lapto")
                .categoria(Categoria.builder().Id(1L).build())
                .precio(Double.parseDouble("12.5"))
                .stock(Double.parseDouble("5"))
                .build();
        Mockito.when(productoRepositorio.findById(1L))
                .thenReturn(Optional.of(lapto));
    }

    @Test
    public void whenValidGetID_ThenReturnProducto(){
        Producto found = productoServicio.getProducto(1L);
        Assertions.assertThat(found.getNombre()).isEqualTo("lapto");
    }
}
