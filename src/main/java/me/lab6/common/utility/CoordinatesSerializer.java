package utilities;

import com.google.gson.*;
import workerRelated.Coordinates;

import java.lang.reflect.Type;

/**
 * A utility class for serializing Coordinates objects to JSON.
 */

public class CoordinatesSerializer implements JsonSerializer<Coordinates> {
    /**
     * Serializes a Coordinates object to JSON.
     *
     * @param src     the Coordinates object to serialize.
     * @param srcType the type of the object to serialize.
     * @param context the context in which the serialization is happening.
     * @return a JsonElement representing the serialized Coordinates object.
     */
    @Override
    public JsonElement serialize(Coordinates src, Type srcType, JsonSerializationContext context) {
        JsonObject result = new JsonObject();
        result.addProperty("x", src.x());
        result.addProperty("y", src.y());
        return result;
    }

}
