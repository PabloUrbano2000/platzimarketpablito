package com.platzi.platzimarket.web.controller;

import com.platzi.platzimarket.domain.Purchase;
import com.platzi.platzimarket.domain.service.PurchaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchaseController {

    @Autowired
    private PurchaseService service;

    @GetMapping("/all")
    public ResponseEntity<List<Purchase>> compras(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/client/{clientId}")
    public ResponseEntity<List<Purchase>> compraCliente(@PathVariable("clientId") String clientId){
        return service.getByClient(clientId).map(
                purchases -> new ResponseEntity<>(purchases, HttpStatus.OK)
        ).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/save")
    public ResponseEntity<Purchase> save (@RequestBody Purchase purchase){
        return new ResponseEntity<>(service.save(purchase), HttpStatus.CREATED);
    }
}
