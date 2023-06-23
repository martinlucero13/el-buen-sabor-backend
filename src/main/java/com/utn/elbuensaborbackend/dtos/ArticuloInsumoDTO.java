package com.utn.elbuensaborbackend.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class ArticuloInsumoDTO extends BaseDTO{

    private String denominacion;

    private Boolean esInsumo;

    private UnidadMedidaDTO unidadMedida;

    private ArticuloInsumoPrecioCompraDTO articuloInsumoPrecioCompra;

    private ArticuloInsumoStockMinimoDTO articuloInsumoStockMinimo;

    private ArticuloInsumoStockActualDTO articuloInsumoStockActual;

    private RubroDTO rubro;

}
