package parser;


import entities.Beer;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

/**
 * An interface to generify parsers which parse beers.xml document
 */
public interface BeerParser {
    List<Beer> parse(String filePath) throws ParserConfigurationException, IOException;
}
