package eu.smirnowld;

import com.google.template.soy.tofu.SoyTofu;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;

import java.util.Locale;

public class SoyViewResolver implements ViewResolver {

    private final SoyTofu soyTofu;

    public SoyViewResolver(final SoyTofu soyTofu) {
        this.soyTofu = soyTofu;
    }

    @Override
    public View resolveViewName(final String viewName,
                                final Locale locale) throws Exception {
        return new SoyView(soyTofu.newRenderer(viewName));
    }
}
