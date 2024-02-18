package org.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public class FlightDTO {
        private String code;
        private String origin;
        private String destination;
        private String date;
        private String id;

}