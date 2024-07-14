package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.validation;

import org.apache.coyote.BadRequestException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class ImageValidation {

    private static final List<String> ALLOWED_CONTENT_TYPES =
            Arrays.asList("image/jpg", "image/jpeg", "image/png", "image/gif", "image/bmp", "image/webp");

    public static void validateImage(MultipartFile image) throws IOException{
        if (image == null || image.isEmpty()){
            throw new BadRequestException("O campo de imagem não pode ser nulo ou vazio.");
        }

        if (image.getContentType() == null || !ALLOWED_CONTENT_TYPES.contains(image.getContentType())){
            throw new BadRequestException("O tipo de arquivo é inválido");
        }
    }
}
