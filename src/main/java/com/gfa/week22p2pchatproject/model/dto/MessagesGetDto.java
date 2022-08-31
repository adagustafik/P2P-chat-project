package com.gfa.week22p2pchatproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MessagesGetDto {
    private Integer channelId;
    private String channelSecret;
    private int count;
}
