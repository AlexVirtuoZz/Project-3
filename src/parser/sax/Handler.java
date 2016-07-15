package parser.sax;

import entities.Beer;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import view.View;

import java.util.LinkedList;
import java.util.List;

/**
 * An object to handle sax parser parsing
 */
public class Handler extends DefaultHandler {
    /**
     * beer -  beer - an object to be parsed and added into list after parsing
     * currentTag - name of current xml tag which is being parsed
     * view - View object to print when parsing is started and done
     * beerList - list of parsed beer objects
     */
    private Beer beer;
    private String currentTag;
    private List<Beer> beerList = new LinkedList<>();
    private View view = new View();

    // Required getters and setters
    public List<Beer> getBeerList() {
        return beerList;
    }

    /**
     * Method executes when document parsing started
     * @throws SAXException
     */
    @Override
    public void startDocument() throws SAXException{
        view.print(View.START_PARSING);
    }

    /**
     * Method executes when document parsing finished
     * @throws SAXException
     */
    @Override
    public void endDocument() throws SAXException{
        view.print(View.END_PARSING);
    }

    /**
     * Method executes when new element is found
     * Mark current tag with element's qName
     * if qName is View.BEER - create new beer object and set its id
     * @param namespace element namespace
     * @param localname element localname
     * @param qName element name
     * @param attributes element attributes
     */
    @Override
    public void startElement(String namespace, String localname, String qName, Attributes attributes){
        currentTag = qName;
        if (qName.equals(View.BEER)) {
            beer = new Beer();
            beer.setId(new Integer(attributes.getValue(0)));
        } else if (qName.equals(View.INGREDIENTS))
            beer.setIngredients(new LinkedList());
    }

    /**
     * Method executes when closing element tag is found
     * if element's qName is View.BEER - add beer object into beerList
     * set currentTag as View.NULL
     * @param namespace element namespace
     * @param localname element localname
     * @param qName element name
     * @throws SAXException
     */
    @Override
    public void endElement(String namespace, String localname, String qName) throws SAXException{
        if (qName.equals(View.BEER))
            beerList.add(beer);
        currentTag = View.NULL;
    }

    /**
     * A method to get text content from element
     * create new temporary string object containing element text content
     * According to current tag set beer parameter with temporary string value
     * @param chars array of chars of element
     * @param start start index of array
     * @param end end index of array
     */
    @Override
    public void characters(char[] chars, int start, int end){
        String tmp = new String(chars, start, end);
        switch (currentTag){
            case View.NAME :
                beer.setName(tmp);
                break;
            case View.TYPE :
                beer.setType(tmp);
                break;
            case View.ALC :
                beer.setAlcoholic(new Boolean(tmp));
                break;
            case View.MANUFACTURER :
                beer.setManufacturer(tmp);
                break;
            case View.INGREDIENT:
                beer.getIngredients().add(tmp);
                break;
            case View.ALC_BY_VOLUME :
                beer.getCharacteristics().setAlcoholByVolume(new Byte(tmp));
                break;
            case View.TRANSPARENCY :
                beer.getCharacteristics().setTransparency(new Byte(tmp));
                break;
            case View.FILTERED :
                beer.getCharacteristics().setFiltered(new Boolean(tmp));
                break;
            case View.CALORIES :
                beer.getCharacteristics().setCalories(new Byte(tmp));
                break;
            case View.CONTAINER :
                beer.getCharacteristics().setContainer(tmp);
        }
    }
}
