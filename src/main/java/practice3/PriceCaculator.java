package practice3;

import java.math.BigDecimal;

public class PriceCaculator {
    private Order order;
    private BigDecimal subTotal;
    private BigDecimal tax;
    private BigDecimal grandTotal;

    public PriceCaculator(Order order) {
        this.order = order;
        subTotal  = new BigDecimal(0);
        tax = new BigDecimal(0);
        grandTotal = new BigDecimal(0);
    }

    public BigDecimal calculate() {
        // Subtract discounts to get total price
        subTotal = getTotalPrice().subtract(getDiscounts());

        // calculate tax
        tax = subTotal.multiply(order.getTax());

        // calculate GrandTotal
        grandTotal = subTotal.add(tax);

        return grandTotal;
    }

    private BigDecimal getDiscounts() {
        BigDecimal discounts = new BigDecimal(0);
        for (BigDecimal discount : order.getDiscounts()) {
            discounts = discounts.add(discount);
        }
        return discounts;
    }

    // Total up line items
    private BigDecimal getTotalPrice() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (OrderLineItem lineItem : order.getOrderLineItemList()) {
            totalPrice = totalPrice.add(lineItem.getPrice());
        }
        return totalPrice;
    }
}
