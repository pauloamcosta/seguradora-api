package com.pauloamcosta.seguradoraapi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@Data
@Document(collection = "policy")
@Getter
@Setter
@EqualsAndHashCode
public class Policy implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    private String id;

    private Integer policyNumber;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate startTime;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    private String vehiclePlate;

    private Double value;

    @Transient
    private Boolean expired;

    @Transient
    private Long daysToExpire;

    public Policy(String id, Integer policyNumber, LocalDate startTime, LocalDate endTime, String vehiclePlate, Double value) {
        this.id = id;
        this.policyNumber = policyNumber;
        this.startTime = startTime;
        this.endTime = endTime;
        this.vehiclePlate = vehiclePlate;
        this.value = value;
    }

    public Boolean getExpired() {
        if (ChronoUnit.DAYS.between(LocalDate.now(), endTime) < 0) {
            return true;
        }
        return false;
    }

    public Long getDaysToExpire() {
        if (ChronoUnit.DAYS.between(LocalDate.now(), endTime) < 0) {
            return 0l;
        }
        return ChronoUnit.DAYS.between(LocalDate.now(), endTime);
    }
}
