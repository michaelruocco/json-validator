package uk.co.tpplc.middleware;

import com.github.fge.jsonschema.core.report.ProcessingMessage;
import com.github.fge.jsonschema.core.report.ProcessingReport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Result {

    private final ProcessingReport report;

    public Result(ProcessingReport report) {
        this.report = report;
    }

    public boolean isValid() {
        return report.isSuccess();
    }

    public List<Error> getErrors() {
        if (isValid())
            return Collections.emptyList();
        return toErrors(report);
    }

    private static List<Error> toErrors(ProcessingReport report) {
        Iterator<ProcessingMessage> iterator = report.iterator();
        List<Error> nodes = new ArrayList<>();
        if (iterator.hasNext()) {
            ProcessingMessage message = iterator.next();
            nodes.add(new Error(message));
        }
        return nodes;
    }

}
