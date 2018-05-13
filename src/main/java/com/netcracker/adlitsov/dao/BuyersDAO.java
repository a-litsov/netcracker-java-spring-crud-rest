package com.netcracker.adlitsov.dao;

import com.netcracker.adlitsov.domain.Buyer;

import java.util.List;

public interface BuyersDAO {

    Buyer getBuyer(int id);

    List<Buyer> getBuyers();

    boolean addBuyer(Buyer buyer);

    boolean deleteBuyers();

    boolean deleteBuyer(int id);
}
