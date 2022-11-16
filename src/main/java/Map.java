import java.util.ArrayList;
import java.util.List;

public class Map {
    private List<Country> countries = new ArrayList<>();

    /**
     * Create a map with a list of countries
     *
     * @param countries
     */
    public Map(List<Country> countries){
        this.countries = countries;
    }


    /**
     * Copy constructor
     *
     * @param toClone
     */
    public Map (Map toClone) {
        for(Country countryToClone : toClone.getCountries()){
            Country country = new Country(countryToClone);
            this.countries.add(country);
        }
    }

    /**
     * Set domain for country
     *
     * @param colors
     */
    public void setCountriesDomain(List<String> colors){
        for (Country country : countries) {
            List<String> domain = new ArrayList<>(colors);
            country.setDomain(domain);
        }
    }


    /**
     * Returns the first country that doesn't have
     * a color assigned
     *
     * @return
     */
    public Country selectUnassignedCountry(){
        for (Country country : countries) {
            if(!country.hasColor()){
                return country;
            }
        }

        return null;
    }

    /**
     * Checks to see if all countries
     * have a color assigned
     *
     * @return
     */
    public boolean assignmentComplete(){
        for (Country country : countries) {
            if(!country.hasColor()){
                return false;
            }
        }

        return true;
    }

    /**
     * @return formatted countries
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        for(Country country : countries){
            builder.append(country.toString());
            builder.append("\n");
        }

        return builder.toString();
    }

    public List<Country> getCountries() {
        return countries;
    }
}
