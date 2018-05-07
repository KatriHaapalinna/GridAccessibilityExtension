package org.vaadin.addons.grid_accessibility_extension;

/**
 * Configuration wrapper class for {@link CellAccessibilityRenderer}
 */
public class CellRendererConfiguration {
    private String[] headers;
    private boolean isHtml;

    /**
     *
     * @param headers
     *            headers to add to the headers attribute
     * @param isHtml
     *            is cell content HTML
     */
    public CellRendererConfiguration(String[] headers,
            boolean isHtml) {
        this.headers = headers;
        this.isHtml = isHtml;
    }

    public String[] getHeaders() {
        return headers;
    }

    public void setHeaders(String[] headers) {
        this.headers = headers;
    }

    public boolean isHtml() {
        return isHtml;
    }

    public void setHtml(boolean isHtml) {
        this.isHtml = isHtml;
    }
}
