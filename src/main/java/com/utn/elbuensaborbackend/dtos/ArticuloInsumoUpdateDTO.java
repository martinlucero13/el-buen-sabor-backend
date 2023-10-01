package com.utn.elbuensaborbackend.dtos;

import lombok.Data;

@Data
public class ArticuloInsumoUpdateDTO {

    private Float stockGanado;
    private Float stockPerdido;
    private Double precioCompra;
}
