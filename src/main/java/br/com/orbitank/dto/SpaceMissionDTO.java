package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SpaceMissionDTO {

    private Long id;

    private String missionCode;

    private String missionName;

    private String clientCompanyName;

    private String externalCommercialRequestCode;

    private String destination;

    private LocalDateTime scheduledLaunchDate;

    private String status;

    private String priority;

    private Long createdByUserId;
}