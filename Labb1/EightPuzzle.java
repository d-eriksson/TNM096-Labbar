import java.util.*;

public class EightPuzzle{
    int[][] State = new int[3][3];
    // 0 1 2
    // 3 4 5
    // 6 7 8

    EightPuzzle(int[][] S){
        this.State = S;
    }
    public void print(){
        System.out.println("_____________");
        System.out.println("| " + this.State[0][0] + " | " + this.State[0][1] + " | " + this.State[0][2] + " |" );
        System.out.println("| " + this.State[1][0] + " | " + this.State[1][1] + " | " + this.State[1][2] + " |" );
        System.out.println("| " + this.State[2][0] + " | " + this.State[2][1] + " | " + this.State[2][2] + " |" );
        System.out.println("_____________");
    }
    public Boolean GoalReached(){
        if(MissPlacedTiles() == 0){
            return true;
        }
        else {
            return false;
        }
    }
    public EightPuzzle Copy(){
        int[][] Temp = new int[3][3];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Temp[i][j] = this.State[i][j];
            }
        }
        return new EightPuzzle(Temp);

    }
    public EightPuzzle Move(int A){
        int[][] Temp = new int[3][3];
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                Temp[i][j] = this.State[i][j];
            }
        }
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.State[i][j] == 0){
                    if(A==0){
                        // Move up
                        Temp[i][j] = Temp[i-1][j];
                        Temp[i-1][j] = 0;
                    }
                    if(A==1){
                        // Move right
                        Temp[i][j] = Temp[i][j+1];
                        Temp[i][j+1] = 0;
                    }
                    if(A==2){
                        // Move down
                        Temp[i][j] = Temp[i+1][j];
                        Temp[i+1][j] = 0;
                    }
                    if(A==3){
                        // Move left
                        Temp[i][j] = Temp[i][j-1];
                        Temp[i][j-1] = 0;
                    }
                }
            }
        }
        return new EightPuzzle(Temp);
    }
    public Boolean[] PossibleActions(){
        Boolean[] Actions = {false,false,false,false};
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.State[i][j] == 0){
                    if(i != 0){
                        Actions[0] = true;
                    }
                    if(j != 2){
                        Actions[1] = true;
                    }
                    if(i != 2){
                        Actions[2] = true;
                    }
                    if(j != 0){
                        Actions[3] = true;
                    }
                }
            }
        }
        return Actions;
    }
    public int MissPlacedTiles(){

        int count = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.State[i][j] != 0 && this.State[i][j] != (j+1+i*3)){
                    count++;
                }
            }
        }
        return count;
    }
    public int ManhattanDistance(){
        int dist = 0;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.State[i][j] != 0){
                    dist += Math.abs(i-(this.State[i][j]-1)/3) + Math.abs(j-(this.State[i][j]-1)%3);
                }
            }
        }
        return dist;
    }
    public static void main(String[] args) throws Exception{
        int[][] InitialState = {{1,4,3},{8,0,5},{2,7,6}};
        EightPuzzle E = new EightPuzzle(InitialState);
        System.out.println(E.MissPlacedTiles());
        EightPuzzle E2;
        E.print();
        E2 = E.Move(3);
        E2.print();

    }
}
