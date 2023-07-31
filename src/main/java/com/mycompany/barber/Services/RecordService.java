package com.mycompany.barber.Services;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Models.Line;
import com.mycompany.barber.Utils.Mappers.LineMapper;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {
    private final LineService lineService;
    private final ModelMapper modelMapper;
    final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    @Autowired
    public RecordService(LineService lineService, ModelMapper modelMapper) {
        this.lineService = lineService;
        this.modelMapper = modelMapper;
    }

    public List<RecordDTO> findAllForUserFromDateToDate(int userId, String userName, String startDate, String endDate) {

        if (startDate == null) {
            startDate = LocalDate.now().toString();
        }
        if (endDate == null) {
            endDate = LocalDate.now().toString();
        }
        LocalDate fromDate = LocalDate.parse(startDate, FORMATTER);
        LocalDate toDate = LocalDate.parse(endDate, FORMATTER);
        List<RecordDTO> records = new ArrayList<>();
        if (toDate.isAfter(fromDate)|| fromDate.isEqual(toDate)) {
            List<Line> lines = lineService.findByUserIdAndDateBetween(userId, fromDate, toDate);
            while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
                LocalDate finalFromDate = fromDate;
                LocalTime finalTime = LocalTime.of(0, 0, 0, 0);
                List<LineDTO> filteredLines = lines.stream()
                        .filter(line -> line.getDate().isEqual(finalFromDate))
                        .map(line -> LineMapper.mapToLineDTO(line))
                        .sorted(Comparator.comparing(LineDTO::getTime))
                        .collect(Collectors.toList());
                records.add(new RecordDTO(userId, userName, fromDate.format(FORMATTER).toString(), filteredLines));
                fromDate = fromDate.plusDays(1);
            }
        }
        return records;
    }
    private LocalTime fromStringToLocalTime (String timeFromDTO){
        return LocalTime.parse(timeFromDTO, DateTimeFormatter.ofPattern("HH:mm"));
    }
}


