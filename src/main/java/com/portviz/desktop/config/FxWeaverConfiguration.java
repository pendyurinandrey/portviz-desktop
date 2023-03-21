package com.portviz.desktop.config;

import net.rgielen.fxweaver.core.FxLoadException;
import net.rgielen.fxweaver.core.FxWeaver;
import net.rgielen.fxweaver.core.FxmlView;
import net.rgielen.fxweaver.spring.SpringFxWeaver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.type.filter.AnnotationTypeFilter;

import java.util.Arrays;
import java.util.Optional;

@Configuration
public class FxWeaverConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(FxWeaverConfiguration.class);

    @Value("${fxweaver.autoscan.basepackages:#{null}}")
    private Optional<String[]> autoScanBasePackages;

    // The solution for https://github.com/rgielen/javafx-weaver/issues/17
    @Bean
    public FxWeaver fxWeaver(ConfigurableApplicationContext applicationContext) {
        var springFxWeaver = new SpringFxWeaver(applicationContext);
        if (this.autoScanBasePackages.isPresent()) {
            var scanner = new ClassPathScanningCandidateComponentProvider(false);
            scanner.addIncludeFilter(new AnnotationTypeFilter(FxmlView.class));
            Arrays.stream(this.autoScanBasePackages.get()).forEach(autoScanBasePackage -> {
                scanner.findCandidateComponents(autoScanBasePackage).forEach(beanDefinition -> {
                    try {
                        LOG.info("Initializing fxmlview annnotated bean '{}'...", beanDefinition.getBeanClassName());
                        springFxWeaver.load(Class.forName(beanDefinition.getBeanClassName()));
                    } catch (ClassNotFoundException cnfe) {
                        throw new FxLoadException(String.format("Class '%s' not found!", beanDefinition.getBeanClassName()), cnfe);
                    }
                });
            });
        } else {
            LOG.info("Config property 'fxweaver.autoscan.basepackages' not set, skipping auto fxmlview annotated bean registration.");
        }
        return springFxWeaver;
    }
}
