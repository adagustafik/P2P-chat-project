package com.gfa.week22p2pchatproject.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class MessagePostedDto {
    private String content;
    private LocalDateTime created;
    private UserIdDto author;

    @JsonProperty("created")
    public void setCreated(String data) {
        created = ZonedDateTime.parse(data, DateTimeFormatter.ISO_ZONED_DATE_TIME.withZone(ZoneId.systemDefault()))
                        .toLocalDateTime();
    }
}
