package org.ruslan.services;

import jakarta.transaction.Transactional;
import org.ruslan.dao.ShopRepository;
import org.ruslan.dao.ShopRepository;
import org.ruslan.entity.Shop;
import org.ruslan.entity.Shop;
import org.ruslan.json.JsonReader;
import org.ruslan.json.JsonWriter;

import java.util.List;

public class ShopService {


    public String pathShopJSON = "src\\main\\resources\\Shop.json";
    private ShopRepository shopRepository = ShopRepository.getInstance();
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();
    public Shop saveShop(Shop shop) {
        return shopRepository.save(shop);
    }

    public boolean deleteShop(Long idShop) {
        if (shopRepository.findById(idShop).isPresent()) {
            shopRepository.delete(idShop);
            return true;
        }
        return false;
    }

    public boolean updateShop(Shop shop) {
        if (shopRepository.findById(shop.getId()).isPresent()) {
            shopRepository.update(shop);
            return true;
        }
        return false;
    }

    public Shop getShopById(Long id) {
        return shopRepository.findById(id).orElse(null);
    }

    public List<Shop> getList() {
        return shopRepository.findAll();
    }
    @Transactional
    public void importShopsFromJsonToDataBase() {
        List<Shop> ShopList = jsonReader.readEntities(Shop.class, pathShopJSON);
        ShopList.forEach(shop -> shopRepository.save(shop));
    }
    @Transactional
    public void exportShopsToJson() {
        List<Shop> Shops = shopRepository.findAll();
        jsonWriter.writeEntities(shopRepository.findAll(), pathShopJSON);
    }

}
