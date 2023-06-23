package com.utn.elbuensaborbackend.util;

import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ImagenUtil {

    /**
     * Verifica si el archivo es una imagen
     *
     * @param file: Imagen a verificar.
     * @return true si el archivo es una imagen, false de lo contrario.
     */
    public static boolean isImage(MultipartFile file) {
        try {
            ImageIO.read(file.getInputStream());
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Varifica si el tamaño del archivo es menor o igual a 10MB
     *
     * @param file: Imagen a verificar.
     * @return true si el tamaño del archivo es menor o igual a 10MB, false de lo contrario.
     */
    public static boolean isSizeAcceptable(MultipartFile file) {
        return file.getSize() <= 10 * 1024 * 1024;
    }

    /**
     * Genera un nuevo nombre combinando la fecha y hora actual con la extensión del archivo
     *
     * @param nombre: Nombre original del archivo, incluyendo la extensión.
     * @return el nuevo nombre de archivo generado que combina la fecha y hora actual con la extensión original.
     */
    public static String generateName(String nombre) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String formattedDateTime = currentDateTime.format(formatter);

        String extension = nombre.substring(nombre.lastIndexOf("."));

        return formattedDateTime + extension;
    }
}
