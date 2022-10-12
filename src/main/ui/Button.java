package main.ui;

import main.App;
import main.audio.AudioPlayer;
import main.utils.ButtonType;
import main.utils.Load;
import model.Product;
import model.StoredCharacter;
import service.ProductService;
import service.StoredCharacterService;
import com.sun.source.tree.NewArrayTree;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Button {
    ButtonType type;
    private int xPos, yPos, rowIndex, index = 0;
    private boolean mouseOver, mousePressed;
    private Rectangle bounds;
    private BufferedImage[] images;
    App app;
    StoredCharacterService characterService;
    ProductService productService;

    public Button(ButtonType type, int xPos, int yPos, int rowIndex, App app){
        characterService = new StoredCharacterService();
        productService = new ProductService();

        this.app = app;
        this.type = type;
        this.xPos = xPos;
        this.yPos = yPos;
        this.rowIndex = rowIndex;
        loadImages();
        initBounds();
    }

    public void initBounds(){
        bounds = new Rectangle(xPos, yPos, 100, 100);

    }

    public void loadImages(){
        images = new BufferedImage[3];
        BufferedImage temp = Load.getSpriteAtlas("buttons.png");
        for(int i = 0; i < images.length; i++){
           images[i] = temp.getSubimage(i * 100, rowIndex * 100, 100, 100);
        }
    }

    public void draw(Graphics g){
        g.drawImage(images[index], xPos, yPos, 100, 100, null);
    }

    public void update(){
        index = 0;
        if(mouseOver)index = 1;
        if(mousePressed) index = 2;
    }

    public void setMouseOver(boolean mouseOver){this.mouseOver = mouseOver;}
    public boolean getMouseOver(){return mouseOver;}
    public void setMousePressed(boolean mousePressed){this.mousePressed = mousePressed;}
    public boolean getMousePressed(){return mousePressed;}

    public  Rectangle getBounds(){return this.bounds;}

    public void openMenu(){
        app.audioPlayer.playEffect(0);

        switch (type){
            case ADDMONEY:
                if(app.money <= 1000000) {
                    app.money += 1000;
                    app.audioPlayer.playEffect(1);
                }
                break;
            case OPENSTOREMANAGER:
                OpenStoreManager();
                break;
            case OPENSTORE:
                OpenStore();
                break;
            case OPENCHARMANAGER:
                OpenCharManager();
                break;
            case OPENINVENTORY:
                OpenInventory();
                break;
            default:
                break;
        }
    }

    public void OpenInventory(){
        int option = 0;

       if (!checkExistence())
           return;

        while (option != 4){
            String input = showMenu("Menu do inventário", "1: Listar ítens \n 2: Usar ítem por id \n 3: Remover ítem por índice do stock");
            int convertedInput = 4;

            if (input != null)
                try{convertedInput = Integer.parseInt(input);}catch (NumberFormatException e){convertedInput = 4;}

            option = convertedInput;
            switch (option){
                case 1:
                    String inventory = characterService.checkInventory(app.charId);
                    int inv[] = new int[10];
                    ArrayList<Integer> finalInventoryList = new ArrayList<>();
                    String[] ids = new String[10];

                    if(inventory != null)
                        ids = inventory.split(";");

                    for(int i = 0; i < ids.length; i++){
                        try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
                        if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                    }

                    String updateInv = "";
                    for(int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length)
                        updateInv += finalInventoryList.get(i);
                        else updateInv += "0";
                        updateInv += ";";
                    }

                    JOptionPane.showMessageDialog(null, characterService.buy(updateInv, app.charId));

                    Product checkproduct = null;
                    String findAll = "";
                    for(int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length) {
                            findAll += "\n slot(" + i + ")";
                            checkproduct = productService.findById(finalInventoryList.get(i));
                            if(checkproduct != null)
                                findAll += checkproduct.storeString();
                        }
                    }

                    JOptionPane.showMessageDialog(null, findAll, "Inventário", JOptionPane.INFORMATION_MESSAGE);

                    break;

                case 2:
                    resetStore();

                    inventory = characterService.checkInventory(app.charId);
                    inv = new int[10];
                    finalInventoryList = new ArrayList<>();
                    ids = new String[10];

                    if(inventory != null)
                        ids = inventory.split(";");

                    for(int i = 0; i < ids.length; i++){
                        try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
                    }

                    for(int i = 0; i < 10; i++){
                        if(i < inv.length)
                            if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                            else finalInventoryList.add(0);
                    }

                    String pType = "";
                    int useOption = 0;
                    try{useOption = Integer.parseInt(JOptionPane.showInputDialog("Insira o stock do ítem desejado: "));}catch (NumberFormatException e){}
                    Product product = productService.findById(finalInventoryList.get(useOption));

                    if (product != null)
                        pType = product.getType();

                    switch (pType){
                        case "CON":
                            JOptionPane.showMessageDialog(null, product.getName() + " foi consumido!");
                            finalInventoryList.set(useOption, 0);
                            deleteAndReset(finalInventoryList);

                            break;
                        case "COL":
                            JOptionPane.showMessageDialog(null, product.getName() + " é coletável");
                            break;
                        default:
                            JOptionPane.showMessageDialog(null, "Invalid item, please fix type");
                            break;
                    }
                    break;
                case 3:
                    inventory = characterService.checkInventory(app.charId);
                    inv = new int[10];
                    finalInventoryList = new ArrayList<>();
                    ids = new String[10];

                    if(inventory != null)
                        ids = inventory.split(";");

                    for(int i = 0; i < ids.length; i++){
                        try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
                    }

                    try{int deleteid = Integer.parseInt(JOptionPane.showInputDialog("Insira o slot do inventário que será excluído (1-10): ")); if(deleteid >= 1 && deleteid <= 10) inv[deleteid] = 0;}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "digite um número!");}

                    for(int i = 0; i < 10; i++){
                        if(i < inv.length)
                        if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                        else finalInventoryList.add(0);
                    }


                    String saveInv = "";
                    for(int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length) {
                            saveInv += finalInventoryList.get(i);
                        }
                        else saveInv += "0";

                        saveInv += ";";
                    }
                    System.out.println(saveInv);
                    JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));
                    break;
                case 4:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }

    private void resetStore(){
        String inventory = characterService.checkInventory(app.charId);
        int inv[] = new int[10];
        ArrayList<Integer> finalInventoryList = new ArrayList<>();
        String ids[] = new String[10];

        if(inventory != null)
            ids = inventory.split(";");

        for(int i = 0; i < ids.length; i++){
            try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
        }

        for(int i = 0; i < 10; i++){
            if(i < inv.length)
                if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                else finalInventoryList.add(0);
        }


        String saveInv = "";
        for(int i = 0; i < 10; i++){
            if(i < finalInventoryList.toArray().length) {
                saveInv += finalInventoryList.get(i);
            }
            else saveInv += "0";

            saveInv += ";";
        }
        System.out.println(saveInv);
        JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));
    }

    private void deleteAndReset(ArrayList<Integer> finalInventoryList){

        String saveInv = "";
        for(int i = 0; i < 10; i++){
            if(i < finalInventoryList.toArray().length) {
                saveInv += finalInventoryList.get(i);
            }
            else saveInv += "0";

            saveInv += ";";
        }
        System.out.println(saveInv);
        JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));
    }

    public void OpenStore(){
        int option = 0;

        if(!checkExistence())
            return;

        while (option != 3){
            String input = showMenu("Menu da loja", "1: Comprar \n 2: Vender");
            int convertedInput = 3;

            if (input != null)
                try{convertedInput = Integer.parseInt(input);}catch (NumberFormatException e){convertedInput = 0;}

            option = convertedInput;
            switch (option){
                case 1:
                    String inventory = characterService.checkInventory(app.charId);
                    int itemcount = 0;
                    int inv[] = new int[10];
                    ArrayList<Integer> finalInventoryList = new ArrayList<>();
                    String[] ids = new String[10];


                    if(inventory != null)
                        ids = inventory.split(";");

                    for(int i = 0; i < ids.length; i++){
                        try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
                        if(productService.productExists(inv[i])){ itemcount++; finalInventoryList.add(inv[i]);}
                    }


                    if(itemcount > 9){
                        JOptionPane.showMessageDialog(null, "Limite do inventário atingido");
                        return;
                    }

                    String allProducts = "";
                    List<Product> products = productService.findAllStore();
                    for(Product product : products){
                        allProducts += product.storeString();
                    }

                    int buychoice = 0;
                    String buychoiceS = JOptionPane.showInputDialog("Escolha um dos produtos para comprar digitando seu id: \n" + allProducts);
                    try{buychoice = Integer.parseInt(buychoiceS);}catch (NumberFormatException e){buychoice = 0;}

                    double price = -1;
                    price = productService.getPrice(buychoice);

                    if(price < 0){
                        JOptionPane.showMessageDialog(null, "Erro ao tentar buscar preço do produto!");
                        return;
                    }

                    app.money = app.money - price;

                    if(price > app.money){
                        JOptionPane.showMessageDialog(null, "Não tem gold suficiente!");
                        return;
                    }

                    int currentStock = productService.getStock(buychoice);

                    if(currentStock != 0){
                        JOptionPane.showMessageDialog( null,productService.updateStock(currentStock - 1 ,buychoice));
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Erro ao ler stock");
                        return;
                    }

                    finalInventoryList.add(buychoice);

                    for (int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length){
                        }
                        else finalInventoryList.add(0);
                    }

                    String saveInv = "";
                    for(int i = 0; i < 10; i++){
                        saveInv += finalInventoryList.get(i);
                        saveInv += ";";
                    }

                    JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));
                    break;
                case 2:
                    inventory = characterService.checkInventory(app.charId);
                    inv = new int[10];
                    finalInventoryList = new ArrayList<>();
                    ids = new String[10];

                    if(inventory != null)
                        ids = inventory.split(";");

                    for(int i = 0; i < ids.length; i++){
                        try{inv[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){inv[i] = 0;}
                    }

                    for(int i = 0; i < 10; i++){
                        if(i < inv.length)
                            if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                            else finalInventoryList.add(0);
                    }

                    saveInv = "";
                    for(int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length) {
                            saveInv += finalInventoryList.get(i);
                        }
                        else saveInv += "0";

                        saveInv += ";";
                    }
                    JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));


                    try{int deleteid = Integer.parseInt(JOptionPane.showInputDialog("Insira o slot do inventário que será excluído (1-10): ")); if(deleteid >= 1 && deleteid <= 10){ app.money += productService.getPrice(inv[deleteid]); inv[deleteid] = 0;}}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "digite um número!");}


                    for(int i = 0; i < 10; i++){
                        if(i < inv.length)
                            if(productService.productExists(inv[i])){finalInventoryList.add(inv[i]);}
                            else finalInventoryList.add(0);
                    }

                     saveInv = "";
                    for(int i = 0; i < 10; i++){
                        if(i < finalInventoryList.toArray().length) {
                            saveInv += finalInventoryList.get(i);
                        }
                        else saveInv += "0";

                        saveInv += ";";
                    }
                    JOptionPane.showMessageDialog(null, characterService.buy(saveInv, app.charId));

                case 3:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }

    public void OpenCharManager(){{
    }
        int option = 0;
        while (option != 6){
            String input = showMenu("Menu de personagens", "1: Mostrar todos os personagens \n 2: escolher um personagem \n 3: editar nome de um personagem pelo id \n 4: adicionar um personagem \n 5: remover um personagem");
           int convertedInput = 6;

           if (input != null)
           try{convertedInput = Integer.parseInt(input);}catch (NumberFormatException e){convertedInput = 0;}

           option = convertedInput;
           switch (option){
               case 1:
                   String allchars = "";
                   List<StoredCharacter> chars = characterService.findAll();
                   for(StoredCharacter chara : chars){
                       allchars += chara.toString();
                   }
                   JOptionPane.showMessageDialog(null, allchars, "Todos os personagens",  JOptionPane.INFORMATION_MESSAGE);
                   break;
               case 2:
                   int charnewId = 0;
                   while (charnewId == 0) {
                       String idS = JOptionPane.showInputDialog("Insira o id do personagem que tera o nome modificado: ");
                       try {
                           charnewId = Integer.parseInt(idS);
                       } catch (NumberFormatException e) {
                           JOptionPane.showMessageDialog(null, "digite um número!");
                       }
                   }
                   app.charId = charnewId;
                   break;
               case 3:
                   int id = 0;
                   String newName = "";
                   while (id == 0) {
                       String idS = JOptionPane.showInputDialog("Insira o id do personagem que tera o nome modificado: ");
                       try {
                           id = Integer.parseInt(idS);
                       } catch (NumberFormatException e) {
                           JOptionPane.showMessageDialog(null, "digite um número!");
                       }
                   }
                   while (newName.length() < 5){
                       newName =  JOptionPane.showInputDialog("Insira o novo nome pro personagem (nome precisa ter mais de 4 letras):");
                   }
                   JOptionPane.showMessageDialog(null, characterService.updateName(id, newName));
                   break;
               case 4:
                   String name = "";
                   while (name.length() < 4)
                   name = JOptionPane.showInputDialog("Insira o nome do personagem (nome precisa ter mais de 4 letras) : ");

                   StoredCharacter chara = new StoredCharacter(0, name, "");
                   JOptionPane.showMessageDialog(null, characterService.save(chara));;
                   break;
               case 5:
                   String idS = JOptionPane.showInputDialog("Insira o id do personagem que será excluído: ");
                   try{int deleteid = Integer.parseInt(idS); JOptionPane.showMessageDialog(null, characterService.deleteById(deleteid));;}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "digite um número!");}
                   break;
               case 6:
                   break;
               default:
                   JOptionPane.showMessageDialog(null, "Opcao invalida");
           }
        }
    }

    public void OpenStoreManager(){{
    }
        int option = 0;
        while (option != 5){
            String input = showMenu("Menu de produtos", "1: Mostrar todos os produtos \n 2: editar nome, preço e tipo de um produto pelo id \n 3: adicionar um produto \n 4: remover um produto");
            int convertedInput = 5;

            if (input != null)
                try{convertedInput = Integer.parseInt(input);}catch (NumberFormatException e){convertedInput = 0;}

            option = convertedInput;
            switch (option){
                case 1:
                    String allProducts = "";
                    List<Product> products = productService.findAll();
                    for(Product product : products){
                        allProducts += product.toString();
                    }
                    JOptionPane.showMessageDialog(null, allProducts, "Todos os produtos da loja",  JOptionPane.INFORMATION_MESSAGE);
                    break;
                case 2:
                    int editid = 0;
                    String editname = "";
                    int editquantity = 0;
                    double editprice = 0f;
                    String edittype = "E";

                    try{editid = Integer.parseInt(JOptionPane.showInputDialog("Selecione o id do produto que será modificado: "));}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Id não valido, edição não vai funcionar.");}

                    while (editname.length() < 4)
                        editname = JOptionPane.showInputDialog("Insira o nome do produto (nome precisa ter mais de 4 letras) : ");
                    while (edittype.length() != 3)
                        edittype = JOptionPane.showInputDialog("Insira o tipo do produto (tipo precisa ter 3 letras) : ");

                    try{editquantity = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade deste produto: "));}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Quantida não valida, produto tera quantidade zero");}
                    try{editprice = Double.parseDouble(JOptionPane.showInputDialog("Insira o preço do produto: "));}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Preço não valido, produto terá preço 0");}

                    Product editproduct = new Product(editid, editname, editquantity, editprice, edittype);
                    JOptionPane.showMessageDialog(null, productService.update(editproduct));
                    break;
                case 3:
                    String name = "";
                    int quantity = 0;
                    double price = 0f;
                    String type = "E";


                    while (name.length() < 4)
                        name = JOptionPane.showInputDialog("Insira o nome do produto (nome precisa ter mais de 4 letras) : ");
                    while (type.length() != 3)
                        type = JOptionPane.showInputDialog("Insira o tipo do produto (tipo precisa ter 3 letras) : ");

                    try{quantity = Integer.parseInt(JOptionPane.showInputDialog("Insira a quantidade deste produto: "));}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Quantida não valida, produto tera quantidade zero");}
                    try{price = Double.parseDouble(JOptionPane.showInputDialog("Insira o preço do produto: "));}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "Preço não valido, produto terá preço 0");}

                    Product product = new Product(0, name, quantity, price, type);
                    JOptionPane.showMessageDialog(null, productService.save(product));
                    break;
                case 4:
                    String idS = JOptionPane.showInputDialog("Insira o id do produto que será excluído: ");
                    try{int deleteid = Integer.parseInt(idS);  JOptionPane.showMessageDialog(null, productService.deleteById(deleteid));;}catch (NumberFormatException e){JOptionPane.showMessageDialog(null, "digite um número!");}
                    break;

                case 5:
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcao invalida");
            }
        }
    }

    public String showMenu(String name, String message){
       return JOptionPane.showInputDialog(null, message, name , JOptionPane.QUESTION_MESSAGE);
    }

    public boolean checkExistence(){
        if(!characterService.checkIdExistence(app.charId)){
            JOptionPane.showMessageDialog(null, "Por favor escolha um número válido no menu de personagens.");
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    public void resetBools(){
        mouseOver = false;
        mousePressed = false;
    }
}
