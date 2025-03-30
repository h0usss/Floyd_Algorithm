module com.h0uss.floyd_algorithm {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;


    exports com.h0uss.floyd_algorithm.logic;
    opens com.h0uss.floyd_algorithm.logic to javafx.fxml;
    exports com.h0uss.floyd_algorithm.UI;
    opens com.h0uss.floyd_algorithm.UI to javafx.fxml;
    exports com.h0uss.floyd_algorithm.UI.cells;
    opens com.h0uss.floyd_algorithm.UI.cells to javafx.fxml;
}