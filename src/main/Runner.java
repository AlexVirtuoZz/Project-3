package main;

import entities.Beer;
import parser.dom.DomBeerParser;
import parser.sax.SaxBeerParser;
import parser.stax.StaxBeerParser;
import view.View;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

/**
 * A class to test {@link parser.BeerParser} parsers
 */
public class Runner {
    private List<Beer> beerList = new LinkedList<>();
    private View view = new View();
    private StaxBeerParser staxParser = new StaxBeerParser();
    private SaxBeerParser saxParser = new SaxBeerParser();
    private DomBeerParser domParser = new DomBeerParser();

    /**
     * A method to run parsers
     * After parsers' job is done
     * print out all parsed objects from beerList
     */
    void run(){
        try {
            beerList.addAll(staxParser.parse(View.FILEPATH));
            beerList.addAll(saxParser.parse(View.FILEPATH));
            beerList.addAll(domParser.parse(View.FILEPATH));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Beer beer : beerList)
            view.print(beer.toString());
    }
}
