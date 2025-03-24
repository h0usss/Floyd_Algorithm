module com.h0uss.floyd_algorithm {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.h0uss.floyd_algorithm to javafx.fxml;
    exports com.h0uss.floyd_algorithm;
}