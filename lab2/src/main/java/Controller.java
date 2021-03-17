import java.io.File;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button idButton;

    @FXML
    private TableView<GraphicFile> table;

    @FXML
    private TableColumn<GraphicFile, String> nameColumn;

    @FXML
    private TableColumn<GraphicFile, String> sizeColumn;

    @FXML
    private TableColumn<GraphicFile, String> resolutionColumn;

    @FXML
    private TableColumn<GraphicFile, String> colorDepthColumn;

    @FXML
    private TableColumn<GraphicFile, String> compressionColumn;

    @FXML
    private Button idDirButton;

    @FXML
    void initialize() {
        idButton.setOnAction(event -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("All files", "*.*"),
                    new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                    new FileChooser.ExtensionFilter("GIF", "*.gif"),
                    new FileChooser.ExtensionFilter("TIF", "*.tif"),
                    new FileChooser.ExtensionFilter("BMP", "*.bmp"),
                    new FileChooser.ExtensionFilter("PNG", "*.png"),
                    new FileChooser.ExtensionFilter("PCX", "*.pcx")
            );
            List<File> file = fileChooser.showOpenMultipleDialog(Main.stage);
            if (file != null) {
                table.setItems(getObsListInfo(file));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
                resolutionColumn.setCellValueFactory(new PropertyValueFactory<>("resolution"));
                colorDepthColumn.setCellValueFactory(new PropertyValueFactory<>("colorDepth"));
                compressionColumn.setCellValueFactory(new PropertyValueFactory<>("compression"));
            }
        });

        idDirButton.setOnAction(event -> {
            DirectoryChooser fileChooser = new DirectoryChooser();
            File file = fileChooser.showDialog(Main.stage);
            if (file != null) {
                table.setItems(getObsListInfo(Arrays.asList(file.listFiles())));
                nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
                sizeColumn.setCellValueFactory(new PropertyValueFactory<>("size"));
                resolutionColumn.setCellValueFactory(new PropertyValueFactory<>("resolution"));
                colorDepthColumn.setCellValueFactory(new PropertyValueFactory<>("colorDepth"));
                compressionColumn.setCellValueFactory(new PropertyValueFactory<>("compression"));
            }
        });
    }

    private ObservableList<GraphicFile> observableList = FXCollections.observableArrayList();

    private ObservableList<GraphicFile> getObsListInfo(List<File> file) {
        file.forEach(x -> {
            Handler fileMetadata = new Handler(x);
            observableList.add(new GraphicFile(fileMetadata.getFileName(), fileMetadata.getImageSize(),
                    fileMetadata.getResolution(), fileMetadata.getColorDepth(), fileMetadata.getCompression()));
        });
        return observableList;
    }
}

