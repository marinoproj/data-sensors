package br.com.marinoprojetos.datasensors.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableFeignClients(basePackages = "br.com.marinoprojetos.datasensors")
@Import(FeignClientsConfiguration.class)
public class FeignClientConfig {
}
