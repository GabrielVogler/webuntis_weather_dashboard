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
    public Color color;

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
        this("XYZ", "Muster", "000", "0Xx", 0, 0);
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
    private Color getColor(){
        switch (name){
            case "D" -> {return Color.ORANGE;}
            case "BW" -> {return Color.PURPLE;}
            case "GETE" -> {return Color.BROWN;}
            case "BSPK" -> {return Color.GREEN;}
            case "BS" -> {return Color.MEDIUMPURPLE;}
            case "COPR" -> {return Color.DARKBLUE;}
            case "AM" -> {return Color.LIGHTYELLOW;}
            case "SEW" -> {return Color.LIGHTGREEN;}
            case "CH" -> {return Color.MEDIUMBLUE;}
            case "GGP" -> {return Color.LIGHTBLUE;}
            case "E" -> {return Color.BLUE;}
            case "GINF" -> {return Color.YELLOW;}
            case "RK" -> {return Color.MEDIUMSEAGREEN;}
            case "MEDT" -> {return Color.PINK;}
            case "NWT" -> {return Color.VIOLET;}
            case "ITSI" -> {return Color.YELLOWGREEN;}
            default -> {return Color.LIGHTGRAY;}
        }
    }

    @Override
    public String toString() {
        //return name + " - " + teacher + ": " + starttime + " - " + endtime;
        return name + ": " + starttime;
    }
}