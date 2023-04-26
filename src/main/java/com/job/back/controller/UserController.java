package com.job.back.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.job.back.common.constant.ApiPattern;
import com.job.back.dto.request.user.ValidateUserEmailDto;
import com.job.back.dto.request.user.ValidateUserTelNumberDto;
import com.job.back.dto.response.ResponseDto;
import com.job.back.dto.response.user.GetUserResponseDto;
import com.job.back.dto.response.user.ValidateEmailResponseDto;
import com.job.back.dto.response.user.ValidateTelNumberResponseDto;
import com.job.back.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
@Api(description = "유저 모듈")
@RestController
@RequestMapping(ApiPattern.USER)
public class UserController {
    
     @Autowired private UserService userService;
     private final String GET_USER = "/";
     private final String VALIDATE_USER_EMAIL = "validte/user-email";
     private final String VALIDATE_USER_TEL_NUMBER = "validte/user-tel-number";

     @ApiOperation(value = "유저 정보 불러오기", notes = "Request Header Authorization에 Bearer Token을 포함하여 요청을 하면, 성공시 유저 정보를 반환하고 실패시 실패 메세지를 반환")
     @GetMapping(GET_USER)
     public ResponseDto<GetUserResponseDto> getUser(@AuthenticationPrincipal String userEmail) {
        ResponseDto<GetUserResponseDto> response = userService.getUser(userEmail);
        return response;
     }

     @ApiOperation(value = "유저 이메일 중복 체크")
     @GetMapping(VALIDATE_USER_EMAIL)
     public ResponseDto<ValidateEmailResponseDto> validateEmail(@Valid @RequestBody ValidateUserEmailDto requsetBody
     ) {
        ResponseDto<ValidateEmailResponseDto> response = userService.validateEmail(requsetBody);
        return response;
     };

     @ApiOperation(value = "유저 전화번호 중복 체크")
     @GetMapping(VALIDATE_USER_TEL_NUMBER)
     public ResponseDto<ValidateTelNumberResponseDto> validateTelNumber(@Valid @RequestBody ValidateUserTelNumberDto requsetBody
     ) {
        ResponseDto<ValidateTelNumberResponseDto> response = userService.validateTelNumber(requsetBody);
        return response;
     };

}