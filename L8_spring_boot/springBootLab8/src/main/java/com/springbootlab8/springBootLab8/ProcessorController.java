package com.springbootlab8.springBootLab8;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.imageio.ImageIO;
import javax.servlet.ServletInputStream;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

public class ProcessorController
{
    public Map<Integer, BufferedImage> mapStoringImages = new HashMap<>();
    int inMap = 0;          //do sprawdzenia czy obraz jest w mapie


    //(2.a.i)
    //POST /image – przyjmuje obraz w formacie binarnym i zapisuje go do pamięci aplikacji, zwraca ID-------------------
    int postImage(ServletInputStream servletInputStream) throws IOException {
        InputStream inputStream = new BufferedInputStream(servletInputStream);
        // odczytanie wybranego zdjęcia
        BufferedImage bufferedImage1 = ImageIO.read(inputStream);
        // uzyskanie szerokości oraz wysokości
        BufferedImage bufferedImage2 = new BufferedImage(bufferedImage1.getWidth(),
                bufferedImage1.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        bufferedImage2.createGraphics().drawImage(bufferedImage1, 0, 0, null);
        mapStoringImages.put(inMap, bufferedImage2);

        return inMap++;
    }

    //(2.a.ii)
    //DELETE /image/{id} – usuwa obraz o danym ID z pamięci aplikacji---------------------------------------------------
    void deleteImage(int id) throws myNotFoundException {
        if (!mapStoringImages.containsKey(id) || mapStoringImages.get(id) == null) {
            throw new myNotFoundException("Nie znaleziono obrazu do usunięcia\n");
        } else {
            mapStoringImages.remove(id);
        }
    }

    //(2.a.iii)
    //GET /image/{id}/size – zwraca dane w formacie JSON z wymiarami wczytanego obrazu----------------------------------
    JSONObject getSize(int id) throws myNotFoundException, JSONException {
        if (!mapStoringImages.containsKey(id) || mapStoringImages.get(id) == null) {
            throw new myNotFoundException("Nie znaleziono obrazu do pobrania rozmiaru\n");
        } else {
            JSONObject imageSize = new JSONObject();
            imageSize.put("szerokość", mapStoringImages.get(id).getWidth());
            imageSize.put("wysokość", mapStoringImages.get(id).getHeight());

            return imageSize;
        }
    }

    //(2.a.iv)
    //GET /image/{id}/histogram - zwraca dane w formacie JSON zawierające histogram wczytanego obrazu-------------------
    JSONObject getHistogram(int id) throws myNotFoundException, JSONException {
        if (!mapStoringImages.containsKey(id) || mapStoringImages.get(id) == null) {
            throw new myNotFoundException("Nie znaleziono obrazu dla histogramu\n");
        } else {
            JSONObject imageHistogram = new JSONObject();
            JSONObject imageSize = getSize(id);
            int black = 0;
            int white = 0;

            // liczenie pikseli
            for(int i = 0; i < imageSize.getInt("szerokość"); i++) {
                for(int j = 0; j < imageSize.getInt("wysokość"); j++) {
                    if (mapStoringImages.get(id).getRGB(i, j) == -1) {
                        white++;
                    } else {
                        black++;
                    }
                }
            }
            imageHistogram.put("Czarny", black);
            imageHistogram.put("Biały", white);

            return imageHistogram;
        }
    }

    //(2.a.v)
    //CROP /image/{id}/crop – zwraca wycinek obrazu, gdzie parametry wycinanego fragmentu (start, stop, szer, wys)------
    //gdzie x, y to punkt startowy gdzie zacząć przycinać, natomiast w, h -> gdzie skończyć przycinanie
    byte[] getCrop(int id, int x, int y, int width, int height) throws JSONException, myOutOfSizeException, myNotFoundException, IOException {
        if (!mapStoringImages.containsKey(id) || mapStoringImages.get(id) == null) {
            throw new myNotFoundException("Nie znaleziono zdjęcia wybranego do przycięcia\n");
        } else {
            JSONObject imageSize = getSize(id);

            if ((x + width) > imageSize.getInt("szerokość") || (y + height) > imageSize.getInt("wysokość")) {
                throw new myOutOfSizeException("Błąd, wybrano obszar poza rozmiarem obrazu\n");
            } else {
                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                ImageIO.write(mapStoringImages.get(id).getSubimage(x, y, width, height), "jpg", byteArrayOutputStream);
                byteArrayOutputStream.flush();

                byte[] bytesImageCrop = byteArrayOutputStream.toByteArray();
                byteArrayOutputStream.close();

                return bytesImageCrop;
            }
        }
    }
}

// obsługa wyjątków
@ResponseBody
@ResponseStatus(HttpStatus.NOT_FOUND)
class myNotFoundException extends RuntimeException
{
    myNotFoundException(String myException) {
        super(myException);
    }
}

@ResponseBody
class myOutOfSizeException extends RuntimeException
{
    myOutOfSizeException(String myOutOfSizeException) {
        super(myOutOfSizeException);
    }
}