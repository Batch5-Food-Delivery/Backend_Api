package com.hostmdy.food.serializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hostmdy.food.domain.Food;
import com.hostmdy.food.domain.Menu;

import java.io.IOException;

public class MenuSerializer extends JsonSerializer<Menu> {

    @Override
    public void serialize(Menu menu, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();
        gen.writeNumberField("id", menu.getId());
        gen.writeStringField("name", menu.getName());
        
        // Serializing the restaurant field
        gen.writeObjectFieldStart("restaurant");
        gen.writeNumberField("id", menu.getRestaurant().getId());
        gen.writeStringField("name", menu.getRestaurant().getName());
        gen.writeEndObject();
        
        // Serializing the foods list
        gen.writeArrayFieldStart("foods");
        for (Food food : menu.getFoods()) {
            gen.writeStartObject();
            gen.writeNumberField("id", food.getId());
            gen.writeStringField("name", food.getName());
            gen.writeStringField("picture", food.getPicture());
            gen.writeNumberField("price", food.getPrice());
            gen.writeStringField("description", food.getDescription());
            gen.writeBooleanField("available", food.getAvailable());
            gen.writeEndObject();
        }
        gen.writeEndArray();
        
        gen.writeEndObject();
    }
}
