package org.gustavosdaniel.ecommerce;

public class OrderMapper {

    public Order fromOrder(OrderRequest orderRequest) {
        if (orderRequest == null) {
            return null;
        }


        return new Order(
                orderRequest.id(),
                or
        )
    }
}
