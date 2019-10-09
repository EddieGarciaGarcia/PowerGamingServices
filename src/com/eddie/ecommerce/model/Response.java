package com.eddie.ecommerce.model;

import com.google.gson.JsonObject;

public class Response {
    private String status;
    private String statusMsg;
    private JsonObject salida;

    public Response(){}

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getStatusMsg() {
        return statusMsg;
    }

    public void setStatusMsg(String statusMsg) {
        this.statusMsg = statusMsg;
    }

    public JsonObject getSalida() {
        return salida;
    }

    public void setSalida(JsonObject salida) {
        this.salida = salida;
    }
}
