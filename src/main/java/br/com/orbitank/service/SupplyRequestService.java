package br.com.orbitank.service;

import br.com.orbitank.entity.OperationalAlert;
import br.com.orbitank.entity.ResourceTank;
import br.com.orbitank.entity.SupplyRequest;
import br.com.orbitank.enums.AlertSeverity;
import br.com.orbitank.enums.ResourceType;
import br.com.orbitank.enums.SupplyRequestStatus;
import br.com.orbitank.repository.OperationalAlertRepository;
import br.com.orbitank.repository.ResourceTankRepository;
import br.com.orbitank.repository.SupplyRequestRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor

public class SupplyRequestService {

    private final SupplyRequestRepository supplyRequestRepository;

    private final ResourceTankRepository resourceTankRepository;

    private final OperationalAlertRepository operationalAlertRepository;

    public SupplyRequest analyzeRequest(Long requestId) {

        SupplyRequest request = supplyRequestRepository
                .findById(requestId)
                .orElseThrow();

        boolean hasCriticalAlert =
                operationalAlertRepository
                        .findAll()
                        .stream()
                        .anyMatch(alert ->
                                alert.getActive()
                                        &&
                                        alert.getSeverity()
                                                == AlertSeverity.CRITICAL
                        );

        if (hasCriticalAlert) {

            request.setStatus(
                    SupplyRequestStatus.DENIED
            );

            request.setDenialReason(
                    "Operação negada devido a alerta crítico ativo"
            );

            return supplyRequestRepository.save(request);
        }

        double availableWater =
                getAvailableVolume(ResourceType.LIQUID_WATER);

        double availableH2 =
                getAvailableVolume(ResourceType.HYDROGEN);

        double availableO2 =
                getAvailableVolume(ResourceType.OXYGEN);

        boolean enoughResources =
                availableWater >= request.getRequestedWaterVolume()
                        &&
                        availableH2 >= request.getRequestedH2Volume()
                        &&
                        availableO2 >= request.getRequestedO2Volume();

        if (!enoughResources) {

            request.setStatus(
                    SupplyRequestStatus.WAITING_FOR_RESOURCES
            );

            request.setDenialReason(
                    "Recursos insuficientes"
            );

            return supplyRequestRepository.save(request);
        }

        request.setStatus(
                SupplyRequestStatus.APPROVED
        );

        request.setDenialReason(null);

        return supplyRequestRepository.save(request);
    }

    private double getAvailableVolume(
            ResourceType resourceType
    ) {

        List<ResourceTank> tanks =
                resourceTankRepository.findAll();

        return tanks.stream()
                .filter(tank ->
                        tank.getResourceType()
                                == resourceType
                )
                .mapToDouble(ResourceTank::getCurrentVolume)
                .sum();
    }
}