package br.com.marinoprojetos.datasensors.service.utils;

import br.com.marinoprojetos.datasensors.domain.Connector;
import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.dto.request.RequestPostDeviceDTO;
import br.com.marinoprojetos.datasensors.dto.request.RequestPutDeviceDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaConnectorDTO;
import br.com.marinoprojetos.datasensors.dto.response.ResponseHitecnologiaDeviceDTO;
import br.com.marinoprojetos.datasensors.exception.GeneralInvalidException;
import br.com.marinoprojetos.datasensors.service.ConnectorService;
import br.com.marinoprojetos.datasensors.service.DeviceService;
import br.com.marinoprojetos.datasensors.service.hitecnologia.HitecnologiaDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceUtilsService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ConnectorService connectorService;

    @Autowired
    private HitecnologiaDeviceService hitecnologiaDeviceService;

    public Device addDevice(RequestPostDeviceDTO request) {

        Optional<Device> deviceOptional = deviceService.getDeviceByExternalId(request.getExternalId());

        if (deviceOptional.isPresent()){
            // dispositivo ja esta cadastrado
            throw new GeneralInvalidException("error.add.device", "Dispositivo já cadastrado");
        }

        Optional<ResponseHitecnologiaDeviceDTO> deviceExternalOptional = hitecnologiaDeviceService.getDeviceById(request.getExternalId());

        if (!deviceExternalOptional.isPresent()){
            // dispositivo não encontrado
            throw new GeneralInvalidException("error.add.device", "Dispositivo não encontrado");
        }

        ResponseHitecnologiaDeviceDTO deviceExternal = deviceExternalOptional.get();

        Optional<Connector> connectorOptional = connectorService.getConnectorByExternalId(deviceExternal.getConnector().getId());

        Connector connector;

        if (connectorOptional.isPresent()){
            connector = connectorOptional.get();
        } else {
            ResponseHitecnologiaConnectorDTO connectorExternal = deviceExternal.getConnector();
            connector = connectorService.save(Connector.toDTO(connectorExternal));
        }

        Device deviceNew = Device.toDTO(deviceExternal);
        deviceNew.setConnector(connector);

        return deviceService.save(deviceNew);

    }

    public void deleteById(Long deviceId){

        Optional<Device> deviceOptional = deviceService.getById(deviceId);

        if (!deviceOptional.isPresent()){
            // dispositivo não existe
            throw new GeneralInvalidException("error.delete.device", "Dispositivo não encontrado");
        }

        deviceService.delete(deviceId);

    }

    public List<Device> findDevices(Long id){

        List<Device> devices = new ArrayList<>();

        if (id == null){
            devices = deviceService.getDevices();
        } else {
            Optional<Device> deviceOptional = deviceService.getById(id);
            if (deviceOptional.isPresent()){
                devices.add(deviceOptional.get());
            }
        }

        return devices;

    }

    public Device update(RequestPutDeviceDTO request){

        Optional<Device> deviceOptional = deviceService.getById(request.getId());

        if (!deviceOptional.isPresent()){
            throw new GeneralInvalidException("error.update.device", "Dispositivo não encontrado");
        }

        Device device = deviceOptional.get();
        device.setLabel(request.getLabel());
        device.setDescription(request.getDescription());
        device.setLatitudeLocation(request.getLatitudeLocation());
        device.setLongitudeLocation(request.getLongitudeLocation());
        device.setReadingTime(request.getReadingTime());
        device.setDisplay(request.getDisplay());
        device.setStartAutomatically(request.getStartAutomatically());

        return deviceService.save(device);

    }

    public Device updateReadingTime(long deviceId, long readingTime){

        Optional<Device> deviceOptional = deviceService.getById(deviceId);

        if (!deviceOptional.isPresent()){
            throw new GeneralInvalidException("error.update.device", "Dispositivo não encontrado");
        }

        Device device = deviceOptional.get();
        device.setReadingTime(readingTime);

        return deviceService.save(device);

    }

}