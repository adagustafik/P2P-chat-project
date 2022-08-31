package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.ChannelUpdateDto;
import com.gfa.week22p2pchatproject.model.Channel;

import java.util.List;

public interface ChannelService {

    List<Channel> getUserChannels();
    Channel createChannel(String name, String description);
    Channel updateChannel(ChannelUpdateDto channelUpdateDto);
}
