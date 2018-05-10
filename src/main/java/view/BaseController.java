package view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;

abstract class BaseController<T> {
    private final Stage stage = new Stage();

    protected abstract void setModel(final T model);

    <TT> void showView(final String viewName, final TT model) {
        final URL resource = getClass().getClassLoader().getResource(viewName);
        try {
            final FXMLLoader loader = new FXMLLoader(resource);
            stage.setScene(new Scene(loader.load()));
            final BaseController controller = loader.getController();
            controller.setModel(model);
            stage.show();
        } catch(final IOException e) {
            e.printStackTrace();
        }
    }
}
