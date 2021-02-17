package com.grecar.serviceproducts.servicio;

import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;
import com.grecar.serviceproducts.repositorio.ProductoRepositorio;
import lombok.RequiredArgsConstructor;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
public class ProductoServicioImpl implements ProductoServicio {


    private final ProductoRepositorio productoRepositorio;
    @Override
    public List<Producto> listAllProducto() {
        return productoRepositorio.findAll();
    }

    @Override
    public Producto getProducto(Long id) {
        return productoRepositorio.findById(id).orElse(null);
    }

    @Override
    public Producto createProducto(Producto producto) {
        producto.setStatus("Creado");
        producto.setCreateAt(new Date());
        return productoRepositorio.save(producto);
    }

    @Override
    public Producto updateProducto(Producto producto) {
            Producto productoDB = getProducto(producto.getId());
            if (null == productoDB){
                return null;
            }
            productoDB.setNombre(producto.getNombre());
            productoDB.setDescripcion(producto.getDescripcion());
            productoDB.setCategoria(producto.getCategoria());
            productoDB.setPrecio(producto.getPrecio());

            return productoRepositorio.save(productoDB);


    }

    @Override
    public Producto deleteProducto(Long id) {
        Producto productoDB = getProducto(id);
        if (null == productoDB){
            return null;
        }
        productoDB.setStatus("Eliminado");
        return productoRepositorio.save(productoDB);
    }

    @Override
    public List<Producto> findByCategoria(Categoria categoria) {
        return productoRepositorio.findByCategoria(categoria);
    }

    @Override
    public Producto updateStock(Long id, Double quantity) {
        Producto productoDB = getProducto(id);
        if (null == productoDB){
            return null;
        }

        Double stock = productoDB.getStock() + quantity;
        productoDB.setStock(stock);
        return productoRepositorio.save(productoDB);
    }
}
