package com.utn.elbuensaborbackend.services;

import com.utn.elbuensaborbackend.dtos.ImagenDTO;
import com.utn.elbuensaborbackend.entities.Imagen;
import com.utn.elbuensaborbackend.mappers.BaseMapper;
import com.utn.elbuensaborbackend.mappers.ImagenMapper;
import com.utn.elbuensaborbackend.repositories.BaseRepository;
import com.utn.elbuensaborbackend.repositories.ImagenRepository;
import com.utn.elbuensaborbackend.services.interfaces.ImagenService;
import com.utn.elbuensaborbackend.util.ImagenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class ImagenServiceImpl extends BaseServiceImpl<Imagen, ImagenDTO, Long> implements ImagenService {

    @Autowired
    private ImagenRepository imagenRepository;

    private final ImagenMapper imagenMapper = ImagenMapper.getInstance();

    public ImagenServiceImpl(BaseRepository<Imagen, Long> baseRepository, BaseMapper<Imagen, ImagenDTO> baseMapper) {
        super(baseRepository, baseMapper);
    }

    @Override
    public Resource findByName(String nombre) throws Exception {
        try {
            Path path = Paths.get("images").toAbsolutePath().resolve(nombre);
            Resource resource = new UrlResource(path.toUri());

            if (!resource.exists() || !resource.isReadable()) {
                throw new Exception("La imagen no existe.");
            }

            return resource;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public void saveImagen(MultipartFile file, String nombre) throws Exception {
       try {
           if (!ImagenUtil.isImage(file) || !ImagenUtil.isSizeAcceptable(file)) {
               throw new Exception("El archivo no es una imagen o su tamaño es demasiado grande");
           }

           Path path = Paths.get("images").toAbsolutePath();
           String filePath = path + File.separator + nombre;

           File dest = new File(filePath);
           file.transferTo(dest);
       } catch (Exception e) {
           throw new Exception(e.getMessage());
       }
    }

    @Override
    public void deleteImagen(String nombre) throws Exception {
        try {
            File imagen = findByName(nombre).getFile();
            imagen.delete();
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}