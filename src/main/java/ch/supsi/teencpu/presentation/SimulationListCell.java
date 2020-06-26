package ch.supsi.teencpu.presentation;

import ch.supsi.teencpu.repository.Simulation;
import javafx.scene.control.ListCell;

public class SimulationListCell extends ListCell<Simulation> {

    private final int CELL_HEIGHT = 60;
    private final int CELL_WIDTH = 280;

    @Override
    protected void updateItem(Simulation simulation, boolean empty) {
        super.updateItem(simulation, empty);
        if (empty) {
            setText(null);
        } else {
            setPrefSize(CELL_WIDTH, CELL_HEIGHT);
            SimulationPresenter presenter = new SimulationPresenter();
            setText(presenter.presentInListCell(simulation));
        }
    }

/*    private VBox cellLayout(Simulation simulation) {
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.BOTTOM_CENTER);
        vBox.setSpacing(15);

        Label label = new Label(simulation.getId().toString());
        label.setStyle("-fx-font-size: 14;");
        Separator hSeparator = new Separator(Orientation.HORIZONTAL);

        vBox.getChildren().addAll(label, hSeparator);
        VBox.setMargin(hSeparator, new Insets(0, 0, -5, 0));
        return vBox;
    }*/
}
