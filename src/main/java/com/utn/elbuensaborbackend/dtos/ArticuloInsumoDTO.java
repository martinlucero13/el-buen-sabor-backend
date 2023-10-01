package com.utn.elbuensaborbackend.dtos;


import com.utn.elbuensaborbackend.enums.UnidadMedida;
import lombok.Data;

@Data
public class ArticuloInsumoDTO extends BaseDTO {

    private String denominacion;
    private Double precioCompra;
    private Boolean bloqueado;
    private UnidadMedida unidadMedida;
}
