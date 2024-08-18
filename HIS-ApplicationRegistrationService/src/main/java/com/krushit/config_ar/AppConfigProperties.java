package com.krushit.config_ar;

import java.util.Map;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Data
@ConfigurationProperties(prefix = "registration-mgmt")
public class AppConfigProperties {
	private Map<String, String> messages;
}
