package com.ssafy.health.domain.crew.entity;

import com.ssafy.health.common.entity.BaseEntity;
import com.ssafy.health.domain.crew.dto.request.CrewRegisterDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Crew extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private Integer memberLimit;

    private String profileImage;
    private Float averageAge;
    private String description;
    private Integer crewCoin;

    @Builder
    public Crew(CrewRegisterDto crewRegisterDto) {
        this.name = crewRegisterDto.getName();
        this.profileImage = crewRegisterDto.getProfileImage();
        this.averageAge = crewRegisterDto.getAverageAge();
        this.description = crewRegisterDto.getDescription();
        this.memberLimit = 10;
        this.crewCoin = 0;
    }
}