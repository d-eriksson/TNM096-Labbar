import java.util.*;

public class KnowledgeBase{
    Vector<Clause> KB = new Vector<>();
    Hashtable<Character, String> Dictionary = new Hashtable<>();
    KnowledgeBase(){
        

    }
    KnowledgeBase(Vector<Clause> k){
        KB = k;
    }
    public void add(Clause c){
        KB.add(c);
    }
    public void print(){
        System.out.println("\nKnowledge Base: ");
        System.out.println("-------------------");
        for(Clause k : KB){
            k.print(Dictionary);
            System.out.print("\n");
        }
        System.out.println("-------------------");
    }
    public void Solver(Boolean prnt){
        Vector<Clause> temp = new Vector<>();
        Vector<Clause> S = new Vector<>();
        Clause C;
        Boolean exists;
        while(!temp.equals(KB)){
            temp = new Vector<>(KB);
            S.clear();
            for(int i = 0; i < KB.size(); i++){
                for(int j = 0; j < KB.size(); j++){
                    if(i != j){
                        C = new Clause(KB.get(i), KB.get(j));
                        if(C.ClauseWorks){
                            exists= false;
                            for(Clause K : S){
                                if(K.equal(C)){
                                    exists = true;
                                }
                            }
                            if(!exists){
                                S.add(C);
                            }
                            
                        }
                        
                    }
                }
            }
            if(S.isEmpty()){
                return;
            }
            Incorporate(S);
            if(prnt){
                System.out.println("\nS:");
                System.out.println("-------------------");
                for(Clause K : S){
                    K.print(Dictionary);
                    System.out.print("\n");
                }
                System.out.println("-------------------");
                System.out.print("\n");
                
                System.out.println("");
                print();
            }
            

        }
    }
    public void Incorporate(Vector<Clause> S){
        for (Clause c : S){
            Incorporate_clause(c);
        }
    }
    public void Incorporate_clause(Clause c){
        Vector<Clause> RemoveList = new Vector<>();
        for (Clause B : KB){
            if(c.subsume(B)){
                RemoveList.add(B);
            }
        }
        for(Clause B : RemoveList){
            KB.remove(B);
        }
        Boolean exists= false;
        for(Clause K : KB){
            if(K.equal(c)){
                exists = true;
            }
        }
        if(!exists){
            add(c);
        }
    }
    public static void main(String[] args) throws Exception{
        KnowledgeBase KB = new KnowledgeBase();
        KB.add(new Clause("-sV-mVi"));
        KB.add(new Clause("-mViVo"));
        KB.add(new Clause("-oVm"));
        KB.add(new Clause("-oV-i"));
        KB.add(new Clause("o"));
        KB.Dictionary.put('s', "Sun");
        KB.Dictionary.put('m', "Money");
        KB.Dictionary.put('i', "Ice");
        KB.Dictionary.put('o', "Movie");
        KB.print();
        KB.Solver(false);
        KB.print();
    }
}