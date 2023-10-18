package com.mycompany.barber.Utils.Mappers;

import com.mycompany.barber.Models.Line;

import java.util.*;

public class LineFiller {
    public static List<Line> fillWithVoidLines(List<Line> list) {
        Map<String, Line> map = new HashMap<>();
        List<Line> filledList = new ArrayList<>();
        List<String> timeList = new ArrayList<>();//массив для хранения времени работы



        for (int i = 0; i < list.size(); i++) {//создаем мап из существующих в базе данных, где ключ это время, значение это вся запись
            map.put(list.get(i).getTime(), list.get(i));
        }

        for (int i = 6; i < 23; i++) {//создаем массив значений времени, от 6:00 до 22:30
            if (i<10){
                timeList.add("0"+ i + ":" + "00");
                timeList.add("0"+ i + ":" + "30");
            }
            else {
                timeList.add(i + ":" + "00");
                timeList.add(i + ":" + "30");
            }
        }


        for (String time : timeList) {
            Line newLine = new Line();// пустая запись
            if (map.get(time) != null) {
                filledList.add(map.get(time));
            } else {
                newLine.setTime(time);
                filledList.add(newLine);
            }
        }
        return filledList;
    }
}
