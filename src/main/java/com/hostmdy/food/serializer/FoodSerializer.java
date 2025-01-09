package com.hostmdy.food.serializer;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hostmdy.food.domain.Food;

import java.io.IOException;

public class FoodSerializer extends JsonSerializer<Food> {

    @Override
    public void serialize(Food food, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("name", food.getName()); // Serialize only the name field
        jsonGenerator.writeEndObject();
    }
}
