package br.com.orbitank.service;

import br.com.orbitank.dto.Request.RefuelOrderRequest;
import br.com.orbitank.dto.Response.RefuelOrderResponse;
import br.com.orbitank.entity.RefuelOrder;
import br.com.orbitank.repository.RefuelOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RefuelOrderService {

    private final RefuelOrderRepository repository;

    public List<RefuelOrderResponse> findAll() {
        return repository.findAll()
                .stream()
                .map(this::toResponse)
                .toList();
    }

    public RefuelOrderResponse findById(Long id) {
        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        return toResponse(entity);
    }

    public RefuelOrderResponse create(RefuelOrderRequest request) {
        return toResponse(repository.save(toEntity(request)));
    }

    public RefuelOrderResponse update(Long id, RefuelOrderRequest request) {
        RefuelOrder entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Ordem não encontrada"));

        entity.setSupplyRequest(request.getSupplyRequest());
        entity.setStartDate(request.getStartDate());
        entity.setEndDate(request.getEndDate());
        entity.setActualWaterTransferred(request.getActualWaterTransferred());
        entity.setActualH2Transferred(request.getActualH2Transferred());
        entity.setActualO2Transferred(request.getActualO2Transferred());
        entity.setStatus(request.getStatus());
        entity.setTankStatus(request.getTankStatus());

        return toResponse(repository.save(entity));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    private RefuelOrderResponse toResponse(RefuelOrder entity) {
        return RefuelOrderResponse.builder()
                .id(entity.getId())
                .supplyRequest(entity.getSupplyRequest())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .actualWaterTransferred(entity.getActualWaterTransferred())
                .actualH2Transferred(entity.getActualH2Transferred())
                .actualO2Transferred(entity.getActualO2Transferred())
                .status(entity.getStatus())
                .tankStatus(entity.getTankStatus())
                .build();
    }

    private RefuelOrder toEntity(RefuelOrderRequest request) {
        return RefuelOrder.builder()
                .supplyRequest(request.getSupplyRequest())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .actualWaterTransferred(request.getActualWaterTransferred())
                .actualH2Transferred(request.getActualH2Transferred())
                .actualO2Transferred(request.getActualO2Transferred())
                .status(request.getStatus())
                .tankStatus(request.getTankStatus())
                .build();
    }
}