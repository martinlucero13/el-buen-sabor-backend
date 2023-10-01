package com.utn.elbuensaborbackend.dtos;

import com.utn.elbuensaborbackend.enums.UnidadMedida;
import lombok.Data;

@Data
public class ArticuloInsumoFullDTO extends BaseDTO {

    private String denominacion;
    private Boolean esInsumo;
    private Boolean bloqueado;
    private Float stockMinimo;
    private Float stockActual;
    private RubroDTO rubro;
    private Double precioCompra;
    private UnidadMedida unidadMedida;
}
