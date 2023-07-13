package com.utn.elbuensaborbackend.controllers;

import com.utn.elbuensaborbackend.dtos.PedidoDTO;
import com.utn.elbuensaborbackend.entities.Pedido;
import com.utn.elbuensaborbackend.services.PedidoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/pedidos")
public class PedidoController {
    @Autowired
    private PedidoServiceImpl service;

    @GetMapping("")
    public ResponseEntity<?> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los pedidos por termino\"}");
        }
    }

    @GetMapping("/byCliente/{id}")
    public ResponseEntity<?> getByCliente(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByCliente(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los pedidos por termino\"}");
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los pedidos por termino\"}");
        }
    }

    @GetMapping("/byTermino/{termino}")
    public ResponseEntity<?> getByTermino(@PathVariable String termino) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByTermino(termino));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los pedidos por termino\"}");
        }
    }

    @GetMapping("/byEstado/{estado}")
    public ResponseEntity<?> getByEstado(@PathVariable String estado) {
        try {
            return ResponseEntity.status(HttpStatus.OK)
                    .body(service.findByEstado(estado));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("{\"error\":\"Error. No se pudieron recuperar los pedidos por termino\"}");
        }
    }

    @PostMapping("")
    public ResponseEntity<?> save(@RequestBody PedidoDTO entity) {
        try {
            Pedido pedido = service.save(entity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al guardar el pedido \"}");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @RequestBody PedidoDTO entity) {
        try {
            Pedido pedido = service.update(id, entity);
            return ResponseEntity.status(HttpStatus.OK)
                    .body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al actualizar el pedido \"}");
        }
    }

    @PutMapping("/minuto/{id}")
    public ResponseEntity<?> sumarMinutos(@PathVariable Long id, @RequestBody PedidoDTO entity) {
        try {
            PedidoDTO pedido = service.updateFecha(id, entity);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al sumar 10 min en pedido \"}");
        }
    }

    @PutMapping("/estado/{id}")
    public ResponseEntity<?> cambiarEstado(@PathVariable Long id, @RequestBody String estado) {
        try {
            PedidoDTO pedido = service.updateEstado(id, estado);
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(pedido);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al sumar 10 min en pedido \"}");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return ResponseEntity.status(HttpStatus.OK)
                    .body("{\"message\":\"Pedido eliminado exitosamente\"}");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("{\"error\":\"Error al eliminar el pedido\"}");
        }
    }
}
