package service;

import model.StoredCharacter;
import persist.StoredCharacterDAO;

import java.util.List;

public class StoredCharacterService {
    private StoredCharacterDAO characterDAO;
    public StoredCharacterService(){
        characterDAO = new StoredCharacterDAO();
    }

    public List<StoredCharacter> findAll(){
        return characterDAO.findAll();
    }
    public String save(StoredCharacter chara){
        return characterDAO.save(chara) ? "Personagem salvo com sucesso." : "Erro ao tentar salvar personagem!";
    }

    public String deleteById(int id){
        return characterDAO.deleteById(id) ? "Personagem deletado com sucesso." : "Erro ao tentar deletar personagem!";
    }

    public String updateName(int id, String name){
        return characterDAO.updateCharacterName(id, name) ? "Personagem atualizado com sucesso." : "Erro ao tentar atualizar personagem!";
    }

    public String checkInventory(int id){
        return characterDAO.checkInventory(id);
    }

    public String buy(String inventory, int id){
        return characterDAO.buy(inventory, id) ? "Inventário atualizado." : "Erro ao atualizar inventário!";
    }

    public String updateInventory(String inventory, int id){
        return characterDAO.buy(inventory, id) ? "Compra finalizada." : "Erro ao finalizar compra!";
    }

    public boolean checkIdExistence(int id){
        return characterDAO.checkExistence(id);
    }
}
