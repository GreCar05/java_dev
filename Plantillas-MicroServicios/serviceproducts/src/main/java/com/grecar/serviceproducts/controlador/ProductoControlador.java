package com.grecar.serviceproducts.controlador;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.grecar.serviceproducts.entity.Categoria;
import com.grecar.serviceproducts.entity.Producto;
import com.grecar.serviceproducts.servicio.ProductoServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping (value = "/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    @GetMapping
    public ResponseEntity<List<Producto>> listProducto(@RequestParam(name = "categoriaId", required = false) Long categoriaId){
        List<Producto> productos = productoServicio.listAllProducto();

        if(null == categoriaId){
            productos = productoServicio.listAllProducto();
            if (productos.isEmpty()){
                return ResponseEntity.noContent().build();
            }
        }else {
            productos = productoServicio.findByCategoria(Categoria.builder().Id(categoriaId).build());
            if (productos.isEmpty()){
                return  ResponseEntity.notFound().build();
            }
        }


        return ResponseEntity.ok(productos);

    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Producto> getProducto(@PathVariable("id") Long id){
        Producto producto = productoServicio.getProducto(id);
        if (null == producto){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(producto);
    }

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@Valid @RequestBody Producto producto, BindingResult result){

        if(result.hasErrors()){
            throw  new ResponseStatusException(HttpStatus.BAD_REQUEST, this.formatMessage(result));
        }
        Producto productocrear = productoServicio.createProducto(producto);
        return ResponseEntity.status(HttpStatus.CREATED).body(productocrear);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Producto> actualizarProducto(@PathVariable("id") Long id,@RequestBody Producto producto){
        producto.setId(id);
        Producto productoDB = productoServicio.updateProducto(producto);
        if (productoDB == null){
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(productoDB);
    }
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Producto> eliminarProducto(@PathVariable("id") Long id){
        Producto productoDelete = productoServicio.deleteProducto(id);
        if (productoDelete== null){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(productoDelete);
    }

    @GetMapping(value = "/{id}/stock" )
    public ResponseEntity<Producto> actualizarStockProducto(@PathVariable Long id, @RequestParam(name = "cantidad", required = true) Double cantidad){
        Producto producto = productoServicio.updateStock(id,cantidad);
        if (null == producto){
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(producto);

    }

    private String formatMessage( BindingResult result){
        List<Map<String,String>> errors = result.getFieldErrors().stream()
            .map(err ->{
                    Map<String,String> error = new HashMap<>();
                    error.put(err.getField(), err.getDefaultMessage());

                    return error;
                }).collect(Collectors.toList());
            ErrorMessage errorMessage = ErrorMessage.builder()
                    .code("01")
                    .message(errors).build();
        ObjectMapper mapper = new ObjectMapper();
            String jsonString= "";
            try{
                jsonString = mapper.writeValueAsString(errorMessage);
            } catch (JsonParseException e){
                e.printStackTrace();
            }

            return jsonString;
    }

}

