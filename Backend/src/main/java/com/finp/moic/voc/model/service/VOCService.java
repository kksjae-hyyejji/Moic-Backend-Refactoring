package com.finp.moic.voc.model.service;

import com.finp.moic.voc.model.dto.request.VOCRequestDTO;

public interface VOCService {
    void sendVOC(String id, VOCRequestDTO dto);
}
