package com.demo.coinbase.service;

import com.demo.coinbase.dto.requests.ActivateRequest;
import com.demo.coinbase.dto.requests.LoginRequest;
import com.demo.coinbase.dto.requests.RegisterRequest;
import com.demo.coinbase.dto.responses.ActivateResponse;
import com.demo.coinbase.dto.responses.LoginResponse;
import com.demo.coinbase.dto.responses.RegisterResponse;

public interface UserService {
    RegisterResponse register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);

    ActivateResponse activate(ActivateRequest activateRequest);

}
