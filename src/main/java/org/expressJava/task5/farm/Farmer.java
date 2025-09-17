package org.expressJava.task5.farm;

public class Farmer {
    private Cattle cattle;

    public void setCattle(Cattle cattle) {
        this.cattle = cattle;
    }

    public void showInfoAboutCattle() {
        cattle.product();
        cattle.needForCare();
    }
}
