package parser.stax;

import entities.Beer;
import parser.BeerParser;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * Stax parser implementation to parse beers.xml document
 * implement {@link BeerParser}
 */
public class StaxBeerParser implements BeerParser {
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
         * xmlInputFactory - factory to build XMLStreamReader object
         * reader - object to parse document
         * view - View object to print when parsing is started and done
         * beerList - list of parsed beer objects
         */
        XMLInputFactory xmlInputFactory = XMLInputFactory.newFactory();
        XMLStreamReader reader = null;
        View view = new View();
        List<Beer> beerList = new LinkedList<>();
        try {
            /** initialize XMLStreamReader object with specified file path) **/
            reader = xmlInputFactory.createXMLStreamReader(new FileReader(filePath));
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        /**
         * currentContent - text content of current element
         * beer - an object to be parsed and added into list after parsing
         **/
        String currentContent = View.NULL;
        Beer beer = null;
        /**
         * Initialize and fill parameters into beer object according to current element tag
         * switch XMLStreamConstant
         * case START_DOCUMENT - print out about parse starting
         * case END_DOCUMENT - print out about parse ending
         * case START_ELEMENT - if current tag is View.BEER - initialize new Beer object and set its id
         *                      if current tag is View.INGREDIENTS - initialize beer's ingerdient list
         * case CHARACTERS - get text content of current element
         * case END_ELEMENT - set beer parameter according to element tag name with currentContent element
         *                      if current tag is View.BEER - add beer object into beerList
         */
            try {
                while (reader.hasNext()) {
                    int type = reader.next();

                    switch (type) {

                        case XMLStreamConstants.START_DOCUMENT:
                            view.print(View.START_PARSING);
                            break;

                        case XMLStreamConstants.END_DOCUMENT:
                            view.print(View.END_PARSING);
                            break;

                        case XMLStreamConstants.START_ELEMENT:
                            switch (reader.getLocalName()) {
                                case View.BEER:
                                    beer = new Beer();
                                    beer.setId(new Integer(reader.getAttributeValue(0)));
                                    break;
                                case View.INGREDIENTS:
                                    beer.setIngredients(new LinkedList());
                                    break;
                            }
                            break;

                        case XMLStreamConstants.CHARACTERS:
                            currentContent = reader.getText().trim();
                            break;

                        case XMLStreamConstants.END_ELEMENT:
                            switch (reader.getLocalName()) {
                                case View.NAME:
                                    beer.setName(currentContent);
                                    break;
                                case View.TYPE:
                                    beer.setType(currentContent);
                                    break;
                                case View.ALC:
                                    beer.setAlcoholic(new Boolean(currentContent));
                                    break;
                                case View.MANUFACTURER:
                                    beer.setManufacturer(currentContent);
                                    break;
                                case View.INGREDIENT:
                                    beer.getIngredients().add(currentContent);
                                    break;
                                case View.ALC_BY_VOLUME:
                                    beer.getCharacteristics().setAlcoholByVolume(new Byte(currentContent));
                                    break;
                                case View.TRANSPARENCY:
                                    beer.getCharacteristics().setTransparency(new Byte(currentContent));
                                    break;
                                case View.FILTERED:
                                    beer.getCharacteristics().setFiltered(new Boolean(currentContent));
                                    break;
                                case View.CALORIES:
                                    beer.getCharacteristics().setCalories(new Byte(currentContent));
                                    break;
                                case View.CONTAINER:
                                    beer.getCharacteristics().setContainer(currentContent);
                                    break;
                                case View.BEER:
                                    beerList.add(beer);
                                    break;
                            }
                            break;
                    }
                }
            } catch (XMLStreamException e) {
                e.printStackTrace();
            }
        return beerList;
    }
}

