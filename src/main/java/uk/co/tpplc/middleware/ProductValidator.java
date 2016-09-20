package uk.co.tpplc.middleware;

public class ProductValidator extends Validator {

    private static final String SCHEMA_PATH = "schema/product-schema.json";

    public ProductValidator() {
        super(SCHEMA_PATH);
    }

}
