package br.com.orbitank.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RefuelOrderDTO {

    private Long id;

    private Double actualWaterTransferred;

    private Double actualH2Transferred;

    private Double actualO2Transferred;

    private String status;
}