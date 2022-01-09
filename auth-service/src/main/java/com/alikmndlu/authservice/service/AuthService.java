package com.alikmndlu.authservice.service;

import com.alikmndlu.authservice.dto.UserCredentialsDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface AuthService {

    boolean isUserExistsWithCredentials(UserCredentialsDto credentialsDto) throws Exception;
}
