package com.gfa.week22p2pchatproject.model.dto;

import com.gfa.week22p2pchatproject.model.Message;
import lombok.Data;

@Data
public class MessagesConsumeDto {
    private Message[] messages;
}
