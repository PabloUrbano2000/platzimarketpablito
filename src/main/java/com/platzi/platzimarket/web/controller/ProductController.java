package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.Product;
import com.platzi.platzimarket.domain.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductService service;

    @GetMapping("/all")
    @ApiOperation("Get all supermarket products")
    @ApiResponse(code=200, message = "OK")
    public ResponseEntity<List<Product>>/*List<Product>*/ productos(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK)/*service.getAll()*/;
    }

    @GetMapping("/{productId}")
    @ApiOperation("Search a product with an ID")
    @ApiResponses({
            @ApiResponse(code=200, message = "OK"),
            @ApiResponse(code=404, message = "NOT FOUND")
    })
    public ResponseEntity<Product>obtenerProducto(
            @ApiParam(value= "The Id of the product", required = true, example = "7")
            @PathVariable("productId") int productId){

        return service.getProduct(productId).map(product-> new ResponseEntity<>(product, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND))/*service.getProduct(productId)*/;
    }

    @GetMapping("/category/{id}")
    @ApiOperation("Search products by IDCategory")
    @ApiResponses({
                    @ApiResponse(code=200, message = "OK"),
                    @ApiResponse(code=404, message = "NOT FOUND")
    })
    public ResponseEntity<List<Product>> productosPorCategoria(
            @ApiParam(value= "The Id of the category", required = true, example = "1")
            @PathVariable("id") int categoryId){
        return service.getByCategory(categoryId).map(products -> new ResponseEntity<>(products, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Product>/*Product*/ save(@RequestBody Product product){
        return new ResponseEntity<>(service.save(product), HttpStatus.CREATED)/*service.save(product)*/;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity/*boolean*/ delete(@PathVariable("id") int productId){
        if (service.delete(productId)){
            return new ResponseEntity(HttpStatus.OK);
        }else{
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        /*return service.delete(productId);*/
    }
}
