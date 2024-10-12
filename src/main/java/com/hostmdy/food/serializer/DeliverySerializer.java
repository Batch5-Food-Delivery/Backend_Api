package com.hostmdy.food.serializer;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.hostmdy.food.domain.Delivery;
import com.hostmdy.food.domain.Restaruant;
import com.hostmdy.food.domain.User;

public class DeliverySerializer extends JsonSerializer<Delivery> {
	 @Override
	    public void serialize(Delivery delivery, JsonGenerator gen, SerializerProvider serializers) throws IOException {
	        gen.writeStartObject();

	        // Serialize basic fields
	        gen.writeNumberField("id", delivery.getId());
	        gen.writeBooleanField("completed", delivery.isCompleted());
	        gen.writeObjectField("startedAt", delivery.getStartedAt());
	        gen.writeObjectField("completedAt", delivery.getCompletedAt());

//	        // Serialize restaurant without address
//	        Restaruant restaurant = delivery.getRestaurant();
//	        if (restaurant != null) {
//	            gen.writeObjectFieldStart("restaurant");
//	            gen.writeNumberField("id", restaurant.getId());
//	            gen.writeStringField("name", restaurant.getName());
//	            gen.writeEndObject();
//	        }
//	        
//	        User customer = delivery.getCustomer();
//	        if (customer != null) {
//	        	gen.writeObjectFieldStart("customer");
//	            gen.writeNumberField("id", customer.getId());
//	            gen.writeStringField("name", customer.getFirstname() + customer.getLastname());
//	            gen.writeEndObject();
//	        }
//
//	        // Serialize restaurantAddress (if needed)
//	        if (delivery.getRestaurantAddress() != null) {
//	            gen.writeObjectField("restaurantAddress", delivery.getRestaurantAddress());
//	        }
//
//	        // Serialize destination
//	        if (delivery.getDestination() != null) {
//	            gen.writeObjectField("destination", delivery.getDestination());
//	        }

	        gen.writeEndObject();
	    }
}
