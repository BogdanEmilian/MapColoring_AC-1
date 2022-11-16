import java.util.*;

public class ArcConsistency  {
    protected Map map;

    public ArcConsistency(Map map){
        this.map=map;
    }

    public Map solve(){
        return this.backtrackingWithArcConsistency(map);
    }

    protected boolean haveADomainValue(List<Country> neighbours){
        for(Country neighbour : neighbours){
            if(neighbour.getDomain().isEmpty()){
                return false;
            }
        }

        return true;
    }

    /**
     * Backtracking with arc consistency
     *
     * @param map
     * @return
     */
    private Map backtrackingWithArcConsistency(Map map){
        if(map.assignmentComplete()){
            return map;
        }

        Country country = map.selectUnassignedCountry();

        List<String> domain = new ArrayList<>(country.getDomain());
        for(String color : domain){
            if(country.canHaveColor(color)){
                // set color and forward check on domains
                country.setColor(color);

                List<Country> unassignedNeighbours = country.getUnassignedNeighbours();
                for(Country neighbour : unassignedNeighbours){
                    neighbour.removeColorFromDomain(color);
                }

                // keep the arc consistency
                Map mapBackup = new Map(map);
                map = this.ac1(map);

                // we proceed only if we all neighbours have
                // at least one value in the domain
                if(this.haveADomainValue(country.getUnassignedNeighbours())){
                    Map result = backtrackingWithArcConsistency(map);
                    if(result != null) return result;
                }

                // backtrack
                country.setColor(null);
                for(Country neighbour : unassignedNeighbours){
                    neighbour.addColorToDomain(color);
                }
                map = mapBackup;
            }
        }

        return null;
    }


    /**
     * Arc consistency algorithm
     *
     * @param map
     * @return
     */
    private Map ac1(Map map){
        Queue<Pair<Country,Country>> arcs = this.getInitialQueue(map);
        Pair<Country,Country> arc;
        boolean change;

        /*
        AC-3
        while(!arcs.isEmpty()){
            arc = arcs.remove();

            if(this.removeInconsistentValues(arc)){
                List<Country> neighbours = arc.first.getNeighbours();
                for(Country neighbour : neighbours){
                    arcs.add(new Pair<>(neighbour, arc.first));
                }
            }
        }
        */

        //AC-1
        do{
            change = false;
            arc = arcs.remove();

            List<Country> neighbours = arc.first.getNeighbours();
            for (Country neighbour : neighbours) {
                change = (this.revise(arc) || change);
            }
        }while(change);

        return map;
    }

    /**
     * Removes inconsistencies
     *
     * @param arc
     * @return
     */
    private boolean revise(Pair<Country,Country> arc){
        boolean removed = false;

        List<String> domain = new ArrayList<>(arc.first.getDomain());
        for(String colorFirst : domain){

            boolean flag = true;
            for(String colorSecond : arc.second.getDomain()){
                if(!colorSecond.equals(colorFirst)){
                    flag = false;
                    break;
                }
            }

            // we only have the exact color, so we need to remove it
            if(flag){
                arc.first.removeColorFromDomain(colorFirst);
                removed = true;
            }

        }

        return removed;
    }

    /**
     * Returns initial queue of arcs
     *
     * @param map
     * @return
     */
    private Queue<Pair<Country,Country>> getInitialQueue(Map map){
        Queue<Pair<Country,Country>> arcs = new LinkedList<>();

        for(Country country : map.getCountries()){
            for(Country neighbour : country.getNeighbours()){
                Pair<Country,Country> arc = new Pair<>(country, neighbour);
                arcs.add(arc);
            }
        }

        return arcs;
    }
}
