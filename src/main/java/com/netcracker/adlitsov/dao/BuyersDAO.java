package com.netcracker.adlitsov.dao;

import com.netcracker.adlitsov.domain.Buyer;

import java.util.List;

public interface BuyersDAO {

    public Buyer getBuyer(int id);

    public List<Buyer> getBuyers();
}
