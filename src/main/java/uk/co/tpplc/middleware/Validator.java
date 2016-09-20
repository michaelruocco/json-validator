package uk.co.tpplc.middleware;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;
import java.io.UncheckedIOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Validator {

    private final JsonSchemaFactory schemaFactory = JsonSchemaFactory.byDefault();
    private final JsonSchema schema;

    public Validator(String schemaPath) {
        schema = toSchema(schemaPath);
    }

    private String loadFileContent(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private JsonNode toNode(String json) {
        try {
            return JsonLoader.fromString(json);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    private JsonSchema toSchema(String path) {
        try {
            String content = loadFileContent(path);
            JsonNode node = toNode(content);
            return schemaFactory.getJsonSchema(node);
        } catch (ProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public Result validate(String json) {
        try {
            JsonNode data = JsonLoader.fromString(json);
            ProcessingReport report = schema.validate(data, true);
            return new Result(report);
        } catch (IOException | ProcessingException e) {
            throw new ValidationException(e);
        }
    }

}
