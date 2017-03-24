package eu.smirnowld;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;

import java.io.IOException;

@Configuration
public class SoyMvcConfiguration {

    @Bean
    public SoyFileSet soyFileSet() {
        try {
            return SoyFileSet.builder().add(new FileSystemResourceLoader().getResource("classpath:static/hello.soy").getFile()).build();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    @Bean
    public SoyTofu soyTofu() {
        return soyFileSet().compileToTofu();
    }

    @Bean
    public SoyViewResolver soyViewResolver() {
        return new SoyViewResolver(soyTofu());
    }
}
