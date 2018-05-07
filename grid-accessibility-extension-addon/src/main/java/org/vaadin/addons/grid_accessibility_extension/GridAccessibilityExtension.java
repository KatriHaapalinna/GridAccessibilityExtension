package org.vaadin.addons.grid_accessibility_extension;

import java.util.Map;

import org.vaadin.addons.grid_accessibility_extension.client.GridAccessibilityExtensionState;

import com.vaadin.ui.Grid;
import com.vaadin.ui.Grid.AbstractGridExtension;

import elemental.json.JsonObject;

/**
 *
 * Extension for {@link com.vaadin.ui.Grid} for adding id attribute to
 * HeaderCells (table headers) and headers attribute to Grid cells (table
 * cells).
 * <h3>Knwon limitations</h3> - cannot be used with column reordering enabled
 * (header id attribute disappears if columns are reordered)
 */
public class GridAccessibilityExtension<T> extends AbstractGridExtension<T> {
    private static final long serialVersionUID = 1L;

    @Override
    protected GridAccessibilityExtensionState getState() {
        return (GridAccessibilityExtensionState) super.getState();
    }

    /**
     * Extend {@code Grid} to add ids to HeaderCells and headers attribute to
     * cells. All headers ({@code
     *
    <th>}) in Grid {@code
     *

    <table>
     * } must be provided header ids due to the limitations in current Grid
     * {@code HeaderCell} implementation.
     *
     * @param grid
     *            {@code Grid} to extend
     * @param headers
     *            Map of {@code Column} ids to {@link CellRendererConfiguration}
     *            for {@code Column}
     * @param headerIds
     *            Array of all header ids, including empty ones in order of
     *            appearance in Grid.
     */
    public static void extend(Grid<?> grid,
            Map<String, CellRendererConfiguration> headers,
            String[] headerIds) {
        GridAccessibilityExtension<?> ex = new GridAccessibilityExtension<>();
        ex.getState().headerIds = headerIds;

        for (String columnId : headers.keySet()) {
            grid.getColumn(columnId).setRenderer(
                    new CellAccessibilityRenderer(headers.get(columnId)));
        }
        ex.extend(grid);
    }

    @Override
    public void generateData(Object item, JsonObject jsonObject) {
    }
}
