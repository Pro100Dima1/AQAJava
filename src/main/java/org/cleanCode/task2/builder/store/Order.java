package org.cleanCode.task2.builder.store;

public class Order {
    private double discount;
    private double payment;
    private String product;

    public Order(double discount, double payment, String product) {
        this.discount = discount;
        this.payment = payment;
        this.product = product;
    }

    public Order(OrderBuilder orderBuilder) {
        this.discount = orderBuilder.discount;
        this.payment = orderBuilder.payment;
        this.product = orderBuilder.product;

    }

    @Override
    public String toString() {
        return "Order{" +
                "discount=" + discount +
                ", payment=" + payment +
                ", product='" + product + '\'' +
                '}';
    }

    static class OrderBuilder {
        private int discount;
        private double payment;
        private String product;

        public OrderBuilder setDiscount(int discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder setPayment(double payment) {
            this.payment = payment;
            return this;
        }

        public OrderBuilder setProduct(String product) {
            this.product = product;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}


