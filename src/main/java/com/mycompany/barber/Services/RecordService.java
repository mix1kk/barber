package com.mycompany.barber.Services;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Utils.Mappers.LineFiller;
import com.mycompany.barber.Utils.Mappers.LineMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {
    private final LineService lineService;
    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Autowired
    public RecordService(LineService lineService) {
        this.lineService = lineService;

    }

    public RecordDTO findAllForUserOnDate(int userId, String userName, String date) {
        if (date == null) {
            date = LocalDate.now().toString();
        }
        List<LineDTO> filteredLinesDTO = LineFiller.fillWithVoidLines(lineService.findByUserIdAndDate(userId, LocalDate.parse(date, FORMATTER))).stream()
                .map(line -> LineMapper.addUserToLine(line, userId))
                .map(line -> LineMapper.mapToLineDTO(line))
                .collect(Collectors.toList());
        return new RecordDTO(userId, userName, convertDateToLongDate(date), date, filteredLinesDTO);
    }

    private String convertDateToLongDate(String date) {
        LocalDate tempDate = LocalDate.parse(date, FORMATTER);
        String[] stringArray = date.split("-");

        return tempDate.getDayOfWeek().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")) + ", " + stringArray[2] + " " +
                tempDate.getMonth().getDisplayName(TextStyle.FULL, Locale.forLanguageTag("ru")) + " " + stringArray[0];
    }

    public static String nextOrPreviousDate(String date, String direction) {
        if (date == null) {
            date = LocalDate.now().toString();
        }
        if (direction.equals("next")) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).plusDays(1).toString();
        } else if (direction.equals("prev")) {
            return LocalDate.parse(date, DateTimeFormatter.ofPattern("yyyy-MM-dd")).minusDays(1).toString();
        } else return date;
    }

    public static boolean checkOnNotEmptyRecord(LineDTO lineDTO) {
        return lineDTO.getClientName() != "" || lineDTO.getProcedureName() != "" || lineDTO.getProcedureCost() != "" || lineDTO.getComment() != "";

    }


    //    /**
//     * Находит все записи для пользователя от одной даты до другой, дата в формате гггг-мм-дд
//     *
//     * @param userId
//     * @param userName
//     * @param startDate
//     * @param endDate
//     * @return
//     */
//    public List<RecordDTO> findAllForUserFromDateToDate(int userId, String userName, String startDate, String endDate) {
//
//        if (startDate == null) {
//            startDate = LocalDate.now().toString();
//        }
//        if (endDate == null) {
//            endDate = LocalDate.now().toString();
//        }
//        LocalDate fromDate = LocalDate.parse(startDate, FORMATTER);
//        LocalDate toDate = LocalDate.parse(endDate, FORMATTER);
//        List<RecordDTO> records = new ArrayList<>();
//        if (toDate.isAfter(fromDate) || fromDate.isEqual(toDate)) {
//            List<Line> lines = lineService.findByUserIdAndDateBetween(userId, fromDate, toDate);
//            while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
//                LocalDate finalFromDate = fromDate;
////                LocalTime finalTime = LocalTime.of(0, 0, 0, 0);
//                List<Line> filteredAndFilledLines = LineFiller.fillWithVoidLines(lines.stream()
//                        .filter(line -> line.getDate().isEqual(finalFromDate)).collect(Collectors.toList()));
//                System.out.println(filteredAndFilledLines);
//                List<LineDTO> filteredLinesDTO = filteredAndFilledLines.stream()
//                        .map(line -> LineMapper.mapToLineDTO(line))
//                        .sorted(Comparator.comparing(LineDTO::getTime))
//                        .collect(Collectors.toList());
//
//                records.add(new RecordDTO(userId, userName, fromDate.format(FORMATTER).toString(), filteredLinesDTO));
//                fromDate = fromDate.plusDays(1);
//            }
//        }
//        return records;
//    }
//

}



