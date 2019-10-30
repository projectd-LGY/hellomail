package com.firstmail.hellomail.service;


import com.firstmail.hellomail.dao.RepairDataDao;
import com.firstmail.hellomail.domain.RepairData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RepairDataService {

    @Autowired
    RepairDataDao repairDataDao;

    public RepairData getOne(int id){
        RepairData one = repairDataDao.getOne(id);
        return one;
    }





}
