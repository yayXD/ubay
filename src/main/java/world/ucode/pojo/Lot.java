package world.ucode.pojo;

import javax.persistence.*;
import java.util.Date;
import java.util.GregorianCalendar;

@Entity
@Table(name = "Lot")
public class Lot {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "Id", updatable = false, nullable = false)
    private int id;
    @Column(name = "itemNumber", nullable = false)
    private int itemNumder;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "type", nullable = false)
    private int type;
    @Column(name = "loginSeller", nullable = false)
    private String loginSeller;
    @Column(name = "description", nullable = false)
    private String description;
    @Column(name = "price", nullable = false)
    private int price;
    @Column(name = "increase", nullable = false)
    private int increase;
    @Column(name = "startTime", nullable = false)
    private String startTime;
    @Column(name = "duration", nullable = false)
    private String duration;
    @Column(name = "pictureName", updatable = true)
    private String pictuteName;
    @Column(name = "status", updatable = true)
    private String status;
    @Column(name = "winner", updatable = true)
    private String winner;
    @Column(name = "markCustomer", updatable = true)
    private int markCustomer;
    @Column(name = "feedbackCustomer", updatable = true)
    private String feedbackCustomer;
    @Column(name = "finalprice", updatable = true)
    private int finalprice;

    public Lot() {
    }

    public Lot(int id, int itemNumder, String name, int type, String loginSeller, String description, int price,
               int increase, String startTime, String duration, String pictureName, String status, String winner,
               int markCustomer, String feedbackCustomer, int finalprice) {
        this.id = id;
        this.itemNumder = itemNumder;
        this.name = name;
        this.type = type;
        this.loginSeller = loginSeller;
        this.description = description;
        this.price = price;
        this.increase = increase;
        this.startTime = startTime;
        this.duration = duration;
        this.pictuteName = pictureName;
        this.status = status;
        this.winner = winner;
        this.markCustomer = markCustomer;
        this.feedbackCustomer = feedbackCustomer;
        this.finalprice = finalprice;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setItemNumder(int itemNumder) {
        this.itemNumder = itemNumder;
    }

    public int getItemNumder() {
        return itemNumder;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getType() {
        return type;
    }

    public void setLoginSeller(String loginSeller) {
        this.loginSeller = loginSeller;
    }

    public String getLoginSeller() {
        return loginSeller;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setIncrease(int increase) {
        this.increase = increase;
    }

    public int getIncrease() {
        return increase;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDuration() {
        return duration;
    }

    public void setPictuteName(String pictuteName) {
        this.pictuteName = pictuteName;
    }

    public String getPictuteName() {
        return pictuteName;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public String getWinner() {
        return winner;
    }

    public void setMarkCustomer(int markCustomer) {
        this.markCustomer = markCustomer;
    }

    public int getMarkCustomer() {
        return markCustomer;
    }

    public void setFeedbackCustomer(String feedbackCustomer) {
        this.feedbackCustomer = feedbackCustomer;
    }

    public String getFeedbackCustomer() {
        return feedbackCustomer;
    }

    public int getFinalprice() {
    return finalprice;
}

    public void setFinalprice(int finalprice) {
        this.finalprice = finalprice;
    }
}

