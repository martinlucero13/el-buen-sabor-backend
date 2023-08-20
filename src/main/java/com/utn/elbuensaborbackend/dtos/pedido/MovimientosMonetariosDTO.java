package com.utn.elbuensaborbackend.dtos.pedido;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovimientosMonetariosDTO{
    private double ingresosTotales;
    private double costosTotales;
    private double gananciasTotales;
}
