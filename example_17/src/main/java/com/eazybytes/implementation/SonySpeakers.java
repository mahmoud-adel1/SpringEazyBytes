package com.eazybytes.implementation;

import com.eazybytes.interfaces.Speakers;
import com.eazybytes.model.Song;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SonySpeakers implements Speakers {

    @Override
    public String makeSound(Song song) {
        return "Playing the song " + song.getTitle() +
                "by " + song.getSingerName() +
                "with Sony speakers";
    }
}
