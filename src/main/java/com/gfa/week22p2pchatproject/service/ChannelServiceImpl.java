package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.ChannelCreateDto;
import com.gfa.week22p2pchatproject.model.dto.ChannelUpdateDto;
import com.gfa.week22p2pchatproject.model.Channel;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ChannelServiceImpl implements ChannelService {

    private final BrokerExchange brokerExchange;
    private final String apiChannel = "/api/channel/";
    private final AutContext autContext;

    @Override
    public List<Channel> getUserChannels() {
        Channel[] channels = brokerExchange.post(
                apiChannel + "user-channels",
                HttpMethod.GET,
                null,
                Channel[].class);
        return new ArrayList<>(Arrays.asList(channels));
    }

    @Override
    public Channel createChannel(String name, String description) {
        Channel channel = brokerExchange.post(
                apiChannel,
                HttpMethod.POST,
                new ChannelCreateDto(name, description),
                Channel.class);
        List<Channel> myChannels = autContext.getChannels();
        myChannels.add(channel);
        autContext.setChannels(myChannels);
        return channel;
    }

    @Override
    public Channel updateChannel(ChannelUpdateDto channelUpdateDto) {
        Channel updated = brokerExchange.post(
                apiChannel + "update",
                HttpMethod.POST,
                channelUpdateDto,
                Channel.class);
        Channel toBeRemoved = autContext.getChannelById(channelUpdateDto.getId());
        updated.setSecret(toBeRemoved.getSecret());
        List<Channel> myChannels = autContext.getChannels();
        myChannels.remove(toBeRemoved);
        myChannels.add(updated);
        autContext.setChannels(myChannels);
        return updated;
    }
}
