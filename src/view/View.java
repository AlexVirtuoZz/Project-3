package view;

/**
 * A class which works with text constants and method to handle them
 */
public class View {
    //System constants
    public static final String START_PARSING = "Starting parsing...";
    public static final String END_PARSING = "Parsing done";

    //XML tag names
    public static final String ID = "id";
    public static final String BEER = "beer";
    public static final String NAME = "name";
    public static final String TYPE = "type";
    public static final String ALC = "alc";
    public static final String MANUFACTURER = "manufacturer";
    public static final String INGREDIENTS = "ingredients";
    public static final String INGREDIENT = "ingredient";
    public static final String CHARS = "chars";
    public static final String ALC_BY_VOLUME = "alc-by-volume";
    public static final String TRANSPARENCY = "transparency";
    public static final String FILTERED = "filtered";
    public static final String CALORIES = "calories";
    public static final String CONTAINER = "container";

    //Utility constants
    public static final String FILEPATH = "src/xml/beers.xml";
    public static final String NULL = "";

    /**
     * A method to print out messages
     * @param value message value
     */
    public void print(String value){
        System.out.println(value);
    }
}
