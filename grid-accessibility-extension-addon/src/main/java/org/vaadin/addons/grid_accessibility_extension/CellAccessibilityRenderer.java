package org.vaadin.addons.grid_accessibility_extension;

import org.vaadin.addons.grid_accessibility_extension.client.CellAccessibilityRendererState;

import com.vaadin.ui.renderers.AbstractRenderer;

/**
 * {@link com.vaadin.ui.renderers.AbstractRenderer} extension for adding headers
 * accessibility attribute to the cells
 */
public class CellAccessibilityRenderer
        extends AbstractRenderer<Object, Object> {

    /**
     *
     * @param config
     *            Renderer configuration
     */
    public CellAccessibilityRenderer(CellRendererConfiguration config) {
        super(Object.class, "");
        getState().isHtml = config.isHtml();
        if (config.getHeaders() != null) {
            getState().headers = String.join(" ", config.getHeaders());
        } else {
            getState().headers = "";
        }

    }

    private static final long serialVersionUID = 1L;

    @Override
    public CellAccessibilityRendererState getState() {
        return (CellAccessibilityRendererState) super.getState();
    }

}
