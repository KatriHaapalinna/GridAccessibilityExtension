package org.vaadin.addons.grid_accessibility_extension.client;

import org.vaadin.addons.grid_accessibility_extension.CellAccessibilityRenderer;

import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.connectors.grid.AbstractGridRendererConnector;
import com.vaadin.shared.ui.Connect;

@Connect(CellAccessibilityRenderer.class)
public class CellAccessibilityRendererConnector
        extends AbstractGridRendererConnector<String> {
    private static final long serialVersionUID = -66300138811340773L;

    public CellAccessibilityRendererConnector() {
    }

    @Override
    public CellAccessibilityRendererWidget getRenderer() {
        return (CellAccessibilityRendererWidget) super.getRenderer();
    }

    @Override
    public CellAccessibilityRendererState getState() {
        return (CellAccessibilityRendererState) super.getState();
    }

    @OnStateChange("headers")
    void updateCellHeaders() {
        getRenderer().updateHeaders(getState().headers);
    }

    @OnStateChange("isHtml")
    void updateIsHtml() {
        getRenderer().setIsHtml(getState().isHtml);
    }
}
