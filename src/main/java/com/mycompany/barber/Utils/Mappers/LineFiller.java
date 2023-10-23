package com.mycompany.barber.Utils.Mappers;

import com.mycompany.barber.Models.Line;
import com.mycompany.barber.Utils.TimeFiller;

import java.util.*;

public class LineFiller {
    public static List<Line> fillWithVoidLines(List<Line> list) {
        Map<String, Line> map = new HashMap<>();
        List<Line> filledList = new ArrayList<>();

        for (int i = 0; i < list.size(); i++) {//создаем мап из существующих в базе данных, где ключ это время, значение это вся запись
            map.put(list.get(i).getTime(), list.get(i));
        }

        for (String time : TimeFiller.createTimeList(6,0,23,30,30)) {
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
