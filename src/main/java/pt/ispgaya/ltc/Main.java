package pt.ispgaya.ltc;

import java.util.ArrayList;
import java.util.List;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/** Interface inicial e navegação principal do Trabalho LTC. */
public class Main extends Application {

    private static final String DASHBOARD = "Painel Inicial";
    private static final String ATTENDANCE = "Registo de Presenças";
    private static final String DATA_MANAGEMENT = "Gestão e Análise de Dados";
    private static final List<String> MENU_ITEMS = List.of(
            DASHBOARD,
            ATTENDANCE,
            "Minhas Aulas",
            "Histórico",
            DATA_MANAGEMENT,
            "Pesquisas",
            "Ordenações",
            "Estruturas de Dados",
            "Exportação");

    private final List<Button> navigationButtons = new ArrayList<>();
    private BorderPane layout;

    @Override
    public void start(Stage stage) {
        layout = new BorderPane();
        layout.getStyleClass().add("root");
        layout.setTop(createHeader());
        layout.setLeft(createSidebar());
        showPage(DASHBOARD);

        Scene scene = new Scene(layout, 1200, 750);
        scene.getStylesheets().add(Main.class.getResource("/css/app.css").toExternalForm());

        stage.setTitle("Trabalho LTC");
        stage.setMinWidth(900);
        stage.setMinHeight(600);
        stage.setScene(scene);
        stage.show();
    }

    private Node createHeader() {
        Label brand = new Label("ISPGAYA");
        brand.getStyleClass().add("brand-mark");
        Label subtitle = new Label("Trabalho LTC");
        subtitle.getStyleClass().add("brand-subtitle");
        HBox header = new HBox(14, brand, subtitle);
        header.setAlignment(Pos.CENTER_LEFT);
        header.getStyleClass().add("top-header");
        return header;
    }

    private Node createSidebar() {
        Label section = new Label("NAVEGAÇÃO");
        section.getStyleClass().add("sidebar-caption");

        VBox menu = new VBox(6);
        for (String item : MENU_ITEMS) {
            Button button = new Button(item);
            button.setMaxWidth(Double.MAX_VALUE);
            button.setAlignment(Pos.CENTER_LEFT);
            button.getStyleClass().add("nav-button");
            button.setOnAction(event -> showPage(item));
            navigationButtons.add(button);
            menu.getChildren().add(button);
        }

        VBox sidebar = new VBox(18, section, menu);
        sidebar.setPadding(new Insets(26, 14, 20, 14));
        sidebar.getStyleClass().add("sidebar");
        sidebar.setPrefWidth(255);
        return sidebar;
    }

    private void showPage(String page) {
        for (Button button : navigationButtons) {
            button.getStyleClass().remove("active");
            if (button.getText().equals(page)) {
                button.getStyleClass().add("active");
            }
        }
        layout.setCenter(page.equals(DASHBOARD) ? createDashboard() : createSectionPage(page));
    }

    private Node createDashboard() {
        Label eyebrow = new Label("ÁREA DE TRABALHO");
        eyebrow.getStyleClass().add("eyebrow");
        Label title = new Label("Painel Inicial");
        title.getStyleClass().add("page-title");
        Label intro = new Label("Aceda às áreas principais do Trabalho LTC.");
        intro.getStyleClass().add("page-intro");

        HBox cards = new HBox(18,
                createFeatureCard(ATTENDANCE, "Consultar e organizar os registos de presenças."),
                createFeatureCard(DATA_MANAGEMENT, "Explorar ferramentas de gestão e análise de dados."));
        cards.setAlignment(Pos.TOP_LEFT);
        HBox.setHgrow(cards.getChildren().get(0), Priority.ALWAYS);
        HBox.setHgrow(cards.getChildren().get(1), Priority.ALWAYS);

        VBox content = new VBox(12, eyebrow, title, intro, cards);
        content.setPadding(new Insets(42, 44, 36, 44));
        content.getStyleClass().add("page-content");
        VBox.setMargin(cards, new Insets(20, 0, 0, 0));
        return content;
    }

    private Node createFeatureCard(String title, String description) {
        Label heading = new Label(title);
        heading.getStyleClass().add("card-title");
        heading.setWrapText(true);
        Label detail = new Label(description);
        detail.getStyleClass().add("card-description");
        detail.setWrapText(true);
        Label action = new Label("Abrir área  →");
        action.getStyleClass().add("card-action");

        VBox card = new VBox(14, heading, detail, action);
        card.setPadding(new Insets(24));
        card.setMinHeight(190);
        card.setMaxWidth(Double.MAX_VALUE);
        card.getStyleClass().add("feature-card");
        card.setOnMouseClicked(event -> showPage(title));
        return card;
    }

    private Node createSectionPage(String page) {
        Label eyebrow = new Label("TRABALHO LTC");
        eyebrow.getStyleClass().add("eyebrow");
        Label title = new Label(page);
        title.getStyleClass().add("page-title");
        title.setWrapText(true);
        Label intro = new Label("Esta área está preparada para a navegação e será desenvolvida nas próximas fases.");
        intro.getStyleClass().add("page-intro");
        intro.setWrapText(true);

        VBox content = new VBox(12, eyebrow, title, intro);
        content.setPadding(new Insets(42, 44, 36, 44));
        content.getStyleClass().add("page-content");
        return content;
    }

    public static void main(String[] args) {
        launch(args);
    }
}