import java.util.*;

public class Clause{
    LinkedHashSet<Character> ClausePositives = new LinkedHashSet<>();
    LinkedHashSet<Character> ClauseNegatives = new LinkedHashSet<>();
    Boolean ClauseWorks = true;

    Clause(Clause c){
        copy(c);
    }
    Clause(String C1){
        DetermineSets(C1);
    }
    Clause(LinkedHashSet<Character> Pos, LinkedHashSet<Character> Neg){
        ClausePositives = Pos;
        ClauseNegatives = Neg;
    }
    Clause(Clause C1, Clause C2){
        Clause temp1 = new Clause (C1);
        Clause temp2 = new Clause (C2);
        Clause c = temp1.Resolve(temp2);
        if(c == null){
            ClauseWorks = false;
        }
        else{
            copy(c);
        }
        
    }
    public void copy(Clause C){
        ClausePositives.clear();
        ClauseNegatives.clear();
        for(char c : C.ClausePositives){
            ClausePositives.add(c);
        }
        for(char c : C.ClauseNegatives){
            ClauseNegatives.add(c);
        }
        ClauseWorks = C.ClauseWorks;
    }
    private void DetermineSets(String Str){
        for (int i = 0; i< Str.length(); ++i){
            switch(Str.charAt(i)){
                case '(':
                break;
                case ')':
                break;
                case ' ':
                break;
                case 'V':
                break;
                case '^':
                break;
                case '-':
                    i += 1;
                    ClauseNegatives.add(Str.charAt(i));
                break;
                default:
                    ClausePositives.add(Str.charAt(i));
                break;
            }

        }
    }
    public Clause Resolve(Clause other){
        char temp;
        
        Set<Character> Intersection1 = SetIntersection(ClausePositives, other.ClauseNegatives);
        Set<Character> Intersection2 = SetIntersection(ClauseNegatives, other.ClausePositives);
        if(Intersection1.isEmpty() && Intersection2.isEmpty()){
            //throw error
            return null;
        }
        else if(!Intersection1.isEmpty()){
            temp = getRandomFromSet(Intersection1);
            ClausePositives.remove(temp);
            other.ClauseNegatives.remove(temp);
        }
        else{
            temp = getRandomFromSet(Intersection2);
            ClauseNegatives.remove(temp);
            other.ClausePositives.remove(temp);
        }
        Clause C = new Clause(SetUnion(ClausePositives,other.ClausePositives), SetUnion(ClauseNegatives,other.ClauseNegatives));
        if(! SetIntersection(C.ClausePositives, C.ClauseNegatives).isEmpty()) {
            return null;
        }
        return C;
    }
    

    public LinkedHashSet<Character> SetIntersection(LinkedHashSet<Character> S1, LinkedHashSet<Character> S2) {
        LinkedHashSet<Character> Res = new LinkedHashSet<>();
        for(char c : S1){
            if(S2.contains(c)){
                Res.add(c);
            }
        }
        return Res;
    }
    public LinkedHashSet<Character> SetUnion(Set<Character> S1, Set<Character> S2){
        LinkedHashSet<Character> Res = new LinkedHashSet<>();
        for(char c : S1){
            Res.add(c);    
        }
        for(char c : S2){
            Res.add(c);
        }
        return Res;
    }
    public char getRandomFromSet(Set<Character> S){
        
        int item = new Random().nextInt(S.size()); 
        int i = 0;
        for(char c : S)
        {
            if (i == item)
                return c;
            i++;
        }
        return 'E';
    }
    public Boolean equal(Clause c){
        return (ClausePositives.equals(c.ClausePositives)) && (ClauseNegatives.equals(c.ClauseNegatives));
    }
    public Boolean subsume(Clause c){
        if(equal(c)){
            return false;
        }
        for(char chr : ClausePositives){
            if(! c.ClausePositives.contains(chr)){
                return false;
            }
        }
        for(char chr : ClauseNegatives){
            if(! c.ClauseNegatives.contains(chr)){
                return false;
            }
        }
        return true;
    }
    public Boolean SubEqual(Clause c){
        return equal(c) || subsume(c);
    }
    public void print(Hashtable<Character, String> Dictionary){
        //System.out.println("\nClause: ");
        int i = 1;
        for(char c : ClausePositives){
        
            System.out.print(Dictionary.get(c));
            if(ClauseNegatives.size() > 0 ){
                System.out.print(" V ");
            }
            else{
                if(i < ClausePositives.size()){
                    System.out.print(" V ");
                }
            }
            ++i;
        }
        i = 1;
        for(char c : ClauseNegatives){
            System.out.print("-" + Dictionary.get(c));
            if(i < ClauseNegatives.size()){
                System.out.print(" V ");
            }
            i++;
            
        }
    }
    public void print(){
        //System.out.println("\nClause: ");
        int i = 1;
        for(char c : ClausePositives){
            System.out.print(c);
            if(ClauseNegatives.size() > 0 ){
                System.out.print(" V ");
            }
            else{
                if(i < ClausePositives.size()){
                    System.out.print(" V ");
                }
            }
            ++i;
        }
        i = 1;
        for(char c : ClauseNegatives){
            System.out.print("-" +c);
            if(i < ClauseNegatives.size()){
                System.out.print(" V ");
            }
            i++;
            
        }
    }
    public static void main(String[] args) throws Exception{
        /*Clause A = new Clause("aVbV-c");
        Clause B = new Clause("dVbV-g");

        Clause C = new Clause(A,B);
        if(C.ClauseWorks){
            C.print();
        }else{
            System.out.println("Error");
        }
        
        System.out.print("\n");
        A.print();
        System.out.print("\n");
        B.print();*/
        Clause C1 = new Clause("b V -c V a");
        Clause C2 = new Clause("a V b V -c");
        if(C1.subsume(C2)){
            System.out.println("<");
        }
        else if(C1.SubEqual(C2)){
            System.out.println("<=");
        }
        else{
            System.out.println("~");
        }
    }
}