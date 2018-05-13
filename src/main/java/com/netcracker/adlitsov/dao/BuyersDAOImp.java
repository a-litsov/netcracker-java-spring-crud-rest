package com.netcracker.adlitsov.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.netcracker.adlitsov.domain.Buyer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Primary
@Service
public class BuyersDAOImp implements BuyersDAO {

    @Autowired
    DataSource ds;

    @Override
    public Buyer getBuyer(int id) {
        Buyer buyer = null;
        try (Connection con = ds.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM buyer WHERE id = ?");
            stmt.setInt(1, id);
            ResultSet res = stmt.executeQuery();
            if (res.next()) {
                String lastName = res.getString("last_name");
                String district = res.getString("district");
                int discount = res.getInt("discount");
                buyer = new Buyer(id, lastName, district, discount);
            }
        } catch (SQLException e) {
            System.err.println("Error while getting buyer by id from db!");
            e.printStackTrace();
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(buyer));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return buyer;
    }

    @Override
    public List<Buyer> getBuyers() {
        List<Buyer> buyers = new ArrayList<>();
        try (Connection con = ds.getConnection()){
            PreparedStatement stmt = con.prepareStatement("SELECT * FROM buyer");
            ResultSet res = stmt.executeQuery();
            while (res.next()) {
                int id = res.getInt("id");
                String lastName = res.getString("last_name");
                String district = res.getString("district");
                int discount = res.getInt("discount");
                buyers.add(new Buyer(id, lastName, district, discount));
            }
        } catch (SQLException e) {
            System.err.println("Error while getting all buyers from db!");
            e.printStackTrace();
        }
        return buyers;
    }

    @Override
    public boolean addBuyer(Buyer buyer) {
        boolean res = false;
        try (Connection con = ds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO buyer (last_name, district, discount) " +
                                                                  "VALUES (?, ?, ?) RETURNING id");

            // Ignoring user id
            stmt.setString(1, buyer.getLastName());
            stmt.setString(2, buyer.getDistrict());
            stmt.setInt(3, buyer.getDiscount());

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                res = true;
                buyer.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.err.println("Error while adding buyer to db!");
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean deleteBuyers() {
        boolean res = false;
        try (Connection con = ds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM buyer");

            int rowsChanged = stmt.executeUpdate();
            if (rowsChanged > 0) {
                res = true;
            }
        } catch (SQLException e) {
            System.err.println("Error while removing all buyers from db!");
            e.printStackTrace();
        }
        return res;
    }

    @Override
    public boolean deleteBuyer(int id) {
        boolean res = false;
        try (Connection con = ds.getConnection()) {
            PreparedStatement stmt = con.prepareStatement("DELETE FROM buyer WHERE id = ?");
            stmt.setInt(1, id);

            int rowsChanged = stmt.executeUpdate();
            if (rowsChanged > 0) {
                res = true;
            }
        } catch (SQLException e) {
            System.err.println("Error while removing buyer#" + id + " from db!");
            e.printStackTrace();
        }
        return res;
    }
}
