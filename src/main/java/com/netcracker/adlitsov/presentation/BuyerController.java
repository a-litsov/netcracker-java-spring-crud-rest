package com.netcracker.adlitsov.presentation;

import com.netcracker.adlitsov.dao.BuyersDAO;
import com.netcracker.adlitsov.domain.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path="/buyer", produces="application/json")
public class BuyerController {

    @Autowired
    BuyersDAO dao;

    @GetMapping()
    public List<Buyer> getBuyers() {
        return dao.getBuyers();
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable("id") int id) {
        return dao.getBuyer(id);
    }
}
