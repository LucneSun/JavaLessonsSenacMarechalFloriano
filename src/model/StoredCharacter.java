package model;

import java.util.Arrays;
import java.util.stream.Collectors;

public class StoredCharacter {
    private int id;
    private String name;
    private int inventory[] = new int[10];

    public StoredCharacter(int id, String name, String inventory){
      this.id = id;
      this.name = name;

      String[] ids = new String[10];

      if(inventory != null)
      ids = inventory.split(";");

      for(int i = 0; i < this.inventory.length; i++){
          try{this.inventory[i] = Integer.parseInt(ids[i]);}catch (NumberFormatException | ArrayIndexOutOfBoundsException e){this.inventory[i] = 0;}
      }
    }

    public int getId(){return id;}
    public void setInt(int id){this.id = id;}
    public String getName(){return name;}
    public void setName(String name){this.name = name;}
    public int[] getInventory(){return inventory;}
    public void setInventory(int inventory[]){this.inventory = inventory;}

    public String toString(){return " \n ============== \n character name: " + name + " \n character id: " + id + "\n ============== \n";}
    public String getInventoryString(){
        String inv = "";
        for(int i = 0; i < inventory.length; i++){
            inv += String.valueOf(inventory[i]);
            inv += ";";
        }

        return inv;
    }
}
