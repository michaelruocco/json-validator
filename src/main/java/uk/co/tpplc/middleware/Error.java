package uk.co.tpplc.middleware;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jsonschema.core.report.ProcessingMessage;

public class Error {

    private final ProcessingMessage message;
    private final JsonNode json;

    public Error(ProcessingMessage message) {
        this.message = message;
        this.json = message.asJson();
    }

    public String getFullMessage() {
        return message.toString();
    }

    public JsonNode getJson() {
        return json;
    }

}
