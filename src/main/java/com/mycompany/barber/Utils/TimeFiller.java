package com.mycompany.barber.Utils;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Создает массив стрингов времени
 */
public class TimeFiller {
    public static List<String> createTimeList(int startHour, int startMinute, int finishHour, int finishMinute, int step) {
        List<String> timeList = new ArrayList<>();//массив для хранения времени работы
        LocalTime tempTime = LocalTime.of(startHour, startMinute);
        LocalTime finishTime = LocalTime.of(finishHour, finishMinute);
        while (tempTime.isBefore(finishTime)) {//создаем массив значений времени
            timeList.add(tempTime.toString());
            if (tempTime.plusMinutes(step).isBefore(tempTime)) {
                break;
            } else {
                tempTime = tempTime.plusMinutes(step);
            }
        }
        return timeList;
    }
}
