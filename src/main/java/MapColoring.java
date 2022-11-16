import java.util.List;

class MapColoring {
    private Map initialMap;
    private List<String> colors;

    MapColoring(Map initialMap, List<String> colors){
        this.initialMap = initialMap;
        this.colors = colors;

        // set the initial domains for each country
        initialMap.setCountriesDomain(colors);
    }

    /**
     * Colors the map using the given strategy,
     * and prints the result
     */
    void colorMap(){
        ArcConsistency ac;
        ac = new ArcConsistency(this.initialMap);
        Map result = ac.solve();
        System.out.println(result);
    }
}
