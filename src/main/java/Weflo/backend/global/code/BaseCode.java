package Weflo.backend.global.code;

import Weflo.backend.global.dto.ReasonDto;

public interface BaseCode {
    public ReasonDto getReason();

    public ReasonDto getReasonHttpStatus();
}
