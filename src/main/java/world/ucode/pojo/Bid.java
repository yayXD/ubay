package world.ucode.pojo;

import javax.persistence.*;

@Entity
@Table(name = "Bid")
public class Bid {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", updatable = false, nullable = false)
    private int id;
    @Column(name = "itemLot", nullable = false)
    private int itemLot;
    @Column(name = "customerLogin", nullable = false)
    private String customerLogin;
    @Column(name = "bid", nullable = false)
    private int bid;

    public Bid() {
    }

    public Bid(int id, int itemLot, String customerLogin, int bid) {
        this.id = id;
        this.itemLot = itemLot;
        this.customerLogin = customerLogin;
        this.bid = bid;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItemLot(int itemLot) {
        this.itemLot = itemLot;
    }

    public int getItemLot() {
        return id;
    }

    public void setCustomerLogin(String customerLogin) {
        this.customerLogin = customerLogin;
    }

    public String getCustomerLogin() {
        return customerLogin;
    }

    public void setBid(int bid) {
        this.bid = bid;
    }

    public int getBid() {
        return bid;
    }
}

