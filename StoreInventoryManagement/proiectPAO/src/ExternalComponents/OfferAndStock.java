package ExternalComponents;

import java.math.BigDecimal;

public class OfferAndStock {
    private BigDecimal price;
    private Integer stock;

    public OfferAndStock(BigDecimal price, Integer stock){
        this.price = price;
        this.stock = stock;
    }

    public String toString() {

        return " ( price = " + price.toString() +" ; stock = " + stock.toString() + " ) \n";
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Integer getStock() {
        return stock;
    }

    protected void setStock(Integer stock) {
        this.stock = stock;
    }
}
