package com.ohgiraffers.adregamdi.user.query.application.service;

import com.ohgiraffers.adregamdi.user.command.application.dto.UserDTO;
import com.ohgiraffers.adregamdi.user.command.domain.aggregate.entity.User;
import com.ohgiraffers.adregamdi.user.query.infrastructure.repository.UserFindInfraRepository;
import com.ohgiraffers.adregamdi.user.query.infrastructure.repository.UserQueryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserQueryService {
    private final UserFindInfraRepository userFindInfraRepository;
    private final UserQueryRepository userQueryRepository;

    @Autowired
    public UserQueryService(UserFindInfraRepository userFindInfraRepository, UserQueryRepository userQueryRepository) {
        this.userFindInfraRepository = userFindInfraRepository;
        this.userQueryRepository = userQueryRepository;
    }

    public UserDTO findById(String id) { // 현재 유저 조회
        User user;
        try {
            user = userQueryRepository.findById(id);
        } catch (NullPointerException ne) {
            ne.printStackTrace();
            return new UserDTO();
        }
        return new UserDTO(
                user.getUserNo(),
                user.getId(),
                user.getKakaoNickName(),
                user.getServiceNickName(),
                user.getEmail(),
                user.getAge(),
                user.getGender(),
                user.getReport_count(),
                user.getReview_count(),
                user.getGrade(),
                user.isBlacklist_status(),
                user.getAccess_Token(),
                user.getRefresh_Token());
    }
}



