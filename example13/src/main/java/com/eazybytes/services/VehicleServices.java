package com.eazybytes.services;

import com.eazybytes.interfaces.Speakers;
import com.eazybytes.interfaces.Tyres;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VehicleServices {
    private Speakers speakers;
    private Tyres tyres;

    @Autowired
    public VehicleServices(Speakers speakers, Tyres tyres) {
        this.speakers = speakers;
        this.tyres = tyres;
    }

    public Speakers getSpeakers() {
        return speakers;
    }

    public void setSpeakers(Speakers speakers) {
        this.speakers = speakers;
    }

    public Tyres getTyres() {
        return tyres;
    }

    public void setTyres(Tyres tyres) {
        this.tyres = tyres;
    }


}
