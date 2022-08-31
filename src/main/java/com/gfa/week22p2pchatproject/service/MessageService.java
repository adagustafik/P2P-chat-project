package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.MessagePostedDto;
import com.gfa.week22p2pchatproject.model.Message;

import java.util.List;

public interface MessageService {

    List<Message> getMessages(Integer channelId, String channelSecret);
    MessagePostedDto postMessage(Integer channelId, String channelSecret, String message);
}
