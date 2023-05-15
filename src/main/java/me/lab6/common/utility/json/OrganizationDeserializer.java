package me.lab6.common.utility.json;

import com.google.gson.*;
import me.lab6.common.exceptions.IncorrectWorkerFieldException;
import me.lab6.common.workerRelated.Address;
import me.lab6.common.workerRelated.Organization;

import java.lang.reflect.Type;

/**
 * This class is a custom JSON deserializer for the Organization class.
 */
public class OrganizationDeserializer implements JsonDeserializer<Organization> {
    /**
     * Deserializes a JSON object into an Organization object.
     *
     * @param json    the JSON object to be deserialized
     * @param typeOfT the type of the desired object
     * @param context the context for deserialization that is passed to a deserializer during invocation of its {@link JsonDeserializer#deserialize(JsonElement, Type, JsonDeserializationContext)} method
     * @return an Organization object
     * @throws JsonParseException            if json is not in the expected format
     * @throws IncorrectWorkerFieldException if a required field is missing or invalid
     */
    @Override
    public Organization deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
        JsonObject jsonObject = json.getAsJsonObject();

        //DataManager.ensureHas(jsonObject, "org_name");
        if (jsonObject.get("org_name").getAsString().isBlank()) {
            throw new IncorrectWorkerFieldException();
        }
        String orgName = jsonObject.get("org_name").getAsString();

        //DataManager.ensureHas(jsonObject, "annual_turnover");
        String preTurnover = jsonObject.get("annual_turnover").getAsString();
        //DataManager.ensureCorrect(DataType.INT, false, true, preTurnover);
        int turnover = Integer.parseInt(preTurnover);

        //DataManager.ensureHas(jsonObject, "employee_count");
        String preEmpCount = jsonObject.get("employee_count").getAsString();
        //DataManager.ensureCorrect(DataType.LONG, false, true, preEmpCount);
        long empCount = Long.parseLong(preEmpCount);

        //DataManager.ensureHas(jsonObject, "address");
        Address address = context.deserialize(jsonObject.get("address"), Address.class);

        return new Organization(orgName, turnover, empCount, address);
    }
}
