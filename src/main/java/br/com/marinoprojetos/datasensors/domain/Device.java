package br.com.marinoprojetos.datasensors.domain;

import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDeviceDTO;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_dispositivo", schema = "dados")
public class Device {

    @Id
    @Column(name = "id_dispositivo")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_externo")
    private long externalId;

    @Column(name = "nome_externo")
    private String externalName;

    @Column(name = "label")
    private String label;

    @Column(name = "descricao")
    private String description;

    @Column(name = "ativo")
    private boolean enabled;

    @Column(name = "conectado")
    private boolean isConnected;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_conector")
    private Connector connector;

    @Column(name = "localizacao_latitude")
    private String latitudeLocation;

    @Column(name = "localizacao_longitude")
    private String longitudeLocation;

    @Column(name = "tempo_leitura")
    private Long readingTime;

    @Column(name = "exibir")
    private Boolean display;

    @Column(name = "iniciar_automaticamente")
    private Boolean startAutomatically;

    @Column(name = "dh_exclusao")
    private LocalDateTime dhExclude;

    public static Device toDTO(ResponseHitecnologiaDeviceDTO dto){
        Device device = new Device();
        device.setExternalId(dto.getId());
        device.setExternalName(dto.getName());
        device.setEnabled(dto.isEnabled());
        device.setConnected(dto.isConnected());
        return device;
    }

}