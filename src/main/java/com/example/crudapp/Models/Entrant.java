package com.example.crudapp.Models;

public class Entrant {
    String id, fio, spec, formeduc, status, avg, formid;

    public Entrant(String id, String fio, String formeduc, String spec,  String status, String avg, String formid) {
        this.id = id;
        this.fio = fio;
        this.spec = spec;
        this.formeduc = formeduc;
        this.status = status;
        this.avg = avg;
        this.formid = formid;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFio() {
        return fio;
    }

    public void setFio(String fio) {
        this.fio = fio;
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

    public String getAvg() {
        return avg;
    }

    public void setAvg(String avg) {
        this.avg = avg;
    }

    public String getFormid() {
        return formid;
    }

    public void setFormid(String formid) {
        this.formid = formid;
    }
}
