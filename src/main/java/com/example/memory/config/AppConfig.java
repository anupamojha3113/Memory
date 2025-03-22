package com.example.memory.config;

import com.example.memory.proto.api.UserProfileResponseDTO;
import com.example.memory.utils.ProtoMessageConverter;
import com.google.protobuf.util.JsonFormat;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import javax.sql.DataSource;

@Configuration
public class AppConfig {
    @Bean
    public DataSource createDataSource(
            @Value("${db.connection-url}") String connectionUrl,
            @Value("${db.username}") String username,
            @Value("${db.password}") String password,
            @Value("${db.pool-size:10}") int poolSize
    ) {
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl(connectionUrl);
        config.setUsername(username);
        config.setPassword(password);
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setMaximumPoolSize(poolSize);
        config.setAutoCommit(true);
        return new HikariDataSource(config);
    }
    @Bean
    ProtoMessageConverter getProtoMessageConverter() {
        JsonFormat.TypeRegistry typeRegistry = JsonFormat.TypeRegistry.newBuilder()
                .add(UserProfileResponseDTO.getDescriptor())
                .build();
        return new ProtoMessageConverter(typeRegistry);
    }
    @Bean
    @Primary
    ProtobufHttpMessageConverter protobufHttpMessageConverter(ProtoMessageConverter protoMessageConverter) {
        return protoMessageConverter.getHttpConverter(true);
    }
}
