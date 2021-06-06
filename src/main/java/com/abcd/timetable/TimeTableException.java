package com.abcd.timetable;

public class TimeTableException extends RuntimeException{
    private static final long serialVersionUID = -6557766057203885473L;

    protected final String message;

    public TimeTableException(String message){
        super();
        this.message = message;
    }

    @Override
    public String getMessage(){return message;}
}
