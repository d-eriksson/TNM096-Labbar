import java.util.Objects;
public class StateNode implements Comparable<StateNode>{
    EightPuzzle state;
    int G;
    int H;
    int F;


    StateNode(EightPuzzle Start, int G, Boolean h1){
        this.state = Start.Copy();
        this.G = G;
        if(h1){
            this.H = Start.MissPlacedTiles();
        }
        else{
            this.H = Start.ManhattanDistance();

        }
        this.F = this.G + this.H;
    }
    @Override
    public int compareTo(StateNode anotherStateNode) {
        if(this.F - anotherStateNode.F < 0)
        {
            return -1;
        }
        else if (this.F - anotherStateNode.F > 0){
            return 1;
        }

        return 0;
    }
    @Override
    public int hashCode() {
        return Objects.hash(state);
    }

    @Override
    public boolean equals(Object obj) {

        if(obj == null) return false;
        StateNode ST = (StateNode) obj;
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                if(this.state.State[i][j] != ST.state.State[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) throws Exception{


    }
}
