package com.grecar.serviceproducts.servicio;

import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;

import java.util.List;

public interface ProductoServicio {
    public List<Producto> listAllProducto();
    public Producto getProducto(Long id);

    public Producto createProducto(Producto producto);
    public Producto updateProducto(Producto producto);
    public Producto deleteProducto(Long id);
    public List<Producto> findByCategoria(Categoria categoria);
    public Producto updateStock(Long id, Double quantity);
}
