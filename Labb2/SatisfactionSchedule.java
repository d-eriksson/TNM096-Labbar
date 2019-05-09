import java.util.*;

public class SatisfactionSchedule{
    int MaxSteps;
    int MaxStepsMinConflict;
    SatisfactionSchedule(int MaxSteps, int MaxStepsMinConflict){
        this.MaxSteps = MaxSteps;
        this.MaxStepsMinConflict = MaxStepsMinConflict;
    }
    MinConflictSchedule Satisfy(MinConflictSchedule M){
        int NS = NonSatisfied(M);
        int tempNS = 99999;
        MinConflictSchedule temp = new MinConflictSchedule(this.MaxStepsMinConflict);
        MinConflictSchedule best = new MinConflictSchedule(this.MaxStepsMinConflict);
        temp.resetDomain(M.Domain);
        temp.print();
        int n = 0;
        while(n < this.MaxSteps && NS > 11){
            temp.solve();
            //temp.print();
            tempNS = NonSatisfied(temp);
            //System.out.println(tempNS);
            if(tempNS < NS ||n == 0){
                best.resetDomain(temp.Domain);
                NS = tempNS;
            }
            temp.resetDomain(M.Domain);
            ++n;
        }
        System.out.println(n == this.MaxSteps);
        System.out.println(NonSatisfied(best));
        return best;
    }
    int NonSatisfied(MinConflictSchedule M){
        int NonSatisfied = 0;
        for(int i= 0 ; i < 8; ++i){
            for(int j = 0; j < 3; ++j){
                if(i == 0 || i == 3 || i == 7){
                    if(M.Domain[i][j] != "     "){
                        NonSatisfied++;
                    }
                }
                else if(i == 4 || i == 5){
                    if(M.Domain[i][j] == "MT501" || M.Domain[i][j] == "MT502"){

                    }
                    else{
                        NonSatisfied++;
                    }
                }
            }
        }
        return NonSatisfied;
    }
    public static void main(String[] args) throws Exception{
        MinConflictSchedule M = new MinConflictSchedule(10000000);
        SatisfactionSchedule S = new SatisfactionSchedule(100000,10000000);
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
        //M.print();
        M =  S.Satisfy(M);
        M.print();

    }
}
