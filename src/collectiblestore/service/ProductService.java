package collectiblestore.service;

import collectiblestore.model.Product;
import collectiblestore.persist.ProductDAO;

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

    public String update(Product product, int id){
        return productDAO.update(product, id) ? "Update realizado com sucesso" : "Ocorreu um erro no update. Verifique o log.";
    }

    public Product findById(int id){
        return productDAO.findById(id);
    }

    public List<Product> findAll(){
        return productDAO.findAll();
    }
}
