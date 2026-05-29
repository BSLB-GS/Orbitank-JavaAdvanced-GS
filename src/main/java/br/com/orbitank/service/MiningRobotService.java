package br.com.orbitank.service;

import br.com.orbitank.dto.MiningRobotDTO;
import br.com.orbitank.entity.MiningRobot;
import br.com.orbitank.repository.MiningRobotRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MiningRobotService {

    private final MiningRobotRepository repository;

    public List<MiningRobotDTO> findAll() {

        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public MiningRobotDTO findById(Long id) {

        MiningRobot entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Robô não encontrado"));

        return toDTO(entity);
    }

    public MiningRobotDTO create(MiningRobotDTO dto) {

        return toDTO(repository.save(toEntity(dto)));
    }

    public MiningRobotDTO update(Long id, MiningRobotDTO dto) {

        MiningRobot entity = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Robô não encontrado"));

        entity.setIdentification(dto.getIdentification());
        entity.setCurrentVolume(dto.getCurrentVolume());
        entity.setCargoCapacity(dto.getCargoCapacity());
        entity.setCurrentIceCargo(dto.getCurrentIceCargo());
        entity.setBatteryLevel(dto.getBatteryLevel());

        return toDTO(repository.save(entity));
    }

    public void delete(Long id) {

        repository.deleteById(id);
    }

    private MiningRobotDTO toDTO(MiningRobot entity) {

        return MiningRobotDTO.builder()
                .id(entity.getId())
                .identification(entity.getIdentification())
                .currentVolume(entity.getCurrentVolume())
                .cargoCapacity(entity.getCargoCapacity())
                .currentIceCargo(entity.getCurrentIceCargo())
                .batteryLevel(entity.getBatteryLevel())
                .status(entity.getStatus().name())
                .build();
    }

    private MiningRobot toEntity(MiningRobotDTO dto) {

        return MiningRobot.builder()
                .id(dto.getId())
                .identification(dto.getIdentification())
                .currentVolume(dto.getCurrentVolume())
                .cargoCapacity(dto.getCargoCapacity())
                .currentIceCargo(dto.getCurrentIceCargo())
                .batteryLevel(dto.getBatteryLevel())
                .build();
    }
}