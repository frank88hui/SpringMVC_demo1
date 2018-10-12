package com.changhui.controller;

import com.changhui.pojo.Items;
import com.changhui.pojo.ItemsExample;
import com.changhui.pojo.QueryVo;
import com.changhui.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ItemsController {

    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpServletRequest request;


    @RequestMapping("/itemList.action")
    public ModelAndView itemList() {
        ModelAndView view = new ModelAndView();
        List<Items> items = itemService.findAll();
        view.addObject("itemList", items);
        view.setViewName("itemList");
        return view;
    }

    @RequestMapping("/queryItem.action")
    public ModelAndView queryItems(QueryVo queryVo) {

        ModelAndView view = new ModelAndView();
        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andNameLike("%"+queryVo.getItems().getName()+"%").andDetailLike("%"+queryVo.getItems().getDetail()+"%");
        List<Items> items=itemService.queryItem(itemsExample);
        view.addObject("itemList", items);
        view.setViewName("itemList");
        return view;
    }

    @RequestMapping("/itemEdit.action")
    public String editItem(Model model) {
        String id = request.getParameter("id");
        Items items = itemService.queryItemById(Integer.parseInt(id));
        model.addAttribute("item",items);
        return "editItem";
    }

    @RequestMapping("/updateitem.action")
    public String updateItem(Items item) {

        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("id")));
        itemService.updateItem(item,itemsExample);
        return "success";
    }


}
