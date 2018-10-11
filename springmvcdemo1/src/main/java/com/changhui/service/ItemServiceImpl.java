package com.changhui.service;

import com.changhui.dao.ItemsMapper;
import com.changhui.pojo.Items;
import com.changhui.pojo.ItemsExample;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemsMapper itemsMapper;

    @Override
    public List<Items> findAll() {

        ItemsExample itemsExample = new ItemsExample();

        List<Items> items = itemsMapper.selectByExampleWithBLOBs(itemsExample);

        return items;
    }

    @Override
    public List<Items> queryItem(ItemsExample itemsExample) {

        List<Items> items = itemsMapper.selectByExampleWithBLOBs(itemsExample);

        return items;
    }

    @Override
    public Items queryItemById(int id) {
        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andIdEqualTo(id);
        List<Items> items = itemsMapper.selectByExampleWithBLOBs(itemsExample);
        return items.get(0);
    }

    @Override
    public void updateItem(Items items, ItemsExample itemsExample) {
        itemsMapper.updateByExampleSelective(items,itemsExample);
    }


}
