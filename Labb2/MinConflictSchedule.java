import java.util.*;

public class MinConflictSchedule{
    List<String> X;
    String[][] Domain;
    int MaxSteps;
    MinConflictSchedule(int MaxSteps){
        this.MaxSteps = MaxSteps;
        X = new ArrayList<String>();
        Domain = new String[8][3];
        initDomain();
    }
    void resetDomain(String[][] D){
        for(int i= 0 ; i < 8; ++i){
            for(int j = 0; j < 3; ++j){
                Domain[i][j] = D[i][j];
            }
        }
    }
    void setInitialState(){
        Collections.shuffle(X);
        int i = 0;
        int j = 0;
        for( String x : X){
             Domain[i][j] = x;
             i++;
             if(i > 7){
                 i = 0;
                 j++;
             }
        }
    }
    void addVariables(String S){
        X.add(S);
    }
    void initDomain(){
        for(int i= 0 ; i < 8; ++i){
            for(int j = 0; j < 3; ++j){
                Domain[i][j] = "     ";
            }
        }
    }
    int[] GetRandomConflictingVariable(String[][] D){
        int[] V = new int[2];
        List<Integer> X = new ArrayList<>();
        List<Integer> Y = new ArrayList<>();
        for(int i = 0; i < 8; ++i){
            if(D[i][0] != "" && D[i][1] != "" && D[i][0].charAt(2) == D[i][1].charAt(2) && D[i][0] != "5"){
                X.add(i);
                Y.add(0);
            }
            else if(D[i][0] != "" && D[i][2] != "" && D[i][0].charAt(2) == D[i][2].charAt(2) && D[i][0] != "5"){
                X.add(i);
                Y.add(2);
            }
            else if(D[i][1] != "" && D[i][2] != "" && D[i][1].charAt(2) == D[i][2].charAt(2)&& D[i][1] != "5"){
                X.add(i);
                Y.add(1);
            }
        }
        Random rand = new Random();
        int r = rand.nextInt(X.size());
        V[0] = X.get(r);
        V[1] = Y.get(r);
        return V;


    }
    void solve(){
        int n = 0;
        int conf = conflicts(this.Domain);
        int tempConf;
        Random rand = new Random();
        int i2 = 0;
        int j2 = 0;
        String[][] temp_D = this.Domain;
        String temp;
        int[] RandomValues = new int[2];
        while(n < this.MaxSteps && conf != 0){
            i2 = rand.nextInt(8);
            j2 = rand.nextInt(3);
            RandomValues = GetRandomConflictingVariable(this.Domain);
            temp = temp_D[RandomValues[0]][RandomValues[1]];
            temp_D[RandomValues[0]][RandomValues[1]] = temp_D[i2][j2];
            temp_D[i2][j2] = temp;
            tempConf = conflicts(temp_D);
            if(tempConf < conf){
                this.Domain = temp_D;
                conf = tempConf;
            }
            else{
                temp_D = this.Domain;
            }
            n++;
        }
        //System.out.println("\nNumber of steps: " + n);

    }
    int conflicts(String[][] D){
        int conflicts = 0;
        for(int i = 0; i < 8; ++i){
            if(D[i][0] != "" && D[i][1] != "" && D[i][0].charAt(2) == D[i][1].charAt(2) && D[i][0] != "5"){
                conflicts++;
            }
            else if(D[i][0] != "" && D[i][2] != "" && D[i][0].charAt(2) == D[i][2].charAt(2) && D[i][0] != "5"){
                conflicts++;
            }
            else if(D[i][1] != "" && D[i][2] != "" && D[i][1].charAt(2) == D[i][2].charAt(2)&& D[i][1] != "5"){
                conflicts++;
            }
        }
        return conflicts;
    }
    void print(){
        System.out.println("     TP51          SP34          K3");
        System.out.println("-----------------------------------");
        for(int i= 0 ; i < 8; ++i){
            System.out.print(i + 9);
            if(i==0){
                System.out.print(" ");
            }
            for(int j = 0; j < 3; ++j){
                System.out.print("    " + Domain[i][j] + "    " );
            }
            System.out.println("");
        }
        System.out.println("Conflicts: " + conflicts(this.Domain));
    }
    public static void main(String[] args) throws Exception{
        MinConflictSchedule M = new MinConflictSchedule(10000000);
        M.addVariables("MT202");
        M.addVariables("MT203");
        M.addVariables("MT204");
        M.addVariables("MT205");
        M.addVariables("MT206");
        M.addVariables("MT101");
        M.addVariables("MT201");
        M.addVariables("MT301");
        M.addVariables("MT302");
        M.addVariables("MT303");
        M.addVariables("MT102");
        M.addVariables("MT103");
        M.addVariables("MT104");
        M.addVariables("MT501");
        M.addVariables("MT105");
        M.addVariables("MT106");
        M.addVariables("MT107");
        M.addVariables("MT304");
        M.addVariables("MT401");
        M.addVariables("MT402");
        M.addVariables("MT403");
        M.addVariables("MT502");
        M.setInitialState();
        M.print();
        M.solve();
        M.print();

    }
}
