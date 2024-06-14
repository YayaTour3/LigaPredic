import java.util.Random;

public class FootGame {

    private static final int FIFA_POINTS_DELTA = 1000;
    private FootballTeam tA;
    private FootballTeam tB;
    private int totalGoalsTeamA;
    private int totalGoalsTeamB;

    private boolean alwaysPlay = false;

    private FootGame pf1;
    private FootGame pf2;

    
    public FootGame(int rangInGrp1, FootGroup footGroup, int rangInGrp2, FootGroup footGroup2) {
        tA = footGroup.getTeamAtPosition(rangInGrp1);
        tB = footGroup2.getTeamAtPosition(rangInGrp2);
    }

    public FootGame(FootballTeam tA, FootballTeam tB){
        this.tA = tA;
        this.tB = tB;
    }

    public FootGame(FootGame f1, FootGame f2) {
        pf1 = f1;
        pf2 = f2;
    }

    /**
     * Run the game. Base to the Fifa Ranking.
     *  goalTeam = lambda_Team /(1-Random(0 ... 1))
     *  lambda_Team = fifaRanking_Team / (Summe of both Ranking)
     * TODO: Add more Criteria, like previous game, form of each player
     * TODO: Add the possibility to parametrize and use other function to generate the goals of each team
     */
    public void runGame() {

        if(!alwaysPlay){
            if(pf1!=null && pf2!=null)
            {
                tA = pf1.getWinner();
                tB = pf2.getWinner();
            }
            
            System.out.println("-------------------------------------------");
            System.out.println(tA.getTeamName()+" of Group "+tA.getFootGroup().getGroupName()+" VS "+tB.getTeamName()+" of Group "+tB.getFootGroup().getGroupName());
            double fpA = tA.getFifaPoints()  - FIFA_POINTS_DELTA;
            double fpB = tB.getFifaPoints()  - FIFA_POINTS_DELTA;

            Random r = new Random();
            double ndouble1 = r.nextDouble();
            double ndouble2 = r.nextDouble();

        
            double lambda1 = fpA/(fpA+fpB);
            double lambda2 = fpB/(fpA+fpB);
            
            totalGoalsTeamA = calculateGoal(ndouble1, lambda1);
            totalGoalsTeamB = calculateGoal(ndouble2, lambda2);
            
            if(totalGoalsTeamA > totalGoalsTeamB){
                tA.updatePointAfterWin();
            } else if(totalGoalsTeamB> totalGoalsTeamA){
                tB.updatePointAfterWin();
            }
            else{
                tA.updatePointAfterDraw();
                tB.updatePointAfterDraw();
            }
            alwaysPlay = true;
            System.out.println("Result: "+totalGoalsTeamA+" - "+totalGoalsTeamB);
            System.out.println("-------------------------------------------");

        }
    }

    int calculateGoal(double x, double lambda)
    {
         // lambda/(1-x)
        return (int)(lambda/(1-x));
    }

    FootballTeam getWinner() {
        if(totalGoalsTeamA == totalGoalsTeamB)
            return null;
        else if(totalGoalsTeamA > totalGoalsTeamB)
            return tA;
        else
            return tB;
    }

    /**
     * To use in the second phase. Draw is not more allow. 
     * TODO 
     */
    public void runGameChaos() {
        runGame();
        if(getWinner() == null){
            //Penalty
            Random r = new Random();
            int tAf5 = r.nextInt(6);
            int tBf5 = r.nextInt(6);
            totalGoalsTeamA +=tAf5;
            totalGoalsTeamB +=tBf5;
            if(tAf5==tBf5)
            {
                int tAf1;
                int tBf1;
                do{
                    tAf1 = r.nextInt(2);
                    tBf1 = r.nextInt(2);
                    totalGoalsTeamA +=tAf1;
                    totalGoalsTeamB +=tBf1;

                }while(tAf1 == tBf1);
            }

            
            System.out.println("Result after Penalty: "+totalGoalsTeamA+" - "+totalGoalsTeamB);
            System.out.println("-------------------------------------------");
        }

    }

}
