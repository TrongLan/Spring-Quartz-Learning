package com.example.springquartztraining.configuration;

import lombok.RequiredArgsConstructor;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class ModelMapperConfig {
  @Bean
  public ModelMapper getModelMapper() {
    ModelMapper mapper = new ModelMapper();
    mapper
        .getConfiguration()
        .setPropertyCondition(Conditions.isNotNull())
        .setFieldMatchingEnabled(true)
        .setFieldAccessLevel(org.modelmapper.config.Configuration.AccessLevel.PRIVATE);
    return mapper;
  }
}
