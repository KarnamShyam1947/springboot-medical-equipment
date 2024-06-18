package com.shyam.services;

import java.util.List;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.shyam.dto.MedicineDTO;
import com.shyam.entities.MedicineEntity;
import com.shyam.repositories.MedicineRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MedicineService {
    
    private final MedicineRepository medicineRepository;
    private final CloudinaryService cloudinaryService;
    private final ModelMapper mapper;

    @SuppressWarnings("rawtypes")
    public MedicineEntity saveMedicine(MedicineDTO medicineDTO) {
        MedicineEntity medicine = mapper.map(medicineDTO, MedicineEntity.class);
        medicine.setId(0);

        Map res = cloudinaryService.uploadToCloud(medicineDTO.getFile(), "medicines");
        medicine.setImageUrl((String) (res.get("secure_url")));
        medicine.setPublicId((String) (res.get("public_id")));

        return medicineRepository.save(medicine);
    }

    public List<MedicineEntity> getAllMedicines() {
        return medicineRepository.findAll();
    }
   
    public MedicineEntity getMedicine(int id) {
        return medicineRepository.findById(id);
    }

    public MedicineEntity getMedicine(String name) {
        return medicineRepository.findByName(name);
    }

}
