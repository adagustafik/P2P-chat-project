package com.gfa.week22p2pchatproject.controller;

import com.gfa.week22p2pchatproject.model.dto.ChannelCreateDto;
import com.gfa.week22p2pchatproject.model.dto.ChannelUpdateDto;
import com.gfa.week22p2pchatproject.model.Channel;
import com.gfa.week22p2pchatproject.service.AutContext;
import com.gfa.week22p2pchatproject.service.ChannelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class ChannelController {

    private final ChannelService channelService;
    private final AutContext autContext;

    @GetMapping("/channel")
    public String generateNewChannelForm(@ModelAttribute ChannelCreateDto channelCreateDto) {
        return "channel";
    }

    @PostMapping("/channel")
    public String handleNewChannelForm(@RequestParam String name,
                                       @RequestParam String description) {
        channelService.createChannel(name, description);
        return "redirect:/";
    }

    @GetMapping("/update")
    public String generateUpdateChannelForm(@ModelAttribute ChannelUpdateDto channelUpdateDto) {
        return "change";
    }

    @PostMapping("/update")
    public String handleUpdateChannelForm(ChannelUpdateDto channelUpdateDto, RedirectAttributes redirectAttributes) {
        if(Objects.isNull(channelUpdateDto.getId())) {
            redirectAttributes.addFlashAttribute("error", "Channel Id is required!");
            return "redirect:/update";
        }
        Channel channel = autContext.getChannelById(channelUpdateDto.getId());
        if (Objects.isNull(channelUpdateDto.getName())) {
            channelUpdateDto.setName(channel.getName());
        }
        if (Objects.isNull(channelUpdateDto.getDescription())) {
            channelUpdateDto.setDescription(channel.getDescription());
        }
        if (Objects.isNull(channelUpdateDto.getIconUrl())) {
            channelUpdateDto.setIconUrl(channel.getIconUrl());
        }
        channelService.updateChannel(channelUpdateDto);
        return "redirect:/";
    }
}
