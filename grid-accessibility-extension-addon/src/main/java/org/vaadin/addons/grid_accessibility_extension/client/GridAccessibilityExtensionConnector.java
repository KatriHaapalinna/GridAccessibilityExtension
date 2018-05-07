package org.vaadin.addons.grid_accessibility_extension.client;

import org.vaadin.addons.grid_accessibility_extension.GridAccessibilityExtension;

import com.google.gwt.dom.client.Element;
import com.vaadin.client.ServerConnector;
import com.vaadin.client.annotations.OnStateChange;
import com.vaadin.client.connectors.grid.GridConnector;
import com.vaadin.client.extensions.AbstractExtensionConnector;
import com.vaadin.client.widgets.Grid;
import com.vaadin.shared.ui.Connect;

import elemental.json.JsonObject;

@Connect(GridAccessibilityExtension.class)
public class GridAccessibilityExtensionConnector
extends AbstractExtensionConnector {
    private Grid<JsonObject> gridWidget;
    private String[] headerIds = {};

    @Override
    public GridAccessibilityExtensionState getState() {
        return (GridAccessibilityExtensionState) super.getState();
    }

    // Whenever the state changes in the server-side, this method is called
    @OnStateChange("headerIds")
    void updateHeaders() {
        headerIds = getState().headerIds;
        onAttachOrChange();
    }

    @Override
    protected void extend(ServerConnector target) {
        gridWidget = getParent().getWidget();
        gridWidget.addAttachHandler(event -> {
            if (event.isAttached()) {
                onAttachOrChange();
            }

        });
    }

    @Override
    public GridConnector getParent() {
        return (GridConnector) super.getParent();
    }

    private void onAttachOrChange() {
        if (gridWidget != null && headerIds != null) {
            Element grid = gridWidget.getElement();
            Element header = grid.getElementsByTagName("THEAD").getItem(0);
            if (header != null) {
                setMutationHandler(header, headerIds);
            }
        }
    }

    private native void setMutationHandler(Element header, String[] headerIds)
    /*-{
        var indHeaders = header.getElementsByTagName('TH');
        for (i in indHeaders) {
            if (headerIds[i] && headerIds[i] !== '') {
                indHeaders[i].id = headerIds[i];
            }
        }
        var config = { attributes: false, childList: true };
        var callback = function(mutationsList) {
            for (mutation in mutationsList) {
                if (mutationsList[mutation].type == 'childList') {
                    var newHeaders = header.getElementsByTagName('TH');
                    for (i in newHeaders) {
                        if (headerIds[i] && headerIds[i] !== '') {
                            indHeaders[i].id = headerIds[i];
                        }
                    }
                }
            };
        };
        var observer = new MutationObserver(callback);
        observer.observe(header, config);
    }-*/;

    private native String getHeaderId(Element elem)
    /*-{
        return elem.id;
    }-*/;
}
