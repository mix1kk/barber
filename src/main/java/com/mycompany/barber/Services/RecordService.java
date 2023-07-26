package com.mycompany.barber.Services;

import com.mycompany.barber.DTO.LineDTO;
import com.mycompany.barber.DTO.RecordDTO;
import com.mycompany.barber.Models.Line;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RecordService {
    private final LineService lineService;
    private final ModelMapper modelMapper;


    @Autowired
    public RecordService(LineService lineService, ModelMapper modelMapper) {
        this.lineService = lineService;
        this.modelMapper = modelMapper;
    }

    public List<RecordDTO> findAllForUserFromDateToDate(int userId, String userName, String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        if (startDate == null) {
            startDate = LocalDate.now().toString();
        }
        if (endDate == null) {
            endDate = LocalDate.now().toString();
        }
        LocalDate fromDate = LocalDate.parse(startDate, formatter);
        LocalDate toDate = LocalDate.parse(endDate, formatter);
        List<RecordDTO> records = null;
        if (toDate.isAfter(fromDate)) {
            List<Line> lines = lineService.findByUserIdAndDateBetween(userId, fromDate, toDate);
            while (fromDate.isBefore(toDate) || fromDate.isEqual(toDate)) {
                LocalDate finalFromDate = fromDate;
                List<LineDTO> filteredLines = lines.stream().filter(line -> LocalDate.parse(line.getDate(), formatter).isEqual(finalFromDate))
                        .map(line -> convertToLineDTO(line))
                        .collect(Collectors.toList());
                records.add(new RecordDTO(userId, userName, fromDate.toString(), filteredLines));
                fromDate = fromDate.plusDays(1);
            }
        }
        return records;
    }

    private LineDTO convertToLineDTO(Line line) {
        return modelMapper.map(line, LineDTO.class);
    }
}


