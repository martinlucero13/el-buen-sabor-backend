package com.utn.elbuensaborbackend.services;

/*import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.preference.PreferenceBackUrlsRequest;
import com.mercadopago.client.preference.PreferenceClient;
import com.mercadopago.client.preference.PreferenceItemRequest;
import com.mercadopago.client.preference.PreferenceRequest;
import com.mercadopago.resources.preference.Preference;
import com.utn.elbuensaborbackend.dtos.MercadoPagoDatosDTO;
import com.utn.elbuensaborbackend.dtos.pedido.ArticuloCantidadDTO;
import com.utn.elbuensaborbackend.entities.ArticuloManufacturado;
import com.utn.elbuensaborbackend.entities.MercadoPagoDatos;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.services.interfaces.MercadoPagoService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class MercadoPagoServiceImpl extends BaseServiceImpl<MercadoPagoDatos, MercadoPagoDatosDTO, Long> implements MercadoPagoService {

    @Autowired
    private ArticuloManufacturadoServiceImpl articuloManufacturadoService;

    @Value("${mercadopago.access_token}")
    private String mpAccessToken;

    @Value("${mercadopago.back_url.success}")
    private String mpSuccessBackUrl;

    @Value("${mercadopago.back_url.pending}")
    private String mpPendingBackUrl;

    @Value("${mercadopago.back_url.failure}")
    private String mpFailureBackUrl;

    public MercadoPagoServiceImpl(BaseRepository<MercadoPagoDatos, Long> baseRepository, BaseMapper<MercadoPagoDatos, MercadoPagoDatosDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @PostConstruct
    public void initMPConfig() {
        MercadoPagoConfig.setAccessToken(mpAccessToken);
    }

    public Preference createPreference(List<ArticuloCantidadDTO> detallesPedido, String estadoEntrega) throws Exception {
        try {
            List<PreferenceItemRequest> items = new ArrayList<>();

            for (ArticuloCantidadDTO detalleDto : detallesPedido) {
                PreferenceItemRequest itemRequest = getItemRequest(detalleDto, estadoEntrega);
                items.add(itemRequest);

            }

            PreferenceRequest preferenceRequest = PreferenceRequest.builder()
                    .items(items)
                    .autoReturn("approved")
                    .backUrls(
                            PreferenceBackUrlsRequest.builder()
                                    .success(mpSuccessBackUrl)
                                    .pending(mpPendingBackUrl)
                                    .failure(mpFailureBackUrl)
                                    .build()
                    )
                    .build();
            PreferenceClient client = new PreferenceClient();
            return client.create(preferenceRequest);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    private PreferenceItemRequest getItemRequest(ArticuloCantidadDTO detalleDto, String estadoEntrega) throws Exception {
        try {
            if (detalleDto.getCantidad() == null || detalleDto.getCantidad() <= 0) {
                throw new Exception("La cantidad a comprar debe ser mayor a cero.");
            }

            Optional<ArticuloManufacturado> optionalArticuloManufacturado
                    = articuloManufacturadoService.findOptionalById(detalleDto.getIdArticuloManufacturado());
            if (optionalArticuloManufacturado.isEmpty()) {
                throw new Exception("No existe un Producto con el ID: " + detalleDto.getIdArticuloManufacturado());
            }
            ArticuloManufacturado articuloManufacturado = optionalArticuloManufacturado.get();

            Double precioFinal = articuloManufacturado.getPreciosVentas().get(0).getMonto();

            if (estadoEntrega.equals("LOCAL")) {
                precioFinal *= 0.9;
            }

            return PreferenceItemRequest.builder()
                    .id(String.valueOf(articuloManufacturado.getId()))
                    .title(articuloManufacturado.getDenominacion())
                    .description(articuloManufacturado.getDescripcion())
                    .quantity(detalleDto.getCantidad())
                    .currencyId("ARS")
                    .unitPrice(BigDecimal.valueOf(precioFinal))
                    .build();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}*/
