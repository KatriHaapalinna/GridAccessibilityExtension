package org.vaadin.addons.grid_accessibility_extension.client;

import java.util.Objects;

import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.vaadin.client.renderers.Renderer;
import com.vaadin.client.widget.grid.RendererCellReference;

// Extend any GWT Widget
public class CellAccessibilityRendererWidget implements Renderer<String> {
    private RendererCellReference cell;
    private String data;
    private String headers;
    private boolean isHtml;
    private boolean refresh = true;

    public CellAccessibilityRendererWidget() {
        this("");
    }

    public CellAccessibilityRendererWidget(String headers) {
        this.headers = headers;
        if (cell != null) {
            render(cell, data);
        }
    }

    @Override
    public void render(RendererCellReference cell, String dataObject) {
        this.cell = cell;
        data = Objects.toString(dataObject, "");
        if (isHtml) {
            cell.getElement()
                    .setInnerSafeHtml(SafeHtmlUtils.fromSafeConstant(data));
        } else {
            cell.getElement().setInnerText(data);
        }

        if (headers != null) {
            setHeadersToElement(cell.getElement(), headers);
        }
    }

    public void setIsHtml(boolean isHtml) {
        this.isHtml = isHtml;
        if (cell != null) {
            render(cell, data);
        }
    }

    public void updateHeaders(String headers) {
        this.headers = headers;
        if (cell != null) {
            render(cell, data);
        }
    }

    private native void setHeadersToElement(Element elem, String text)
    /*-{
        elem.setAttribute("headers", text);
    }-*/;
}