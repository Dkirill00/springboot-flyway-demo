package ru.javamentor.springbootflywaydemo.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration //необходимо явно включить, добавив аннотацию @ConfigurationPropertiesScan
@ConfigurationProperties(prefix = "mail")
public class MailConfigProperties {
    String from;
    String to;
    String password;
    String host;
    String smtpPort;
    String subject;
    String text;
    String mailSmtpHost;
    String mailSmtpPort;
    String mailSmtpSsl;
    String mailSmtpAuth;
}
