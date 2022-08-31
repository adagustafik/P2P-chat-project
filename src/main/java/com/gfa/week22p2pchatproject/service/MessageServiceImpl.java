package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.*;
import com.gfa.week22p2pchatproject.model.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final BrokerExchange brokerExchange;

    @Override
    public List<Message> getMessages(Integer channelId, String channelSecret) {
        Message[] messages = brokerExchange.post(
                "/api/channel/get-messages",
                HttpMethod.POST,
                new MessagesGetDto(channelId, channelSecret, 20),
                MessagesConsumeDto.class).getMessages();
        return Arrays.asList(messages);
    }

    @Override
    public MessagePostedDto postMessage(Integer channelId, String channelSecret, String message) {
        return brokerExchange.post(
                "/api/message",
                HttpMethod.POST,
                new MessagePostDto(channelId, channelSecret, message),
                MessagePostedDto.class);
    }
}
