package parser.sax;

import entities.Beer;
import org.xml.sax.SAXException;
import parser.BeerParser;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Sax parser implementation to parse beers.xml document
 * implement {@link BeerParser}
 */
public class SaxBeerParser implements BeerParser {
    /**
     * Main method to parse document
     * @param filePath xml file path
     * @return list of created objects
     * @throws ParserConfigurationException
     * @throws IOException
     */
    @Override
    public List<Beer> parse(String filePath) throws ParserConfigurationException, IOException {
        /**
         * factory - object to create factory
         * handler - object to handle parsing
         * parser - sax parser
         */
        SAXParserFactory factory = SAXParserFactory.newInstance();
        Handler handler = new Handler();
        SAXParser parser;
        try {
            /**
             * create new parser
             * parse required document
             */
            parser = factory.newSAXParser();
            parser.parse(new File(filePath), handler);
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return handler.getBeerList();
    }
}

