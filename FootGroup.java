import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class FootGroup {

    private static final String COMMA_DELIMITER = ";";
    private String groupName;
    private List<FootballTeam> teamList;
    private List<FootGame> gameList;
    private Iterator<FootGame> gameListIterator;

    public FootGroup(String grpName) {
        this.groupName = grpName;
        teamList = new ArrayList<FootballTeam>();
        gameList = new ArrayList<FootGame>();
    }

    public boolean isGameAvailable() {
        return gameListIterator!=null && gameListIterator.hasNext();
        
    }

    public void runNextGame() {
        
        initGameList();
        
        if(gameListIterator.hasNext()){
            FootGame fg = gameListIterator.next();
            fg.runGame();
        }
    }

    public String groupTabelleToSting()
    {
        String res = "";
        for(FootballTeam ft: teamList)
        {
            res+=ft.getTeamName()+"\t"+ft.getGroupPoints()+"\n";
        }
        return "\nGroup "+groupName+
                "\n"
                +res;
    }
    private void initGameList() {
        
        if(!gameList.isEmpty()) {
            return;
        }

        gameList.add(new FootGame(teamList.get(0), teamList.get(1)));
        gameList.add(new FootGame(teamList.get(0), teamList.get(2)));
        gameList.add(new FootGame(teamList.get(0), teamList.get(3)));
        gameList.add(new FootGame(teamList.get(1), teamList.get(2)));
        gameList.add(new FootGame(teamList.get(1), teamList.get(3)));
        gameList.add(new FootGame(teamList.get(2), teamList.get(3)));

        gameListIterator = gameList.iterator();
    }

    public void addNewTeam(String[] values) {
        FootballTeam ft = new FootballTeam(values[0].trim(), this, Double.valueOf(values[2].trim()));
        teamList.add(ft);
    }

    public boolean nameIs(String grpName) {
        return groupName.equals(grpName);
    }


    static List<FootGroup> loadTournaments(String pfadCsvTournamentString) {
        
        List<FootGroup> allFootGroups = new LinkedList<FootGroup>();
        
        try (BufferedReader br = new BufferedReader(new FileReader(pfadCsvTournamentString))) {
            String line = br.readLine();//Skip the first line
            while ((line = br.readLine()) != null) {
                String[] values = line.split(COMMA_DELIMITER);
                String grpName = values[1].trim();
                FootGroup gp = getOrAddGroupByName(allFootGroups, grpName);
                gp.addNewTeam(values);
            }
        } catch(Exception e){
            e.printStackTrace();
        }
    
        
        return allFootGroups;
    }

    private static FootGroup getOrAddGroupByName(List<FootGroup> allFootGroups, String grpName) {
        for(FootGroup gp: allFootGroups)
        {
            if(gp.nameIs(grpName)){
                return gp;
            }
        }

        FootGroup newGroup = new FootGroup(grpName);
        allFootGroups.add(newGroup);
        return newGroup;
    }

    public FootballTeam getTeamAtPosition(int ranking) {
        return teamList.get(teamList.size() - ranking);
    }

    public String getGroupName() {
        return this.groupName;
    }

    public void sortTeams() {
        teamList.sort(FootballTeam.FOOTBAL_TEAM_COMPARATOR);
    }

}
