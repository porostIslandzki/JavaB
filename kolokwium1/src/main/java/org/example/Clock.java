package org.example;

import java.time.LocalTime;

public abstract class Clock {
    LocalTime localTime;

    public void setCurrentTime(){
        localTime = LocalTime.now();
    }

    public void setTime(int hours, int minutes, int seconds){
        if(((hours < 0) && (hours > 24)) && ((minutes < 0) &&))
    }
}
