package com.codestoreapi.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

public class UploadFileUtils {
    private static ServletContext context;

    private static String format = "";

    public static void setFormat(String currentFormat) {
        format = currentFormat;
    }


    public static void saveFile(String uploadDir, String fileName,
                                MultipartFile multipartFile) throws IOException {
        Path uploadPath = Paths.get(context.getRealPath("resources/"+uploadDir));

        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }

        try (InputStream inputStream = multipartFile.getInputStream()) {
            Path filePath = uploadPath.resolve(fileName);
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException ioe) {
            throw new IOException("Could not save image file: " + fileName, ioe);
        }
    }

    public static String formatImage(MultipartFile multipartFile) {
        String contentType = multipartFile.getContentType();
//        String test = contentType;
        switch (contentType) {
            case "image/png":
                setFormat(".png");
                break;
            case "image/svg+xml":
                setFormat(".svg");
                break;
            default:
                setFormat(".jpg");
                break;
        }

        return format;
    }
}