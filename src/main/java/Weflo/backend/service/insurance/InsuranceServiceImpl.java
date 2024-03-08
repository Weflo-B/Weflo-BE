package Weflo.backend.service.insurance;

import Weflo.backend.domain.User;
import Weflo.backend.dto.user.response.InsuranceResponse;
import Weflo.backend.repository.user.UserRepository;
import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
public class InsuranceServiceImpl implements InsuranceService {
    private final UserRepository userRepository;

    @Override
    public InsuranceResponse getInsuranceDetails(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        return InsuranceResponse.builder()
                .isJoin(user.getIsJoin())
                .joinDate(user.getJoinDate())
                .updateDate(user.getUpdateDate())
                .insuranceRate(user.getInsuranceRate())
                .build();
    }

    @Override
    public InsuranceResponse joinInsurance(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));

        LocalDate now = LocalDate.now();

        User newUserInfo = User.builder()
                .id(user.getId())
                .loginId(user.getLoginId())
                .loginPassword(user.getLoginPassword())
                .name(user.getName())
                .tel(user.getTel())
                .address(user.getAddress())
                .isJoin(true)
                .joinDate(now)
                .updateDate(now.plusYears(3))
                .insuranceRate(10)
                .build();

        userRepository.save(newUserInfo);

        return InsuranceResponse.builder()
                .isJoin(newUserInfo.getIsJoin())
                .joinDate(newUserInfo.getJoinDate())
                .updateDate(newUserInfo.getUpdateDate())
                .insuranceRate(newUserInfo.getInsuranceRate())
                .build();
    }

}
