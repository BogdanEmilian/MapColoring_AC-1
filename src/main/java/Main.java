import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args){

        Map map = MapGenerator.generateMap();
        List<String> colors = new ArrayList<>(Arrays.asList("red","green","blue", "yellow"));

        MapColoring mapColoring = new MapColoring(map, colors);
        mapColoring.colorMap();
    }
}
