package com.kanue.interviews.santander.accountrestapi.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Entity
@Table(name = "ACCOUNT")
@Builder
@Getter
@Setter
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "sortCode")
    @JsonProperty("sortCode")
    private String sortCode;

    public Account(String name, String sortCode) {
        this.name = name;
        this.sortCode = sortCode;
    }

    public Account(Long id, String name, String sortCode) {
        this.id = id;
        this.name = name;
        this.sortCode = sortCode;
    }

    public Account() {
    }
}
