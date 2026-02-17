package org.cleanCode.task2.builder.store;

public class Order {

    private double discount;

    private String payment;

    private String product;

    public Order(double discount, String payment, String product) {
        this.discount = discount;
        this.payment = payment;
        this.product = product;
    }

    public Order (OrderBuilder orderBuilder){
        this.product = orderBuilder.product;
        this.discount = orderBuilder.discount;
        this.payment = orderBuilder.payment;
    }

    @Override
    public String toString() {
        return "Order{" +
                "discount=" + discount +
                ", payment='" + payment + '\'' +
                ", product='" + product + '\'' +
                '}';
    }

    static class OrderBuilder {

        private double discount;

        private String payment;

        private String product;

        public OrderBuilder setDiscount(double discount) {
            this.discount = discount;
            return this;
        }

        public OrderBuilder setPayment(String payment) {
            this.payment = payment;
            return this;
        }

        public OrderBuilder setProduct(String product) {
            this.product = product;
            return this;
        }

        public Order build(){
            return new Order(this);
        }
    }
}



