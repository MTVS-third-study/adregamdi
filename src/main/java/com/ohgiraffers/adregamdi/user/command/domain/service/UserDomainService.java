package com.ohgiraffers.adregamdi.user.command.domain.service;

import com.ohgiraffers.adregamdi.common.annotation.DomainService;
import com.ohgiraffers.adregamdi.user.command.application.dto.UserDTO;

@DomainService
public interface UserDomainService {

    // 카카오 아이디로 유저 정보 조회
    UserDTO findByKakaoId(String kakaoId);
}
