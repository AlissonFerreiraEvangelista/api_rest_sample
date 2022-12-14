package br.com.springboot.bootstrap_spring.models;

import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name="tdendereco")
public class Endereco {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    UUID uuid;

    String cep;
    String rua;
    String bairro;
    String cidade;
    String uf;



}
