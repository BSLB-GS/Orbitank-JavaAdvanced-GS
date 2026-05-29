package br.com.orbitank.service;

import br.com.orbitank.dto.RefuelOrderDTO;
import br.com.orbitank.entity.RefuelOrder;
import br.com.orbitank.repository.RefuelOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefuelOrderService {

    private final RefuelOrderRepository repository;

    public List<RefuelOrderDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public RefuelOrderDTO findById(Long id) {

        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        return toDTO(entity);
    }

    public RefuelOrderDTO create(RefuelOrderDTO dto) {

        return toDTO(repository.save(toEntity(dto)));
    }

    public RefuelOrderDTO update(Long id, RefuelOrderDTO dto) {

        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        entity.setActualWaterTransferred(dto.getActualWaterTransferred());

        entity.setActualH2Transferred(dto.getActualH2Transferred());

        entity.setActualO2Transferred(dto.getActualO2Transferred());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private RefuelOrderDTO toDTO(RefuelOrder entity) {

        return RefuelOrderDTO.builder()
                .id(entity.getId())
                .actualWaterTransferred(entity.getActualWaterTransferred())
                .actualH2Transferred(entity.getActualH2Transferred())
                .actualO2Transferred(entity.getActualO2Transferred())
                .status(entity.getStatus().name())
                .build();
    }

    private RefuelOrder toEntity(RefuelOrderDTO dto) {

        return RefuelOrder.builder()
                .id(dto.getId())
                .actualWaterTransferred(dto.getActualWaterTransferred())
                .actualH2Transferred(dto.getActualH2Transferred())
                .actualO2Transferred(dto.getActualO2Transferred())
                .build();
    }
}