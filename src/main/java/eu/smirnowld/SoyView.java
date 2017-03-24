package eu.smirnowld;

import com.google.template.soy.data.SanitizedContent;
import com.google.template.soy.tofu.SoyTofu;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class SoyView implements View {

    private final SoyTofu.Renderer renderer;

    public SoyView(final SoyTofu.Renderer renderer) {
        this.renderer = renderer;
    }

    @Override
    public String getContentType() {
        return "text/html";
    }

    @Override
    public void render(final Map<String, ?> model,
                       final HttpServletRequest httpServletRequest,
                       final HttpServletResponse httpServletResponse) throws Exception {
        httpServletResponse.getWriter().write(renderer.setContentKind(SanitizedContent.ContentKind.HTML).setData(model).render());
    }
}
