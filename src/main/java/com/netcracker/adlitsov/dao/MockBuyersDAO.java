package com.netcracker.adlitsov.dao;

import com.netcracker.adlitsov.domain.Buyer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MockBuyersDAO implements BuyersDAO {

    public Buyer getBuyer(int id) {
        return new Buyer(id, "Mock buyer", "Mock district", -1);
    }

    public List<Buyer> getBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            buyers.add(new Buyer(i+1, "Mock buyer#" + (i+1), "Mock district#" + (i+1), i));
        }
        return buyers;
    }
}
