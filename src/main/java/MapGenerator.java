import java.util.ArrayList;
import java.util.Arrays;

final public class MapGenerator {
    private MapGenerator() {
        throw new AssertionError();
    }

    static public Map generateMap(){
        ArrayList<Country> countries = new ArrayList<>();
//
//        Country A = new Country("1");
//        Country B = new Country("2");
//        Country C = new Country("3");
//        Country D = new Country("4");
//        Country E = new Country("5");
//
//        // add neighbours
//        A.setNeighbours(B, E);
//        B.setNeighbours(A, C, D, E);
//        C.setNeighbours(B, D, E);
//        D.setNeighbours(B, C, E);
//        E.setNeighbours(A, B, C, D);


        Country A = new Country("1");
        Country B = new Country("2");
        Country C = new Country("3");
        Country D = new Country("4");

        A.setNeighbours(B, D);
        B.setNeighbours(A, C);
        C.setNeighbours(B, D);
        D.setNeighbours(A, C);

        countries.addAll(Arrays.asList(A, B, C, D));
        return new Map(countries);
    }
}
