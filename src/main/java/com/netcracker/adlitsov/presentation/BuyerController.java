package com.netcracker.adlitsov.presentation;

import com.netcracker.adlitsov.dao.BuyersDAO;
import com.netcracker.adlitsov.domain.Buyer;
import com.netcracker.adlitsov.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/buyer", produces="application/json")
public class BuyerController {

    @Autowired
    BuyersDAO dao;

    @GetMapping()
    public List<Buyer> getBuyers() {
        List<Buyer> buyers = dao.getBuyers();
        return dao.getBuyers();
    }

    @GetMapping("/{id}")
    public Buyer getBuyer(@PathVariable("id") int id) {
        return dao.getBuyer(id);
    }

    @PostMapping(consumes="application/json")
    public Status addBuyer(@RequestBody Buyer buyer) {
        if (dao.addBuyer(buyer)) {
            return Status.OK.setDescription("Buyer successfully added with id:" + buyer.getId());
        } else {
            return Status.FAIL.setDescription("Error while adding buyer");
        }
    }

    @DeleteMapping()
    public Status deleteBuyers() {
        if (dao.deleteBuyers()) {
            return Status.OK.setDescription("All buyers successfully deleted");
        } else {
            return Status.FAIL.setDescription("Error while deleting buyers");
        }
    }

    @DeleteMapping("/{id}")
    public Status deleteBuyer(@PathVariable("id") int id) {
        if (dao.deleteBuyer(id)) {
            return Status.OK.setDescription("Buyer successfully deleted");
        } else {
            return Status.FAIL.setDescription("Error while deleting buyer");
        }
    }
}
