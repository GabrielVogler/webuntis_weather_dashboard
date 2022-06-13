module com.voglic {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires dotenv.java;
    requires json.simple;

    opens com.voglic to javafx.fxml;

    exports com.voglic;
}
