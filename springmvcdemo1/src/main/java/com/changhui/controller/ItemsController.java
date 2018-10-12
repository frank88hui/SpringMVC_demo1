package com.changhui.controller;

import com.changhui.pojo.Items;
import com.changhui.pojo.ItemsExample;
import com.changhui.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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
    public ModelAndView queryItems() throws UnsupportedEncodingException {

        request.setCharacterEncoding("utf-8");
        ModelAndView view = new ModelAndView();
        String name = request.getParameter("items.name");
        String detail = request.getParameter("items.detail");
        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andNameLike("%"+name+"%").andDetailLike("%"+detail+"%");
        List<Items> items=itemService.queryItem(itemsExample);
        view.addObject("itemList", items);
        view.setViewName("itemList");
        return view;
    }

    @RequestMapping("/itemEdit.action")
    public ModelAndView editItem() throws UnsupportedEncodingException {
        ModelAndView view = new ModelAndView();
        request.setCharacterEncoding("utf-8");
        String id = request.getParameter("id");
        Items items = itemService.queryItemById(Integer.parseInt(id));
        view.addObject("item",items);
        view.setViewName("editItem");
        return view;
    }

    @RequestMapping("/updateitem.action")
    public ModelAndView updateItem() throws UnsupportedEncodingException, ParseException {
        ModelAndView view = new ModelAndView();
        request.setCharacterEncoding("utf-8");
        Items items = new Items();
        ItemsExample itemsExample = new ItemsExample();

        itemsExample.createCriteria().andIdEqualTo(Integer.parseInt(request.getParameter("id")));
        items.setName(request.getParameter("name"));
        items.setPrice(Float.parseFloat(request.getParameter("price")));
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        items.setCreatetime(format.parse(request.getParameter("createtime")));
        items.setDetail(request.getParameter("detail"));

        itemService.updateItem(items,itemsExample);

        view.setViewName("success");
        return view;
    }


}
