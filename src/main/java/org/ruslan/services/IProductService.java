package org.ruslan.services;

import org.ruslan.entity.Product;

import java.util.List;
import java.util.Optional;

public interface IProductService {

    public boolean userExists(String username);

    public Optional<Product> saveProduct(Product product); //throws UserExistException;

  //  @PostAuthorize("returnObject.username == authentication.name")
    Product getProduct(Long id);

    public List<Product> getList();

    boolean deleteProduct(Integer productId);

}
