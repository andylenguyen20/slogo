package Experiment;

import parsers.Parser;

import java.util.function.Consumer;

public interface ParserActionDelegate {
    void performParserAction(Consumer<Parser> p);
}
