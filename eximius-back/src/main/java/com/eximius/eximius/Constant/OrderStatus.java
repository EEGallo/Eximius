package com.eximius.eximius.Constant;

public enum OrderStatus {
    PENDING("Pendiente"),
    PAID("Pagado"),
    SHIPPED("Enviado"),
    COMPLETED("Completado");

    private final String displayName;

    OrderStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
