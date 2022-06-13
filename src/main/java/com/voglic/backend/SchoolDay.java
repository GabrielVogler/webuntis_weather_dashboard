package com.voglic.backend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

public class SchoolDay {
    public static ArrayList<Subject> subjects = new ArrayList<Subject>();
    Subject[] subjectArray;

    /**
     * Constructor of the Object, the Object is an array of Subjects
     * @param timetableFile the file of the Timetable from the WebUntisAPI
     */
    SchoolDay(String timetableFile) {
        int length = 0;
        try {
            while (true){
                new Subject(timetableFile, length);
                length++;
            }
        }catch (IndexOutOfBoundsException e){
            System.out.println("Succesfully read the length!");
        }
        subjectArray = new Subject[length];
        for (int i = 0; i < length; i++){
            subjectArray[i] = new Subject(timetableFile, i);
        }
        Arrays.sort(subjectArray, new Sortbystart());
        subjects = new ArrayList<>(Arrays.asList(subjectArray));
        fixLessons();
        subjectArray = subjects.toArray(new Subject[subjects.size()]);
    }

    private static void fixLessons(){
        for(int i = 0; i < subjects.size(); i++){
            for(int j = 0; j < subjects.size(); j++){
                if(subjects.get(i).starttime == subjects.get(j).starttime && i != j){
                    subjects.get(i).teacher += ", " + subjects.get(j).teacher;
                    subjects.remove(j);
                }
            }
        }
    }
    public static Subject getCurrentSubject(){
        int currentTime = Time.getTimeInt();
        for (int i = 0; i < subjects.size(); i++){
            if(subjects.get(i).starttime <= currentTime && subjects.get(i).endtime >= currentTime){
                return subjects.get(i);
            }
        }
        return new Subject();
    }

    private static String[][] getArray(int size){
        String[][] result = new String[size][5];
        for (int i = 0; i < size; i++){
            Subject subject = new Subject(getStarttime(i), getStarttime(i) + 50);
            result[i][0] = subject.name;
            result[i][1] = subject.teacher;
            result[i][2] = subject.room;
            result[i][3] = subject.color;
            result[i][4] = "" + subject.starttime;
        }
        return result;
    }

    public static String[][] getSubjects(){
        String[][] result = getArray(11);
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < subjects.size(); j++){
                Subject subject = subjects.get(j);
                if(Integer.parseInt(result[i][4]) == subject.starttime){
                    result[i][0] = subject.name;
                    result[i][1] = subject.teacher;
                    result[i][2] = subject.room;
                    result[i][3] = subject.color;
                    result[i][4] = "" + subject.starttime;
                }
            }
        }
        return result;
    }

    private static int getStarttime(int index){
        switch(index){
            case 0 -> {return 800;}
            case 1 -> {return 850;}
            case 2 -> {return 955;}
            case 3 -> {return 1045;}
            case 4 -> {return 1135;}
            case 5 -> {return 1235;}
            case 6 -> {return 1325;}
            case 7 -> {return 1415;}
            case 8 -> {return 1515;}
            case 9 -> {return 1605;}
            case 10 -> {return 1655;}
            default -> {return 0;}
        }
    }

    @Override
    public String toString() {
        return "SchoolDay: " + Arrays.toString(subjectArray);
    }
}

/**
 * a comparator for the subjects by their starttime
 */
class Sortbystart implements Comparator<Subject> {
    @Override
    public int compare(Subject a, Subject b){
        return a.starttime - b.starttime;
    }
}
