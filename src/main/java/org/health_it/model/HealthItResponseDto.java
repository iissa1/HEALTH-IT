package org.health_it.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class HealthItResponseDto {

    private String region;
    private String region_code;
    private String period;
    private double pct_md_pa_np_mu_aiu;
    private double pct_md_pa_np_mu;
    private double pct_md_mu_aiu;
    private double pct_md_mu;
    private double pct_np_mu_aiu;
    private double pct_np_mu;
    private double pct_pa_mu_aiu;
    private double pct_pa_mu;
    private double pct_hospitals_mu_aiu;
    private double pct_hospitals_mu;
    private double pct_cah_small_rural_mu_aiu;
    private double pct_cah_small_rural_mu;

}
