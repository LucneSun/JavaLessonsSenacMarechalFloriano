package com.sun.collectiblestore.service;

import com.sun.collectiblestore.model.Product;
import com.sun.collectiblestore.persist.ProductDAO;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public  ProductService(){
        productDAO = new ProductDAO();
    }

    public String save(Product product){
        return productDAO.save(product) ? "Gravado com sucesso" : "Ocorreu uma falha na gravação. Verifique o log.";
    }

    public String deleteById(int id){
        return  productDAO.deleteById(id) ? "Removido com sucesso" : "Ocorreu um erro ao remover. Verifique o log.";
    }

    public String deleteAll(){
        return productDAO.deleteAll() ? "Todos os produtos removidos com sucesso" : "Ocurreu um erro ao tentar apagar produtos. Verifique o log.";
    }

    public String update(Product product){
        return productDAO.update(product) ? "Update realizado com sucesso" : "Ocorreu um erro no update. Verifique o log.";
    }

    public Product findById(int id){
        return productDAO.findById(id);
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }

    public List<Product> findAllStore(){
        return productDAO.findAllStore();
    }

    public int getStock(int id){return productDAO.getStock(id);}

    public String updateStock(int quantity, int id){return productDAO.updateStock(quantity, id) ? "Stock atualizado com sucesso." : "Erro ao tentar fazer update no stock!";}

    public double getPrice(int id){
        return productDAO.price(id);
    }
    public boolean productExists(int id){
        return productDAO.productExists(id);
    }
}
