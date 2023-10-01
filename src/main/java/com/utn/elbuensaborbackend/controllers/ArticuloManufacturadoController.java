package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.ArticuloManufacturadoFullDTO;
import com.utn.elbuensaborbackend.dtos.pedido.ArticuloCantidadDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.services.interfaces.ArticuloManufacturadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("api/v1/articulos-manufacturados")
public class ArticuloManufacturadoController extends BaseControllerImpl<ArticuloManufacturado, ArticuloManufacturadoFullDTO> {

    @Autowired
    private ArticuloManufacturadoService service;

    @GetMapping("/activos")
    public ResponseEntity<?> getAllActivos() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllActivos());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/simple")
    public ResponseEntity<?> getAllSimple() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAllSimple());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/byTermino/{termino}")
    public ResponseEntity<?> getByTermino(@PathVariable String termino) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByTermino(termino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/simple/{id}")
    public ResponseEntity<?> getSimpleById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findSimpleById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @GetMapping("/enCocina")
    public ResponseEntity<?> getArticulosEnCocina() {
        try {
            List<ArticuloManufacturado> articulosEnCocina = service.findArticulosEnCocina();
            return ResponseEntity.status(HttpStatus.OK).body(articulosEnCocina);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\": \"" + e.getMessage() + "\"}");
        }
    }

    @PostMapping("/checkStock")
    public ResponseEntity<?> checkStockDisponible(@RequestBody List<ArticuloCantidadDTO> articulosCantidad) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findStockDisponible(articulosCantidad));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }


    @PostMapping(value = "/saveFull", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> saveFull(
            @RequestPart("articuloManufacturado") ArticuloManufacturadoFullDTO dto,
            @RequestParam("file") MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.saveFull(dto, file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping(value = "/updateFull/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> updateFull(
            @PathVariable Long id,
            @RequestPart("articuloManufacturado") ArticuloManufacturadoFullDTO dto,
            @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateFull(id, dto, file));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }

    @PutMapping("/updateEstado/{id}")
    @PreAuthorize("hasAnyAuthority('Admin', 'Cocinero')")
    public ResponseEntity<?> updateEstado(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(service.updateEstado(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\": \"Ocurrio un error\"}");
        }
    }
}
