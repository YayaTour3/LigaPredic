import java.util.List;

public class FootballSimApp {
    

    public static void main(String[] args)
    {
        System.out.println("Welcome!");
        
    
        List<FootGroup> allFootGroups = FootGroup.loadTournaments("config.csv");
     

        // Gruppen Phase Run
        boolean gameToPlayInGroup = false;
        do {
            gameToPlayInGroup = false;
            for (FootGroup fg : allFootGroups) {
                fg.runNextGame();
                gameToPlayInGroup |= fg.isGameAvailable();
            }
        }while (gameToPlayInGroup);

        for (FootGroup fg : allFootGroups) {
            fg.sortTeams();
            System.out.print(fg.groupTabelleToSting());
        }

        System.out.println("\n\n +++++ Start Secund Run ++++++");
        // Second Run
        SecondRunGraph scGraph = new SecondRunGraph(allFootGroups);

        while(scGraph.isNotFinish()){
            scGraph.runNextGame();
        }

        scGraph.showAllResult();


    }



}
