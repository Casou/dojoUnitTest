package com.bparent.dojo.unitTest.empty.util;

import lombok.*;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class JsonUtilTest {

    @Test
    public void toJson_shouldReturnJsonString() {
        String json = JsonUtil.toJson(new JsonObject("idValue", "labelValue"));
        assertEquals("{\"id\":\"idValue\",\"label\":\"labelValue\"}", json);
    }

    @Test
    public void toJson_shouldReturnJsonStringForList() {
        String json = JsonUtil.toJson(Arrays.asList(
                new JsonObject("idValue", "labelValue"),
                new JsonObject("idValue2", "labelValue2")
        ));
        assertEquals("[{\"id\":\"idValue\",\"label\":\"labelValue\"},{\"id\":\"idValue2\",\"label\":\"labelValue2\"}]", json);
    }

    @Test
    public void toObject_shouldReturnObjectFromString() {
        JsonObject result = JsonUtil.toObject("{\"id\":\"idValue\",\"label\":\"labelValue\"}", JsonObject.class);
        assertEquals("idValue", result.getId());
        assertEquals("labelValue", result.getLabel());
    }

    @Test
    public void toObject_shouldReturnListOfObjectFromString() {
        List<JsonObject> result = JsonUtil.toObjectList("[{\"id\":\"idValue\",\"label\":\"labelValue\"}, {\"id\":\"idValue2\",\"label\":\"labelValue2\"}]", JsonObject[].class);
        assertEquals(2, result.size());
        assertEquals("idValue", result.get(0).getId());
        assertEquals("idValue2", result.get(1).getId());
    }


    @Data
    @AllArgsConstructor
    class JsonObject {
        private @NonNull String id;
        private @NonNull String label;
    }

}