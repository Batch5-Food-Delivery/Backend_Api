package com.hostmdy.food.serializer;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hostmdy.food.domain.Address;

import java.io.IOException;

public class AddressSerializer extends JsonSerializer<Address> {

    @Override
    public void serialize(Address address, JsonGenerator gen, SerializerProvider serializers) throws IOException {
        gen.writeStartObject();

        // Exclude 'id' field as requested
        gen.writeStringField("township", address.getTownship());
        gen.writeStringField("street", address.getStreet());
        gen.writeStringField("additionalDetails", address.getAdditionalDetails());

        gen.writeEndObject();
    }
}
