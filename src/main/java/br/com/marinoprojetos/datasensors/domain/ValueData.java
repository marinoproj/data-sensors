package br.com.marinoprojetos.datasensors.domain;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_valor_dado", schema = "dados")
public class ValueData {

    @Id
    @Column(name = "id_valor_dado")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_externo")
    private long externalId;

    @Column(name = "valor")
    private String value;

    @Column(name = "dh_leitura")
    private LocalDateTime dhReading;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dado_dispositivo")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private DataDevice dataDevice;

    @Column(name = "dh_criacao")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dhCreate;

}