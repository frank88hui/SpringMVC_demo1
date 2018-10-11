package com.changhui.service;


import com.changhui.pojo.Items;
import com.changhui.pojo.ItemsExample;

import java.util.List;

public interface ItemService {

    List<Items> findAll();

    List<Items> queryItem(ItemsExample itemsExample);

    Items queryItemById(int id);

    void updateItem(Items items, ItemsExample itemsExample);
}
