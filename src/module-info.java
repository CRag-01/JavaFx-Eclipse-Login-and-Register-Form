module LoginRegister {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires java.sql;
	requires java.desktop;
	requires javafx.base;
//	requires mysql.connector.java;
	
	opens application to javafx.graphics, javafx.fxml;
}
