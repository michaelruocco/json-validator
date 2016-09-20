package uk.co.tpplc.middleware;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class ValidatorTest {

    private static final String INVALID_TYPE_PRODUCT_PATH = "example-json/invalid-data-type-product.json";
    private static final String INVALID_MISSING_FIELD_PRODUCT_PATH = "example-json/invalid-missing-field-product.json";

    private final Validator validator = new ProductValidator();
    private final FileContentLoader contentLoader = new FileContentLoader();

    @Test
    public void shouldVerifyJsonIsValid() {
        String validJson = contentLoader.load("example-json/valid-product.json");

        Result result = validator.validate(validJson);

        assertThat(result.isValid()).isTrue();
    }

    @Test
    public void shouldVerifyJsonHasInvalidDataType() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        assertThat(result.isValid()).isFalse();
    }

    @Test
    public void shouldReturnValidationErrorForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        assertThat(errors.size()).isEqualTo(1);
    }

    @Test
    public void shouldReturnFullMessageForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getFullMessage()).isEqualTo("error: instance type (string) does not match any allowed primitive type (allowed: [\"integer\",\"number\"])\n" +
                "    level: \"error\"\n" +
                "    schema: {\"loadingURI\":\"http://json-schema.org/geo#\",\"pointer\":\"/properties/latitude\"}\n" +
                "    instance: {\"pointer\":\"/1/warehouseLocation/latitude\"}\n" +
                "    domain: \"validation\"\n" +
                "    keyword: \"type\"\n" +
                "    found: \"string\"\n" +
                "    expected: [\"integer\",\"number\"]\n");
    }

    @Test
    public void shouldReturnErrorLevelForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("level").asText()).isEqualTo("error");
    }

    @Test
    public void shouldReturnSchemaLoadingUriForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("schema").get("loadingURI").asText()).isEqualTo("http://json-schema.org/geo#");
    }

    @Test
    public void shouldReturnSchemaPointerForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("schema").get("pointer").asText()).isEqualTo("/properties/latitude");
    }

    @Test
    public void shouldReturnInstancePointerForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("instance").get("pointer").asText()).isEqualTo("/1/warehouseLocation/latitude");
    }

    @Test
    public void shouldReturnDomainForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("domain").asText()).isEqualTo("validation");
    }

    @Test
    public void shouldReturnKeywordForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("keyword").asText()).isEqualTo("type");
    }

    @Test
    public void shouldReturnTypeFoundForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("found").asText()).isEqualTo("string");
    }

    @Test
    public void shouldReturnExpectedTypesForInvalidDataTypeJson() {
        String invalidJson = loadInvalidDataTypeJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("expected").get(0).asText()).isEqualTo("integer");
        assertThat(error.getJson().get("expected").get(1).asText()).isEqualTo("number");
    }

    @Test
    public void shouldReturnFullMessageForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getFullMessage()).isEqualTo("error: object has missing required properties ([\"price\"])\n" +
                "    level: \"error\"\n" +
                "    schema: {\"loadingURI\":\"#\",\"pointer\":\"/items\"}\n" +
                "    instance: {\"pointer\":\"/0\"}\n" +
                "    domain: \"validation\"\n" +
                "    keyword: \"required\"\n" +
                "    required: [\"id\",\"name\",\"price\"]\n" +
                "    missing: [\"price\"]\n");
    }

    @Test
    public void shouldReturnErrorLevelForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("level").asText()).isEqualTo("error");
    }

    @Test
    public void shouldReturnSchemaLoadingUriForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("schema").get("loadingURI").asText()).isEqualTo("#");
    }

    @Test
    public void shouldReturnSchemaPointerForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("schema").get("pointer").asText()).isEqualTo("/items");
    }

    @Test
    public void shouldReturnInstancePointerForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("instance").get("pointer").asText()).isEqualTo("/0");
    }

    @Test
    public void shouldReturnDomainForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("domain").asText()).isEqualTo("validation");
    }

    @Test
    public void shouldReturnKeywordForMissingFieldJson() {
        String invalidJson = loadInvalidMissingFieldJson();

        Result result = validator.validate(invalidJson);

        List<Error> errors = result.getErrors();
        Error error = errors.get(0);
        assertThat(error.getJson().get("keyword").asText()).isEqualTo("required");
    }

    @Test
    public void shouldIgnoreExtraFieldsWhenValidating() { //not sure I like this, would prefer if it failed
        String json = contentLoader.load("example-json/valid-extra-field-product.json");

        Result result = validator.validate(json);

        assertThat(result.isValid()).isTrue();
    }

    @Test(expected = ValidationException.class)
    public void shouldThrowExceptionIfSyntaxIsInvalid() {
        String invalidJson = contentLoader.load("example-json/invalid-syntax-product.json");

        validator.validate(invalidJson);
    }

    private String loadInvalidDataTypeJson() {
        return contentLoader.load(INVALID_TYPE_PRODUCT_PATH);
    }

    private String loadInvalidMissingFieldJson() {
        return contentLoader.load(INVALID_MISSING_FIELD_PRODUCT_PATH);
    }

}
