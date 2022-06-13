package com.voglic.backend;

import javafx.scene.paint.Color;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileReader;
import java.io.IOException;

public class Subject {
    public String name;
    String teacher;
    String room;
    String className;
    int starttime;
    int endtime;
    public static String color;

    /**
     * Constructor of a Subject, reads the timetable and makes a Subject with essential information
     * @param timetableFile File of the timetable JSON
     * @param index         index of the Subject in the timetable file
     */
    public Subject(String timetableFile, int index) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(timetableFile));
            JSONObject subjectJSON = (JSONObject) ((JSONArray) obj).get(index);
            this.name = getVariable(subjectJSON, "su");
            this.className = getVariable(subjectJSON, "kl");
            this.teacher = getVariable(subjectJSON, "te");
            this.room = getVariable(subjectJSON, "ro");
            this.starttime = Integer.parseInt(getVariable(subjectJSON, "startTime", true));
            this.endtime = Integer.parseInt(getVariable(subjectJSON, "endTime", true));
            this.color = getColor();
        } catch (ParseException pe) {
            System.out.println("position: " + pe.getPosition());
            System.out.println(pe);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Subject (String name, String teacher, String room, String className, int starttime, int endtime){
        this.name = name;
        this.teacher = teacher;
        this.room = room;
        this.className = className;
        this.starttime = starttime;
        this.endtime = endtime;
        this.color = getColor();
    }
    public Subject(){
        this("Frei", "", "", "", 0, 0);
    }
    public Subject(int starttime, int endtime){
        this("Frei", "", "", "", starttime, endtime);
    }

    /**
     * returns the variables which are picked from an JSONObject
     * @param subjectJSON
     * @param variable
     * @param ifTime
     * @param shortName
     * @return
     */
    private static String getVariable(JSONObject subjectJSON, String variable, boolean ifTime, boolean shortName) {
        String nameSwitch = "";
        if (shortName) nameSwitch = "name";
        else nameSwitch = "longname";
        if (ifTime) return subjectJSON.get(variable).toString();
        else return ((JSONObject) (((JSONArray) subjectJSON.get(variable))).get(0)).get(nameSwitch).toString();
    }
    private static String getVariable(JSONObject subjectJSON, String variable, boolean ifTime) {
        return getVariable(subjectJSON, variable, ifTime, true);
    }
    private static String getVariable(JSONObject subjectJSON, String variable) {
        return getVariable(subjectJSON, variable, false, true);
    }

    /**
     *
     * @return  defined fixed Color of Subject
     */
    private String getColor(){
        switch (name){
            case "D" -> {return "#F4D80B";}
            case "BW" -> {return "#8000FF";}
            case "GETE" -> {return "#800040";}
            case "BSPK" -> {return "#008000";}
            case "BS" -> {return "#A0A0FE";}
            case "COPR" -> {return "#0080FF";}
            case "AM" -> {return "#FFFF00";}
            case "SEW" -> {return "#00FF00";}
            case "CH" -> {return "#B3DEFF";}
            case "GGP" -> {return "#80FFFF";}
            case "E" -> {return "#0080FF";}
            case "GINF" -> {return "#FFFF00";}
            case "RK" -> {return "#418770";}
            case "MEDT" -> {return "#FF0080";}
            case "NWT" -> {return "#8080FF";}
            case "ITSI" -> {return "#E0E000";}
            default -> {return "#404040";}
        }
    }

    public static void setColor(){
        color = "#404040";
    }

    @Override
    public String toString() {
        //return name + " - " + teacher + ": " + starttime + " - " + endtime;
        return name + ": " + starttime;
    }
}