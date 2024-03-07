package Weflo.backend.service.insurance;

import Weflo.backend.domain.User;
import Weflo.backend.dto.user.response.InsuranceResponse;
import Weflo.backend.repository.user.UserRepository;
import com.amazonaws.services.kms.model.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
