package com.gfa.week22p2pchatproject.service;

import com.gfa.week22p2pchatproject.model.dto.UserApiDto;
import com.gfa.week22p2pchatproject.model.dto.UserIdDto;
import com.gfa.week22p2pchatproject.exception.LoginUnsuccessfulException;
import com.gfa.week22p2pchatproject.exception.NameAlreadyTakenException;

public interface UserService {

    UserIdDto registerUser(String login, String password) throws NameAlreadyTakenException;
    UserApiDto loginUser(String login, String password) throws LoginUnsuccessfulException;
    Boolean logout ();
    UserIdDto getLoggedUser();
    UserIdDto changeLoggedUser(String username, String avatarUrl);
}
