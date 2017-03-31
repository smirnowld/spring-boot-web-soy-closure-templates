package eu.smirnowld;

import com.google.template.soy.SoyFileSet;
import com.google.template.soy.tofu.SoyTofu;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResourceLoader;

import java.io.File;
import java.io.IOException;

@Configuration
public class SoyMvcConfiguration {

    @Bean
    public SoyFileSet soyFileSet() {
        try {
            final File soyTemplatesLocation = new FileSystemResourceLoader().getResource("classpath:/static/soy/").getFile();
            final SoyFileSet.Builder soyFileSetBuilder = SoyFileSet.builder();
            populateBuilderWithSoyTemplates(soyFileSetBuilder, soyTemplatesLocation);
            return soyFileSetBuilder.build();
        } catch (final IOException e) {
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

    private void populateBuilderWithSoyTemplates(final SoyFileSet.Builder soyFiles,
                                                 final File directory) {
        final File[] files = directory.listFiles();
        if (files != null) {
            for (final File file : files) {
                if (file.isFile()) {
                    if (file.getName().endsWith(".soy")) {
                        soyFiles.add(file);
                    }
                } else if (file.isDirectory()) {
                    populateBuilderWithSoyTemplates(soyFiles, file);
                }
            }
        } else {
            throw new IllegalArgumentException(
                    "Failed to populate SoyFileSet.Builder with soy templates from directory [" + directory + "]. Check that it is" + " indeed a directory " +
                            "and not empty!");
        }
    }
}
