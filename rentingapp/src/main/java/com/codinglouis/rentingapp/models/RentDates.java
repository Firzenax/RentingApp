package com.codinglouis.rentingapp.models;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class RentDates {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate rentStartDateStr;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate rentEndDateStr;

    public RentDates() {
    }

    public RentDates(LocalDate rentStartDateStr, LocalDate rentEndDateStr) {
        this.rentStartDateStr = rentStartDateStr;
        this.rentEndDateStr = rentEndDateStr;
    }

    public LocalDate getRentStartDateStr() {
        return rentStartDateStr;
    }

    public void setRentStartDateStr(LocalDate rentStartDateStr) {
        this.rentStartDateStr = rentStartDateStr;
    }

    public LocalDate getRentEndDateStr() {
        return rentEndDateStr;
    }

    public void setRentEndDateStr(LocalDate rentEndDateStr) {
        this.rentEndDateStr = rentEndDateStr;
    }
}
