package com.maria.maria2.repository;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@ConfigurationProperties(prefix = "car")
@Component //żeby było zarządzane przez springa
@Validated
public class CarProperties {
    private String mark;
    private String model;
    //po uruchomieniu programu te pola zostaną automatycznie zapełnione
    //przez właściwości znajdujące się w application properties


    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }
}
