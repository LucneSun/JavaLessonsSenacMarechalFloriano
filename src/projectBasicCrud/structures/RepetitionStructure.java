package projectBasicCrud.structures;

import java.util.*;

public class RepetitionStructure {

    public void forStructure(List<String> names){
        for(int i = 0; i < names.size(); i++){
            System.out.print(names.get(i));

            if( i != names.size() - 1)
                System.out.println(", ");
        }
    }
    public void whileStructure(boolean test){
        int i = 0;
        System.out.println(" ");
        while (true){
            i++;

            System.out.println(i);
            if(i == 50) test = true;

            if(test)break;
        }
    }
    public void doWhileStructure(){
        var teclado = new Scanner(System.in);
        var test = false;

        do{
            System.out.println("Informe a senha: ");
            test = teclado.next().equals("senha123");
        }while(!test);

        teclado.close();
    }
    public void forEachStructure(List<Integer> numbers){
        Collections.sort(numbers);
        for(int number : numbers){
            System.out.println(number);
        }
    }


    public static void main(String[] args){
        var rs = new RepetitionStructure();
        rs.forStructure(Arrays.asList("Luis", "Carlos Eduardo", "Antonio", "Carlos Rodrigo"));
        rs.whileStructure(false);
        rs.doWhileStructure();
        rs.forEachStructure(Arrays.asList(30, 12, 4, 5, 6, 8));
    }
}
