package com.devteria.identityservice.controller;

import com.devteria.identityservice.dto.request.TableRequest;
import com.devteria.identityservice.dto.response.TableResponse;
import com.devteria.identityservice.entity.Table;
import com.devteria.identityservice.mapper.TableMapper;
import com.devteria.identityservice.service.TableService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tables")
public class TableController {

    @Autowired
    TableService tableService;

    @Autowired
    TableMapper tableMapper;

    @GetMapping("/available")
    public List<TableResponse> getTableAvailable() {
        List<Table> availableTables = tableService.getTableAvailable();
        return tableMapper.toResponseList(availableTables);
    }

    @PostMapping("/book")
    public TableResponse bookTable(@RequestBody TableRequest request) {
        Table bookedTable = tableService.bookTable(request.getId(), request.getReservationTime());
        return tableMapper.toResponse(bookedTable);
    }

    @PostMapping("/cancel/{tableId}")
    public void cancelReservation(@PathVariable Long tableId) {
        tableService.cancelReservation(tableId);
    }

}
