package com.gfa.week22p2pchatproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class ChannelCreateDto {
    private String name;
    private String description;
}
