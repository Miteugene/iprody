package org.example.lesson24.framework.printers.interfaces;

import org.example.lesson24.framework.executions.Execution;

import java.util.List;

public interface PrinterInterface {
    public void write(List<Execution> executions);
}
