package br.com.marinoprojetos.datasensors.domain;

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
    private DataDevice dataDevice;

    @Column(name = "dh_criacao")
    private LocalDateTime dhCreate;

}