package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.Channel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Getter @Setter
public class AutContext {
    private String apiKey;
    private String avatarUrl;
    private List<Channel> channels;

    public Channel getChannelById(Integer id) {
        return channels.stream()
                .filter(x -> x.getId().equals(id))
                .findAny().orElse(null);
    }
}
