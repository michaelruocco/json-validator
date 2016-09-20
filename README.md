# JSON Validator

This project is an attempt at giving a simple API to use when trying to
validate JSON data against a [json schema](http://json-schema.org/).

It is essentially involves creating a wrapper around an [existing validation
library](https://github.com/daveclayton/json-schema-validator) for a specific
scenario. In the example used for this project a simple product schema is
used.

It might be that if we decide to make use of this approach that we don't
create specific wrappers and just use the library directly, although some
kind of wrapper would probably make it easier to test against if nothing else.
The main purpose of this project was to see how easy it is to do. With a view
to potentially plugin something into an WSO2 API Handler or Mediator depending
on the scenario. The next step would be to try something out directly in WSO2
if possible.

## Running the Tests

You can run the tests for this project by running the following command:

```
gradlew clean build
```