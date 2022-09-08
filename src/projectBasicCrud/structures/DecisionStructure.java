package projectBasicCrud.structures;

public class DecisionStructure {

    public void simpleStructure(String a){
        if(a.equalsIgnoreCase("Java")){
            System.out.println("We are working with Java");
        }
    }

    public void composedStructure(String a){
        if(a.equalsIgnoreCase("Java")){
            System.out.println("We are working with Java.");
        }
        else{ System.out.println("Not the language that we are working with.");}
    }

    public void chainedStructure(String a){
        if(a.equalsIgnoreCase("PHP")) {
            System.out.println("Linguagem voltada para web comumente usada para aplicações simples");
        }
        else if(a.equalsIgnoreCase("MySQL")) {
            System.out.println("SBGD muito utilizado para aprendizagem de banco de dados");
        }
        else if(a.equalsIgnoreCase("Angular")) {
            System.out.println("Framework javascript para aplicações frontend");
        }
        else if(a.equalsIgnoreCase("Java")) {
            System.out.println("Linguagem voltada para qualquer tipo de plataforma, muito utilizada por empresas");
        }
        else{ System.out.println("Opção Invalida");}
    }

    public void ternaryStructure(String a){
        System.out.println(a.equalsIgnoreCase("Java") ? "Linguagem top" : "Não é muito boa não");
    }
    public void compactStructure(String a){
        if(a.equalsIgnoreCase("Java"))
            System.out.println("Linguagem fácilde aprender");
        else System.out.println("Linguagem dificil");
    }

    public static void main(String[] args){
        var ds = new DecisionStructure();
        ds.simpleStructure("JAVA");
        ds.composedStructure("PHP");
        ds.chainedStructure("Ruby");
        ds.ternaryStructure("Java");
        ds.compactStructure("JAVA");
    }
}
