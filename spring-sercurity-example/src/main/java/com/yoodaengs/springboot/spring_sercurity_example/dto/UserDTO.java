package com.yoodaengs.springboot.spring_sercurity_example.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@RequiredArgsConstructor
@Data
public class UserDTO {
    private Long id;
    private String name;
    private String email;
    private String birth;
}
