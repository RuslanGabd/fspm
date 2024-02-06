package org.ruslan.services;

import jakarta.transaction.Transactional;
import org.ruslan.core.dao.ShopRepository;
import org.ruslan.core.dto.ShopDto;
import org.ruslan.core.entity.Shop;
import org.ruslan.core.mapper.ShopMapper;
import org.ruslan.utils.json.JsonReader;
import org.ruslan.utils.json.JsonWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopService {


    public String pathShopJSON = "src\\main\\resources\\Shop.json";
    private final ShopRepository shopRepository;
    private final JsonReader jsonReader = JsonReader.getInstance();
    private final JsonWriter jsonWriter = JsonWriter.getInstance();

    private final ShopMapper shopMapper;

    @Autowired
    public ShopService(ShopRepository shopRepository, ShopMapper shopMapper) {
        this.shopRepository = shopRepository;
        this.shopMapper = shopMapper;
    }

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

    public List<ShopDto> listShopDto() {
        return shopRepository.
                findAll().
                stream().
                map(shopMapper::mapToShopDto).
                toList();
    }
}
