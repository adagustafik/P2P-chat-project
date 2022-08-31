package com.gfa.week22p2pchatproject.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class MessagePostDto {
    private Integer channelId;
    private String channelSecret;
    private String content;
}
