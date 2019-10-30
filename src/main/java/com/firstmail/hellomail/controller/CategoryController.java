package com.firstmail.hellomail.controller;


import com.firstmail.hellomail.dao.CategoryDao;
import com.firstmail.hellomail.dao.ProductionDAO;
import com.firstmail.hellomail.dao.RepairDataDao;
import com.firstmail.hellomail.domain.Category;
import com.firstmail.hellomail.domain.RepairData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@RestController
public class CategoryController {
    @Autowired
    CategoryDao categoryDao;
    @Autowired
    ProductionDAO productionDAO;
    @Autowired
    RepairDataDao repairDataDao;

    @RequestMapping("/listCategory/{id}")
    public Category get(@PathVariable("id") int id){
        Category one = categoryDao.getOne(id);
        System.out.println(one.getId());
        System.out.println(one.getName());
        return one;

    }

    @RequestMapping("/repair/{id}")
    public RepairData getRepair(@PathVariable("id") int id){
        RepairData one = repairDataDao.getOne(id);
        return one;
    }



}