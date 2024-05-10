package com.eazybytes.services;

import com.eazybytes.interfaces.LogAspect;
import com.eazybytes.interfaces.Speakers;
import com.eazybytes.interfaces.Tyres;
import com.eazybytes.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.util.logging.Level;
import java.util.logging.Logger;

@Component
@Scope(BeanDefinition.SCOPE_SINGLETON)
public class VehicleServices {
    Logger logger = Logger.getLogger(VehicleServices.class.getName());

    private Speakers speakers;
    private Tyres tyres;

    @Autowired
    public VehicleServices(Speakers speakers, Tyres tyres) {
        this.speakers = speakers;
        this.tyres = tyres;
        System.out.println("VehicleServices object is created");
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

    public String moveVehicle(boolean vehicleStarted) {
//        Instant start = Instant.now();
//        logger.info("method execution start");

//        String status = null;
//        if(vehicleStarted) {
//            status = tyres.rotate();
//        }else {
//            logger.log(Level.SEVERE,"Vehicle not started to perform the operation");
//        }

//        logger.info("method execution end");
//        Instant finish = Instant.now();
//        long elapsedTime = Duration.between(start,finish).toMillis();
//        logger.info("Time took to execute the method : " + elapsedTime);
        return tyres.rotate();
//        throw new NullPointerException("Damn! Null Pointer Exception occurred");
    }

    @LogAspect
    public String playMusic(boolean vehicleStarted, Song song) {
//        Instant start = Instant.now();
//        logger.info("method execution start");

//        String music = null;
//        if(vehicleStarted) {
//            music = speakers.makeSound(song);
//        }else {
//            logger.log(Level.SEVERE,"Vehicle not started to perform the operation");
//        }

//        logger.info("method execution end");
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start,finish).toMillis();
//        logger.info("Time took to execute the method : " + timeElapsed);
        return speakers.makeSound(song);
    }

    public String applyBrake(boolean vehicleStarted) {
//        Instant start = Instant.now();
//        logger.info("method execution start");

//        String status = null;
//        if(vehicleStarted) {
//            status = tyres.stop();
//        }else {
//            logger.log(Level.SEVERE,"vehicle not started to perform the operation");
//        }

//        logger.info("method execution end");
//        Instant finish = Instant.now();
//        long timeElapsed = Duration.between(start, finish).toMillis();
//        logger.info("Time took to execute the method : " + timeElapsed);
        return tyres.stop();
    }


}
