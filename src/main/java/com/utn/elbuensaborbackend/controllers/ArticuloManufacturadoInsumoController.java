package com.utn.elbuensaborbackend.controllers;


import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoDTO;
import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoInsumoDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturadoInsumo;
import com.utn.elbuensaborbackend.services.ArticuloManufacturadoInsumoServiceImpl;
import com.utn.elbuensaborbackend.services.ArticuloManufacturadoServiceImpl;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoInsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/v1/articulos-manufacturados-insumos")
public class ArticuloManufacturadoInsumoController {

    @Autowired
    private ArticuloManufacturadoInsumoServiceImpl service;

    @GetMapping("/byArticuloManufacturado/{id}")
    public ResponseEntity<?> getByArticuloManufacturadoId(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByArticuloManufacturadoId(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los productos por termino\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody ArticuloManufacturadoInsumoDTO entity) {
        try {
            ArticuloManufacturadoInsumo articuloManufacturadoInsumo = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(articuloManufacturadoInsumo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al guardar el artículo manufacturado\"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody ArticuloManufacturadoInsumoDTO entity) {
        try {
            service.update(id,entity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\":\"Artículo manufacturado insumo actualizado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al actualizar el artículo manufacturado\"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\":\"Artículo manufacturado eliminado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al eliminar el artículo manufacturado\"}");
        }
    }
}
