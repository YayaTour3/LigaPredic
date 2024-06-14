import java.util.Comparator;

public class FootBallTeamComparator implements Comparator<FootballTeam> {

    /**
     * TODO: Add more criteria to compare two team in competition:
     *  - Points
     *  - Goals Diff
     *  - Set Goals
     *  - receive Goals
     *  - Fairplay 
     *  - etc
     * 
     */

    @Override
    public int compare(FootballTeam o1, FootballTeam o2) {
           
            return o1.getGroupPoints() - o2.getGroupPoints();
    }



}
