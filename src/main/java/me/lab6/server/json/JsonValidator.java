package me.lab6.server.json;

import com.google.gson.JsonObject;
import me.lab6.common.exceptions.IncorrectWorkerFieldException;
import me.lab6.common.utility.DataType;
import me.lab6.common.utility.Limitations;
import me.lab6.common.utility.Validator;

public class JsonValidator {

    protected static void ensureCorrect(DataType type, boolean nullable, boolean positive, String string) {
        if (!Validator.validateData(string, new Limitations(type, nullable, positive))) {
            throw new IncorrectWorkerFieldException();
        }
    }

    protected static void ensureHas(JsonObject jOb, String field) {
        if (!jOb.has(field)) {
            throw new IncorrectWorkerFieldException();
        }
    }

}
