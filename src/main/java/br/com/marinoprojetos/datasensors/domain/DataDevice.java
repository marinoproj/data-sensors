package br.com.marinoprojetos.datasensors.domain;

import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDataDTO;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_dado_dispositivo", schema = "dados")
public class DataDevice {

    @Id
    @Column(name = "id_dado_dispositivo")
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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_dispositivo")
    private Device device;

    @Column(name = "tamanho_memoria")
    private Long memorySize;

    @Column(name = "tipo_memoria")
    private String memoryType;

    @Column(name = "endereco_memoria")
    private Long memoryAddress;

    @Column(name = "tipo_formato_valor")
    private String valueFormatType;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_valor_corrente", referencedColumnName = "id_valor_dado")
    private ValueData currentValueData;

    @Column(name = "exibir")
    private Boolean display;

    @Column(name = "somente_leitura")
    private boolean readOnly;

    @Column(name = "modo_leitura_gravacao")
    private long readWriteMode;

    @Column(name = "dh_exclusao")
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    private LocalDateTime dhExclude;

    public static DataDevice toDTO(ResponseHitecnologiaDataDTO dto){
        DataDevice dataDevice = new DataDevice();
        dataDevice.setExternalId(dto.getId());
        dataDevice.setExternalName(dto.getName());
        dataDevice.setEnabled(dto.isEnabled());
        dataDevice.setMemorySize(dto.getMemorySize());
        dataDevice.setMemoryType(dto.getMemoryType().getName());
        dataDevice.setMemoryAddress(dto.getMemoryAddress());
        dataDevice.setValueFormatType(dto.getValueFormatType().getName());
        dataDevice.setReadOnly(dto.isReadOnly());
        dataDevice.setReadWriteMode(dto.getReadWriteMode());
        return dataDevice;
    }

}