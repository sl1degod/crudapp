package com.example.crudapp.Models;

public class Entrant {
    String id, spec, formeduc, status;

    public Entrant(String id, String formeduc, String spec,  String status) {
        this.id = id;
        this.spec = spec;
        this.formeduc = formeduc;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getFormeduc() {
        return formeduc;
    }

    public void setFormeduc(String formeduc) {
        this.formeduc = formeduc;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


}
