
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecondRunGraph {


    final Map<String, int[]> mp = new HashMap<>();
    List<FootGame> secondRunList = new LinkedList<>();
    private Iterator<FootGame> gameIter;
    FootGame ffinal;
                  

    public SecondRunGraph(List<FootGroup> allFootGroups) {

        // Base on https://de.wikipedia.org/wiki/Fu%C3%9Fball-Europameisterschaft_2021#Einordnung_der_qualifizierten_Gruppendritten_in_das_Achtelfinale
        mp.put("ABCD", new int[]{0,3,1,2});
        mp.put("ABCE", new int[]{0,4,1,2});
        mp.put("ABCF", new int[]{0,5,1,2});
        mp.put("ABDE", new int[]{3,5,0,1});
        mp.put("ABDF", new int[]{3,5,0,1});
        mp.put("ABEF", new int[]{4,5,1,0});
        mp.put("ACDE", new int[]{4,3,2,0});
        mp.put("ACDF", new int[]{5,3,2,0});
        mp.put("ACEF", new int[]{4,5,2,0});
        mp.put("ADEF", new int[]{4,5,3,0});
        mp.put("BCDE", new int[]{4,3,1,2});
        mp.put("BCDF", new int[]{0,3,2,1});
        mp.put("BCEF", new int[]{0,4,2,1});
        mp.put("BDEF", new int[]{0,4,3,1});
        mp.put("CDEF", new int[]{0,4,3,2});

        String best4thirth = getBest3AsString(allFootGroups);
        
        FootGame f1 = new FootGame(1, allFootGroups.get(0), 2, allFootGroups.get(2));
        FootGame f2 = new FootGame(1, allFootGroups.get(1), 3, getGroup(best4thirth, 1, allFootGroups));
        FootGame ff1 = new FootGame(f1, f2);
        FootGame f3 = new FootGame(2, allFootGroups.get(3), 2, allFootGroups.get(4));
        FootGame f4 = new FootGame(1, allFootGroups.get(5), 3, getGroup(best4thirth, 5, allFootGroups));
        FootGame ff2 = new FootGame(f3, f4);
        FootGame fff1 = new FootGame(ff1, ff2);
        
        FootGame f5 = new FootGame(2, allFootGroups.get(0), 2, allFootGroups.get(1));
        FootGame f6 = new FootGame(1, allFootGroups.get(2), 3, getGroup(best4thirth, 2, allFootGroups));
        FootGame ff3 = new FootGame(f5, f6);
        FootGame f7 = new FootGame(1, allFootGroups.get(4), 3, getGroup(best4thirth, 4, allFootGroups));
        FootGame f8 = new FootGame(1, allFootGroups.get(3), 2, allFootGroups.get(5));
        FootGame ff4 = new FootGame(f7, f8);
        FootGame fff2 = new FootGame(ff3, ff4);

        ffinal = new FootGame(fff1, fff2);
        secondRunList.add(f1);
        secondRunList.add(f2);
        secondRunList.add(f3);
        secondRunList.add(f4);
        secondRunList.add(f5);
        secondRunList.add(f6);
        secondRunList.add(f7);
        secondRunList.add(f8);
        secondRunList.add(ff1);
        secondRunList.add(ff2);
        secondRunList.add(ff3);
        secondRunList.add(ff4);
        secondRunList.add(fff1);
        secondRunList.add(fff2);
        secondRunList.add(ffinal);

        gameIter = secondRunList.iterator();

    }

    private FootGroup getGroup(String best4thirth, int groupidxFirstTeam, List<FootGroup> allFootGroups) {
        

         Set<String> kSet = mp.keySet();
        int[] value = null;
        for(String k: kSet)
        {
            if(k.equals(best4thirth)){
                value = mp.get(best4thirth);
                break;
            }

            boolean isFound = true;
            for (char c: best4thirth.toCharArray()) {
                if(k.indexOf(c)<0){
                    isFound = false;
                    break;
                }
            }
            if(isFound){
                value = mp.get(k);
                break;
            }

        }

        switch (groupidxFirstTeam) {
            case 1:
                return allFootGroups.get(value[0]);
            case 2:
                return allFootGroups.get(value[1]);
            case 4:
                return allFootGroups.get(value[2]);
            case 5:
                return allFootGroups.get(value[3]);
            default:
                break;
        }
        return null;
    }



    private String getBest3AsString(List<FootGroup> allFootGroups) {
        List<FootballTeam> thirtFootballTeams = new LinkedList<>();
        for(FootGroup fg: allFootGroups){
            thirtFootballTeams.add(fg.getTeamAtPosition(3));// Get the 3te
        }

        thirtFootballTeams.sort(FootballTeam.FOOTBAL_TEAM_COMPARATOR);

        return  thirtFootballTeams.get(5).getFootGroup().getGroupName()+
                thirtFootballTeams.get(4).getFootGroup().getGroupName()+
                thirtFootballTeams.get(3).getFootGroup().getGroupName()+
                thirtFootballTeams.get(2).getFootGroup().getGroupName();

    }

    public void showAllResult() {
        
        FootballTeam ww = ffinal.getWinner();
        System.out.println("\n"+
                            "############################"+
                            "\n"+
                            "The Winner is:"+
                            ww.getTeamName()+
                            "\n"+
                            "############################");
    }

    public boolean isNotFinish() {
        return gameIter.hasNext();
    }

    public void runNextGame() {
        
        gameIter.next().runGameChaos();
    }

}
