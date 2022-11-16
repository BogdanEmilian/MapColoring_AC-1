import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class Country {
    private List<Country> neighbours = new ArrayList<>();
    private String name = null;
    private String color = null;
    private List<String> domain = new ArrayList<>();

    /**
     * Initialize country with name
     *
     * @param name
     */
    public Country(String name){
        this.name = name;
    }

    /**
     * Copy constructor
     *
     * @param toClone
     */
    public Country(Country toClone){
        this.name = toClone.name;

        // set the domain
        List<String> domain = new ArrayList<>(toClone.getDomain());
        this.setDomain(domain);

        // set the neighbours
        List<Country> neighbours = new ArrayList<>();
        for(Country neighbour : toClone.getNeighbours()){
            neighbours.add(new Country(neighbour.name));
        }
        this.setNeighbours(neighbours);
    }

    /**
     * Unassigned neighbours
     *
     * @return
     */
    public List<Country> getUnassignedNeighbours() {
        List<Country> unassignedNeighbours = new ArrayList<>();

        for(Country neighbour : neighbours){
            if(!neighbour.hasColor()){
                unassignedNeighbours.add(neighbour);
            }
        }

        return unassignedNeighbours;
    }

    public List<Country> getNeighbours() {
        return neighbours;
    }

    public void setNeighbours(Country... neighbours) {
        this.neighbours = Arrays.asList(neighbours);
    }

    public void setNeighbours(List<Country> neighbours) {
        this.neighbours = neighbours;
    }

    public String getName() {
        return name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;

        List<String> domain = new ArrayList<>();
        domain.add(color);
        this.setDomain(domain);
    }

    public List<String> getDomain() {
        return domain;
    }

    public void setDomain(List<String> domain) {
        this.domain = domain;
    }

    /**
     * Adds color back to domain
     *
     * @param color
     */
    public void addColorToDomain(String color) {
        domain.add(color);
    }

    /**
     * Removes the color from the domain
     *
     * @param color
     */
    public void removeColorFromDomain(String color) {
        for (Iterator<String> iter = domain.iterator(); iter.hasNext(); ) {
            String c = iter.next();
            if (c.equals(color)) {
                iter.remove();
            }
        }
    }

    /**
     * Checks to see if we have a color assigned
     *
     * @return
     */
    public boolean hasColor(){
        return this.color != null;
    }


    /**
     * Checks to see if we can have this
     * color by checking the neighbours
     *
     * @param color
     * @return
     */
    public boolean canHaveColor(String color){
        for(Country neighbour : neighbours){
            if(color.equals(neighbour.getColor())){
                return false;
            }
        }

        return true;
    }


    /**
     * @return formatted country status
     */
    public String toString() {
        StringBuilder builder = new StringBuilder();

        builder.append("Country: ").append(this.getName()).append(", ").append("color: ")
                .append(this.getColor())
                .append("\n");
        builder.append("\tNeighbours: \n");
        for(Country neighbour : neighbours){
            builder.append("\t\t")
                    .append(neighbour.getName())
                    .append(", ")
                    .append("color: ")
                    .append(neighbour.getColor())
                    .append("\n");
        }

        return builder.toString();
    }
}
