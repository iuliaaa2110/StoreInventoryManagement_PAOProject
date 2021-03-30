package TheEntireSystem;

import java.math.BigDecimal;

public class OfferAndStock {
    private BigDecimal price;
    private Integer stock;

    public OfferAndStock(BigDecimal price, Integer stock){
        this.price = price;
        this.stock = stock;
    }

    public String toString() {

        return " ( price = " + price.toString() +" ; stock = " + stock.toString() + "; ) \n";
    }

    protected BigDecimal getPrice() {
        return price;
    }

    protected Integer getStock() {
        return stock;
    }

    protected void setPrice(BigDecimal price) {
        this.price = price;
    }

    protected void setStock(Integer stock) {
        this.stock = stock;
    }
}
