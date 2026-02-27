module fr.isen.java2 {
        requires javafx.controls;
        requires javafx.fxml;
        requires transitive javafx.graphics;
        requires java.sql;
    requires java.desktop;

    // Export main package
        exports fr.isen.java2;

        opens fr.isen.java2 to javafx.fxml;
        opens fr.isen.java2.model to javafx.base;
        opens fr.isen.java2.view to javafx.fxml;
}