package com.utn.elbuensaborbackend.services.interfaces;


import com.utn.elbuensaborbackend.dtos.ImagenDTO;
import com.utn.elbuensaborbackend.entities.Imagen;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;


public interface ImagenService extends BaseService<Imagen, ImagenDTO, Long> {

    Resource findImagenByName(String nombre) throws Exception;
    void saveImage(MultipartFile file, String nombre) throws Exception;
}
