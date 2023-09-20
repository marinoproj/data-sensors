package br.com.marinoprojetos.datasensors.monitoring;

import br.com.marinoprojetos.datasensors.domain.Device;
import br.com.marinoprojetos.datasensors.enums.StatusMonitoringDeviceEnum;
import br.com.marinoprojetos.datasensors.service.DeviceService;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
public class DeviceMonitoringService {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private BeanFactory beanFactory;

    private List<DeviceMonitoringJob> deviceMonitoringJobList;

    @PostConstruct
    private void init(){

        deviceMonitoringJobList = new ArrayList<>();

        List<Device> deviceList = deviceService.getDevices();

        deviceList.forEach(device -> {

            if (device.getStartAutomatically() != null && device.getStartAutomatically()){
                startMonitoringDevice(device);
            }

        });

    }

    public StatusMonitoringDeviceEnum statusMonitoringDevice(Device device){

        boolean started = deviceMonitoringJobList.stream().anyMatch(obj -> obj.getDevice().getId().equals(device.getId()));

        if (started){
            return StatusMonitoringDeviceEnum.INICIADO;
        }

        return StatusMonitoringDeviceEnum.NAO_INICIADO;

    }

    public void startMonitoringDevice(Device device){

        StatusMonitoringDeviceEnum status = statusMonitoringDevice(device);

        if (status == StatusMonitoringDeviceEnum.NAO_INICIADO) {

            DeviceMonitoringJob deviceMonitoringJob = new DeviceMonitoringJob(device, beanFactory);
            deviceMonitoringJob.start();

            deviceMonitoringJobList.add(deviceMonitoringJob);

        }

    }

    public void stopMonitoringDevice(Device device){

        StatusMonitoringDeviceEnum status = statusMonitoringDevice(device);

        if (status == StatusMonitoringDeviceEnum.INICIADO) {

            Optional<DeviceMonitoringJob> deviceMonitoringJobOptional = deviceMonitoringJobList.stream().filter(obj -> obj.getDevice().getId().equals(device.getId())).findFirst();

            if (deviceMonitoringJobOptional.isPresent()){

                DeviceMonitoringJob deviceMonitoringJob = deviceMonitoringJobOptional.get();
                stopMonitoringDevice(deviceMonitoringJob);
                deviceMonitoringJobList.remove(deviceMonitoringJob);

            }

        }

    }

    public void stopAllMonitoringDevice(){

        Iterator<DeviceMonitoringJob> iterator = deviceMonitoringJobList.iterator();

        while(iterator.hasNext()){

            DeviceMonitoringJob deviceMonitoringJob = iterator.next();
            stopMonitoringDevice(deviceMonitoringJob);
            iterator.remove();

        }

    }

    private void stopMonitoringDevice(DeviceMonitoringJob deviceMonitoringJob){

        deviceMonitoringJob.close();

        try {
            deviceMonitoringJob.interrupt();
        } catch (Exception ignored) {}

        try {
            deviceMonitoringJob.join();
        } catch (Exception ignored) {}

    }

}