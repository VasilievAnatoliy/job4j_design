package ru.job4j.ood.srp;

import org.junit.Test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class XMLReportTest {
    @Test
    public void whenXMLReport() {
        MemStore store = new MemStore();
        Calendar now = Calendar.getInstance();
        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX");
        Employee worker = new Employee("Ivan", now, now, 100);
        store.add(worker);
        Report engine = new XMLReport(store);
        StringBuilder expect = new StringBuilder()
                .append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>")
                .append("\n")
                .append("<employee>")
                .append("\n    ")
                .append("<employees>")
                .append("\n        ")
                .append("<fired>")
                .append(format.format(worker.getFired().getTime()))
                .append("</fired>")
                .append("\n        ")
                .append("<hired>")
                .append(format.format(worker.getHired().getTime()))
                .append("</hired>")
                .append("\n        ")
                .append("<name>")
                .append(worker.getName())
                .append("</name>")
                .append("\n        ")
                .append("<salary>")
                .append(worker.getSalary())
                .append("</salary>")
                .append("\n    ")
                .append("</employees>")
                .append("\n")
                .append("</employee>")
                .append("\n");
        assertThat(engine.generate(em -> true), is(expect.toString()));
    }
}
