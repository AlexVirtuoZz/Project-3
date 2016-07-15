package entities;

import java.util.List;

/**
 * Class represents beer object
 */
public class Beer {
    /**
     * id - beer's id
     * name - beer's name
     * manufacturer - beer's  manufacturer
     * alcoholic - shows if beer contains alcohol
     * ingredient - ingredients, which beer contains
     * characteristics - inner class which represents beer's characteristics
     */
    private int id;
    private String name;
    private String type;
    private String manufacturer;
    private boolean alcoholic;
    private List<String> ingredients ;
    private Chars characteristics = new Chars();

    public class Chars {
        /**
         * alcoholByVolume - beer's alcoholByVolume
         * transparency - beer's transparency in percentage
         * filtered - shows if beer is filtered
         * calories - beer's calories
         * container - beer's container
         */
        private byte alcoholByVolume;
        private byte transparency;
        private boolean filtered;
        private byte calories;
        private String container;

        //Required setters and getters

        public void setAlcoholByVolume(byte alcoholByVolume) {
            this.alcoholByVolume = alcoholByVolume;
        }

        public void setTransparency(byte transparency) {
            this.transparency = transparency;
        }

        public void setFiltered(boolean filtered) {
            this.filtered = filtered;
        }

        public void setCalories(byte calories) {
            this.calories = calories;
        }

        public void setContainer(String container) {
            this.container = container;
        }

        @Override
        public String toString() {
            return "Chars{" +
                    (alcoholic == true ? "alcoholByVolume=" + alcoholByVolume+" ," : "") +
                    "transparency=" + transparency +
                    ", filtered=" + filtered +
                    ", calories=" + calories +
                    ", container='" + container + '\'' +
                    '}';
        }
    }

    // Required setters and getters

    public List<String> getIngredients() {
        return ingredients;
    }

    public Chars getCharacteristics() {
        return characteristics;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setAlcoholic(boolean alcoholic) {
        this.alcoholic = alcoholic;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }

    @Override
    public String toString() {
        return "Beer{" +
                "id='" + id + '\'' +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", manufacturer='" + manufacturer + '\'' +
                ", alcoholic=" + alcoholic +
                ", ingredients=" + ingredients +
                ", characteristics=" + characteristics +
                '}';
    }
}
