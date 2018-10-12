package com.changhui.controller;

import com.changhui.pojo.Items;
import com.changhui.pojo.ItemsExample;
import com.changhui.pojo.QueryVo;
import com.changhui.service.ItemService;
import org.apache.commons.io.IOUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.util.List;
import java.util.UUID;

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
    public String queryItems(Model model, QueryVo queryVo) {

           /* for (Integer s : queryVo.getIds()) {
                System.out.println(s);
            }*/

        ItemsExample itemsExample = new ItemsExample();
        try {
            itemsExample.createCriteria().andIdIn(queryVo.getIds()).andNameLike("%" + queryVo.getItems().getName() + "%").andDetailLike("%" + queryVo.getItems().getDetail() + "%");
        }catch (Exception e){
            itemsExample.createCriteria().andNameLike("%" + queryVo.getItems().getName() + "%").andDetailLike("%" + queryVo.getItems().getDetail() + "%");
        }
        List<Items> items = itemService.queryItem(itemsExample);
        model.addAttribute("itemList", items);

        return "itemList";
    }

    @RequestMapping("/itemEdit.action")
    public String editItem(Model model) {
        String id = request.getParameter("id");
        Items items = itemService.queryItemById(Integer.parseInt(id));
        model.addAttribute("item", items);
        return "editItem";
    }

    @RequestMapping("/updateitem.action")
    public String updateItem(Items item , MultipartFile pictureFile) throws IOException {

        String name = pictureFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()+name.substring(name.indexOf("."));
        File file = new File("D:\\Develop\\upload\\pics\\"+newName);
        pictureFile.transferTo(file);
        item.setPic(newName);

        ItemsExample itemsExample = new ItemsExample();
        itemsExample.createCriteria().andIdEqualTo(item.getId());
        itemService.updateItem(item, itemsExample);

        return "success";
    }

    @RequestMapping("/ajaxJson.action")
    @ResponseBody
    public Items ajaxJson(@RequestBody Items items) {
        System.out.println(items);
        return items;
    }

    @RequestMapping("/ajaxUpload.action")
    @ResponseBody
    public String ajaxUpload(MultipartFile pictureFile) throws IOException {

            String name = pictureFile.getOriginalFilename();
            String newName = UUID.randomUUID().toString()+name.substring(name.indexOf("."));
            File file = new File("D:\\Develop\\upload\\pics\\"+newName);
            pictureFile.transferTo(file);

        return newName;
    }
}
