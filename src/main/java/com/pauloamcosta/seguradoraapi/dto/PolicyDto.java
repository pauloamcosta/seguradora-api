package com.pauloamcosta.seguradoraapi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.pauloamcosta.seguradoraapi.model.Policy;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
@EqualsAndHashCode
public class PolicyDto implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    @NotNull(message = "Field policyNumber must be filled")
    private Integer policyNumber;

    @NotNull(message = "Field startTime must be filled")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @NotNull(message = "Field endTime must be filled")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    @NotBlank(message = "Field vehiclePlate must be filled")
    private String vehiclePlate;

    @NotNull(message = "Field value must be filled")
    private Double value;

    public PolicyDto(Policy policy) {
        this.id = policy.getId();
        this.policyNumber = policy.getPolicyNumber();
        this.startTime = policy.getStartTime();
        this.endTime = policy.getEndTime();
        this.vehiclePlate = policy.getVehiclePlate();
        this.value = policy.getValue();
    }

    public PolicyDto() {
    }
}
