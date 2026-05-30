package br.com.orbitank.dto.Response;

import br.com.orbitank.entity.OperationalUser;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuditLogResponse {
    private Long id;
    private OperationalUser user;
    private String action;
    private String description;
    private LocalDateTime createdAt;
}