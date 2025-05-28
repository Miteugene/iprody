package org.example.lesson24.framework.printers;

import org.example.lesson24.framework.executions.Execution;
import org.example.lesson24.framework.printers.interfaces.PrinterInterface;

import java.util.List;

public abstract class Printer implements PrinterInterface {
    protected String template;

    public Printer(String template) {
        this.template = template;
    }
}
