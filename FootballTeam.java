

public class FootballTeam {

    public static final FootBallTeamComparator FOOTBAL_TEAM_COMPARATOR = new FootBallTeamComparator();

    private double fifaPoints;
    private FootGroup footGroup;
    private String teamName;

    private int groupPoints;

    
    public FootballTeam(String teamName, FootGroup footGroup, double fifaPoints) {
        this.teamName = teamName;
        this.footGroup = footGroup;
        this.fifaPoints = fifaPoints;
    }


    public void setGroupPoints(int groupPoints) {
        this.groupPoints = groupPoints;
    }

    public int getGroupPoints() {
        return groupPoints;
    }

    public void updatePointAfterWin() {
        this.groupPoints += 3;
    }

    public void updatePointAfterDraw() {
        this.groupPoints += 1;
    }


    public double getFifaPoints(){
        return fifaPoints;
    }

    public FootGroup getFootGroup(){
        return footGroup;
    }

    public String getTeamName(){
        return teamName;
    }

}
