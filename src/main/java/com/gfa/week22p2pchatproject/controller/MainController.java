package com.gfa.week22p2pchatproject.controller;

import com.gfa.week22p2pchatproject.service.AutContext;
import com.gfa.week22p2pchatproject.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final AutContext autContext;
    private final MessageService messageService;

    @GetMapping
    public String generateIndex(Model model,
                                @RequestParam(required = false) Integer channelId) {
        if (Objects.isNull(autContext.getApiKey())) {
            return "redirect:/login";
        }
        model.addAttribute("avatarUrl", autContext.getAvatarUrl());
        model.addAttribute("channels", autContext.getChannels());
        if (Objects.nonNull(channelId)) {
            String channelSecret = autContext.getChannelById(channelId).getSecret();
            model.addAttribute("channelId", channelId);
            model.addAttribute("channelSecret", channelSecret);
            model.addAttribute("messages", messageService.getMessages(channelId, channelSecret));
        } else {
            model.addAttribute("messages", messageService.getMessages(null, null));
        }
        return "index";
    }

    @PostMapping
    public String handleNewMessage(@RequestParam(required = false) Integer channelId,
                                   @RequestParam(required = false) String message,
                                   RedirectAttributes redirectAttributes) {
        if (message.isBlank()) {
            if (Objects.nonNull(channelId)) {
                redirectAttributes.addAttribute("channelId", channelId);
            }
            return "redirect:/";
        }
        if (Objects.nonNull(channelId)) {
            String channelSecret = autContext.getChannelById(channelId).getSecret();
            messageService.postMessage(channelId, channelSecret, message);
            redirectAttributes.addAttribute("channelId", channelId);
        } else {
            messageService.postMessage(null, null, message);
        }
        return "redirect:/";
    }
}
