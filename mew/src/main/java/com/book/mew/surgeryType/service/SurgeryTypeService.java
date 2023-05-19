package com.book.mew.surgeryType.service;

import com.book.mew.surgeryType.dto.SurgeryTypeResponse;
import com.book.mew.surgeryType.entity.SurgeryType;
import com.book.mew.surgeryType.repository.SurgeryTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SurgeryTypeService {
    private final SurgeryTypeRepository stRepo;

    // surgeryTypes정보 조회
    public ArrayList<SurgeryTypeResponse> getAllSurgeryTypes() {
        List<SurgeryType> surgeryTypes = stRepo.findAll();
        ArrayList<SurgeryTypeResponse> surgeryTypeResponses = new ArrayList<>();

        for(SurgeryType surgeryType : surgeryTypes) {
            SurgeryTypeResponse surgeryTypeResponse = SurgeryTypeResponse.builder()
                    .id(surgeryType.getId())
                    .detail(surgeryType.getDetail())
                    .surgeryType(surgeryType.getSurgeryType())
                    .imgUrl(surgeryType.getImgUrl())
                    .build();
            surgeryTypeResponses.add(surgeryTypeResponse);

        }

        return surgeryTypeResponses;

    }
}
