package co.istad.sokcheatmbankingapi.features.media;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ResourceHandlersConfig implements WebMvcConfigurer {
    @Value("${media.client-path}")
    private String clientPath;
    @Value("${media.server-path}")
    private String serverPath;
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry
                .addResourceHandler(clientPath)
                .addResourceLocations("file:" + serverPath);
    }
}
