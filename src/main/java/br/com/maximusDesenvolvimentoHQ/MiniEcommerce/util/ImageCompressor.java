package br.com.maximusDesenvolvimentoHQ.MiniEcommerce.util;

import javax.imageio.IIOImage;
import javax.imageio.ImageIO;
import javax.imageio.ImageWriteParam;
import javax.imageio.ImageWriter;
import javax.imageio.stream.ImageOutputStream;
import javax.xml.bind.DatatypeConverter;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Iterator;

public class ImageCompressor {

    public static String compressImage(String base64Image) throws IOException {
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage image = ImageIO.read(bais);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        Iterator<ImageWriter> writes = ImageIO.getImageWritersByFormatName("png");
        ImageWriter writer = writes.next();
        writer.setOutput(ios);

        ImageWriteParam param = writer.getDefaultWriteParam();
        if(param.canWriteCompressed()){
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality((float) 0.0005);
        }

        writer.write(null,new IIOImage(image,null,null),param);

        ios.close();
        writer.dispose();

        byte[] compressedImageBytes = baos.toByteArray();
        return DatatypeConverter.printBase64Binary(compressedImageBytes);
    }

    public static String compressAndResizeImage(String base64Image, double compressionPercentage, double scale) throws IOException {
        // Decodificar a imagem base64 para um BufferedImage
        byte[] imageBytes = DatatypeConverter.parseBase64Binary(base64Image);
        ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
        BufferedImage originalImage = ImageIO.read(bais);

        // Redimensionar a imagem
        int newWidth = (int) (originalImage.getWidth() * scale);
        int newHeight = (int) (originalImage.getHeight() * scale);
        Image tmpImage = originalImage.getScaledInstance(newWidth, newHeight, Image.SCALE_SMOOTH);
        BufferedImage resizedImage = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D g2d = resizedImage.createGraphics();
        g2d.drawImage(tmpImage, 0, 0, null);
        g2d.dispose();

        // Preparar para escrever a imagem comprimida
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageOutputStream ios = ImageIO.createImageOutputStream(baos);
        Iterator<ImageWriter> writers = ImageIO.getImageWritersByFormatName("jpeg");
        ImageWriter writer = writers.next();
        writer.setOutput(ios);

        // Configurar parâmetros de compressão
        ImageWriteParam param = writer.getDefaultWriteParam();
        if (param.canWriteCompressed()) {
            param.setCompressionMode(ImageWriteParam.MODE_EXPLICIT);
            param.setCompressionQuality((float) compressionPercentage); // Ajuste a qualidade da compressão (0.0 a 1.0)
        }

        // Escrever a imagem comprimida
        writer.write(null, new IIOImage(resizedImage, null, null), param);

        // Fechar streams
        ios.close();
        writer.dispose();

        // Converter a imagem comprimida para base64
        byte[] compressedImageBytes = baos.toByteArray();
        return DatatypeConverter.printBase64Binary(compressedImageBytes);
    }
}
