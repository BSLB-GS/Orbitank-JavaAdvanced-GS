package br.com.orbitank.service;

import br.com.orbitank.dto.OperationalUserDTO;
import br.com.orbitank.entity.OperationalUser;
import br.com.orbitank.repository.OperationalUserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OperationalUserService {

    private final OperationalUserRepository repository;

    public OperationalUserService(OperationalUserRepository repository) {
        this.repository = repository;
    }

    public List<OperationalUserDTO> findAll() {
        return repository.findAll()
                .stream()
                .map(user -> {
                    OperationalUserDTO dto = new OperationalUserDTO();
                    dto.setId(user.getId());
                    // Se o seu DTO tiver mais campos (ex: nome, email), preencha-os aqui:
                    // dto.setName(user.getName());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    public OperationalUserDTO findById(Long id) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));

        OperationalUserDTO dto = new OperationalUserDTO();
        dto.setId(user.getId());
        return dto;
    }


    public OperationalUserDTO create(OperationalUserDTO dto) {
        OperationalUser user = new OperationalUser();
        // user.setName(dto.getName());

        OperationalUser savedUser = repository.save(user);

        OperationalUserDTO resultDto = new OperationalUserDTO();
        resultDto.setId(savedUser.getId());
        return resultDto;
    }

    public OperationalUserDTO update(Long id, OperationalUserDTO dto) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));

        OperationalUser updatedUser = repository.save(user);

        OperationalUserDTO resultDto = new OperationalUserDTO();
        resultDto.setId(updatedUser.getId());
        return resultDto;
    }

    public void delete(Long id) {
        OperationalUser user = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Usuário operacional não encontrado com o ID: " + id));
        repository.delete(user);
    }
}