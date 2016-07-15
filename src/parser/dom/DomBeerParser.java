package parser.dom;

import entities.Beer;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import parser.BeerParser;
import view.View;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;


/**
 * Dom parser implementation to parse beers.xml document
 * implement {@link BeerParser}
 */
public class DomBeerParser implements BeerParser {
    //Overridden methods
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
         * documentBuilderFactory - factory to create documentBuilder
         * documentBuilder - builder to create document
         * document - document to parse through
         */
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = null;
        /** View object to print when parsing is started and done **/
        View view = new View();

        try {
            /**
             * Parse document to get information in a node list
             */
            document = documentBuilder.parse(new File(filePath));
        } catch (SAXException e) {
            e.printStackTrace();
        }

        /**
         * beer - an object to be parsed and added into list after parsing
         * beerList - list of parsed beer objects
         */
        Beer beer;
        List<Beer> beerList = new LinkedList<>();

        /** Convert parsed document into node list **/
        NodeList nodeList = document.getElementsByTagName(View.BEER);


        /**
         * print about parse starting
         * For each node named "beer"
         * convert node into element
         * create new beer object
         * set all beer parameters
         * create new element (charsElement) and set all beer characteristics obtained by charsElement
         * create new node list (ingredientNodeList) and for each node - add element into beer ingredient list
         * add beer object into beerList
         * print about parse finishing
         * return beerList
         */
        view.print(View.START_PARSING);
        for (int i = 0; i < nodeList.getLength(); i++){
            Node node = nodeList.item(i);
            Element element = (Element) node;

            beer = new Beer();
            beer.setIngredients(new LinkedList<>());

            beer.setId(new Integer(element.getAttribute(View.ID)));
            beer.setName(getElementTextContent(element, View.NAME));
            beer.setType(getElementTextContent(element, View.TYPE));
            beer.setManufacturer(getElementTextContent(element, View.MANUFACTURER));
            beer.setAlcoholic(new Boolean(getElementTextContent(element, View.ALC)));

            Element charsElement = (Element) element.getElementsByTagName(View.CHARS).item(0);
            beer.getCharacteristics().setAlcoholByVolume(new Byte(getElementTextContent(charsElement, View.ALC_BY_VOLUME)));
            beer.getCharacteristics().setCalories(new Byte(getElementTextContent(charsElement, View.CALORIES)));
            beer.getCharacteristics().setFiltered(new Boolean(getElementTextContent(charsElement, View.FILTERED)));
            beer.getCharacteristics().setTransparency(new Byte(getElementTextContent(charsElement, View.TRANSPARENCY)));
            beer.getCharacteristics().setContainer(getElementTextContent(charsElement, View.CONTAINER));

            NodeList ingredientNodeList = element.getElementsByTagName(View.INGREDIENT);

            for (int j = 0; j < ingredientNodeList.getLength(); j++){
                beer.getIngredients().add(ingredientNodeList.item(j).getTextContent());
            }

            beerList.add(beer);

        }
        view.print(View.END_PARSING);
        return beerList;
    }

    /**
     * A method to get text information from element object
     * @param element specified element
     * @param value tag value
     * @return text information from element object
     */
    private String getElementTextContent(Element element, String value){
        return element.getElementsByTagName(value).item(0).getTextContent();
    }

}
