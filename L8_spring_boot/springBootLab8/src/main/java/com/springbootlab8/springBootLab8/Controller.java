package com.springbootlab8.springBootLab8;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import org.json.JSONException;
import org.json.JSONObject;

//klasa RestController zawierająca metody operacji na obrazach: POST, DELETE, GET, CROP ----> tu wypisywanie
@RestController
public class Controller
{
    public ProcessorController processorController = new ProcessorController();

    //(2.a.i)
    //POST /image – wyświetlanie----------------------------------------------------------------------------------------
    @ResponseBody
    //Spring stara się przekształcić zwrócone wartości w odpowiedź http (np. konwertując obiekt na format JSON lub XML)
    @RequestMapping(method = RequestMethod.POST, value = "/image")
    public String postObject(HttpServletRequest request) throws IOException {
        Integer id = processorController.postImage(request.getInputStream());
        return "Przesłano zdjęcie o ID = " + id.toString();
    }

    //(2.a.ii)
    //DELETE /image/{id} – wyświetlanie---------------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(method = RequestMethod.DELETE, value = "/image/{id}")
    public String deleteObject(@PathVariable int id) {
        processorController.deleteImage(id);
        return "Usunięto zdjęcie o ID = " + id;
    }

    //(2.a.iii)
    //GET /image/{id}/size – wyświetlanie-------------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/image/{id}/size")
    public String getSizeObject(@PathVariable int id) throws myNotFoundException, JSONException {
        JSONObject imageSize = processorController.getSize(id);
        return "Rozmiar zdjęcia o ID = " + id + " jest równy = " + imageSize;
    }

    //(2.a.iv)
    //GET /image/{id}/histogram - wyświetlanie--------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, value = "/image/{id}/histogram")
    public String getHistogramObject(@PathVariable int id) throws myNotFoundException, JSONException {
        JSONObject imageHistogram = processorController.getHistogram(id);
        return "Histogram zdjęcia ID = " + id + ": " + imageHistogram;
    }

    //(2.a.v)
    //CROP /image/{id}/crop – wyświetlanie------------------------------------------------------------------------------
    @ResponseBody
    @RequestMapping(method = RequestMethod.GET, produces = MediaType.IMAGE_PNG_VALUE, value = "/image/{id}/crop/{x}/{y}/{width}/{height}")
    public byte[] getCropObject(@PathVariable int id, @PathVariable int x, @PathVariable int y,
                                @PathVariable int width, @PathVariable int height)
            throws JSONException, myOutOfSizeException, myNotFoundException, IOException {
                return processorController.getCrop(id, x, y, width, height);
    }
}