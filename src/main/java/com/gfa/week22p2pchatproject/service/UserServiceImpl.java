package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.UserChangeDto;
import com.gfa.week22p2pchatproject.model.User;
import com.gfa.week22p2pchatproject.model.dto.UserApiDto;
import com.gfa.week22p2pchatproject.model.dto.UserIdDto;
import com.gfa.week22p2pchatproject.exception.LoginUnsuccessfulException;
import com.gfa.week22p2pchatproject.exception.NameAlreadyTakenException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final BrokerExchange brokerExchange;
    private final String apiUser = "/api/user/";
    private final AutContext autContext;

    private final ChannelService channelService;

    @Override
    public UserIdDto registerUser(String login, String password) throws NameAlreadyTakenException {
        UserIdDto userDto;
        try {
            userDto = brokerExchange.post(
                    apiUser + "register",
                    HttpMethod.POST,
                    new User(login, password),
                    UserIdDto.class);
        } catch (HttpClientErrorException exception) {
            throw new NameAlreadyTakenException(exception.getMessage());
        }
        return userDto;
    }

    @Override
    public UserApiDto loginUser(String login, String password) throws LoginUnsuccessfulException {
        UserApiDto userApiDto;
        try {
            userApiDto = brokerExchange.post(
                    apiUser + "login",
                    HttpMethod.POST,
                    new User(login, password),
                    UserApiDto.class);
        } catch (HttpClientErrorException exception) {
            throw new LoginUnsuccessfulException(exception.getMessage());
        }
        autContext.setApiKey(userApiDto.getApiKey());
        autContext.setAvatarUrl(userApiDto.getAvatarUrl());
        autContext.setChannels(channelService.getUserChannels());
        return userApiDto;
    }

    @Override
    public Boolean logout() {
        Boolean result = brokerExchange.post(
                apiUser + "logout",
                HttpMethod.POST,
                null,
                Boolean.class);
        autContext.setApiKey(null);
        return result;
    }

    @Override
    public UserIdDto getLoggedUser() {
        return brokerExchange.post(
                apiUser + "info",
                HttpMethod.GET,
                null,
                UserIdDto.class);
    }

    @Override
    public UserIdDto changeLoggedUser(String username, String avatarUrl) {
        return brokerExchange.post(
                apiUser + "update",
                HttpMethod.POST,
                new UserChangeDto(username, avatarUrl),
                UserIdDto.class);
    }
}
