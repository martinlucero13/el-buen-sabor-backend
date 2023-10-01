package com.utn.elbuensaborbackend.services.interfaces;

import com.utn.elbuensaborbackend.dtos.ImagenDTO;
import com.utn.elbuensaborbackend.entities.Imagen;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface ImagenService extends BaseService<Imagen, ImagenDTO, Long> {

    Resource findByName(String nombre) throws Exception;
    void saveImagen(MultipartFile file, String nombre) throws Exception;
    void deleteImagen(String nombre) throws Exception;
}