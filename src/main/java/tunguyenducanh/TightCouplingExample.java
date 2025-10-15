package tunguyenducanh;

import java.util.logging.Level;
import java.util.logging.Logger;

interface Printer {
    void print(String message);
}

final class ConsolePrinter implements Printer {
    private static final Logger LOG = Logger.getLogger(ConsolePrinter.class.getName());
    @Override
    public void print(String message) {
        LOG.log(Level.INFO, message);
    }
}

// Report là data holder bất biến -> dùng record gọn gàng
record Report(Printer printer) {
    public Report {
        if (printer == null) throw new IllegalArgumentException("printer must not be null");
    }
    void generate() {
        printer.print("Generating report...");
    }
}

public class TightCouplingExample {
    public static void main(String[] args) {
        Printer printer = new ConsolePrinter();
        Report report = new Report(printer);
        report.generate();
    }
}
