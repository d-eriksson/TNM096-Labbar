import java.util.*;
import java.text.ParseException;
import java.time.*;
public class AstarSearch{
    PriorityQueue<StateNode> NodeList;
    StateNode Result;
    Boolean h1;


    AstarSearch(EightPuzzle Start, Boolean h1){
        this.NodeList = new PriorityQueue<StateNode>();
        this.h1 = h1;
        this.NodeList.add(new StateNode(Start, 0,this.h1));
    }
    public Boolean HeuristicSearch(){
        StateNode temp;
        Boolean[] actions = new Boolean[4];
        while(this.NodeList.isEmpty() == false){
            temp = this.NodeList.poll();

            if(temp.state.MissPlacedTiles() == 0){
                this.Result = temp;
                return true;
            }
            else{
                actions = temp.state.PossibleActions();
                for(int i = 0; i < 4; ++i){
                    if(actions[i]){
                        //if(!this.NodeList.contains(temp.state.Move(i))){
                            this.NodeList.add(new StateNode(temp.state.Move(i), temp.G + 1, this.h1));
                        //}

                    }
                }
            }
        }
        return false;
    }

    public static void main(String[] args) throws Exception{

        int[][] InitialState = {{2,5,0},{1,4,8},{7,3,6}};

        EightPuzzle E = new EightPuzzle(InitialState);
        E.print();
        AstarSearch A = new AstarSearch(E, true);
        Instant start = Instant.now();
        if(A.HeuristicSearch()){
            A.Result.state.print();
            System.out.println("Steps: " + A.Result.G);
        }
        Instant finish = Instant.now();
        long timeElapsed = Duration.between(start, finish).toMillis();
        System.out.println("Milliseconds: " + timeElapsed);
    }
}
