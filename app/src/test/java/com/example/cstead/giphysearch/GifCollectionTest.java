package com.example.cstead.giphysearch;

import com.example.cstead.giphysearch.Models.Gif;
import com.example.cstead.giphysearch.Models.GifCollection;
import com.example.cstead.giphysearch.Models.Images;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by cstead on 9/9/17.
 */

public class GifCollectionTest {

    @Test
    public void parseJson_ParsesAGif() {
        String json = "{\n" +
                "    \"data\": [\n" +
                "        {\n" +
                "            id: \"FiGiRei2ICzzG\",\n" +
                "            images: {\n" +
                "                fixed_height: {\n" +
                "                    url: \"http://media2.giphy.com/media/FiGiRei2ICzzG/200.gif\"\n" +
                "                },\n" +
                "                fixed_height_still: {\n" +
                "                    url: \"http://media2.giphy.com/media/FiGiRei2ICzzG/200_s.gif\"\n" +
                "                }\n" +
                "            }\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        GifCollection gifCollection = GifCollection.parseJSON(json);
        Gif gif = gifCollection.gifs.get(0);
        assertNotNull(gif);
        assertEquals("FiGiRei2ICzzG", gif.id);
        Images images = gif.images;
        assertNotNull(images);
        assertNotNull(images.fixedHeight);
        assertEquals("http://media2.giphy.com/media/FiGiRei2ICzzG/200.gif", images.fixedHeight.url);
        assertNotNull(images.fixedHeightStill);
        assertEquals("http://media2.giphy.com/media/FiGiRei2ICzzG/200_s.gif", images.fixedHeightStill.url);
    }

}
