package application;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.event.*;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;

public class LoginController {
	@FXML
	private Button cancelButton;
	@FXML
	private Label loginMessageLabel;
	@FXML
	private TextField usernameTextField;
	@FXML
	private PasswordField enterPasswordField;
	
	
	
	public void loginButtonOnAction() {

		if(usernameTextField.getText().isBlank() == false && enterPasswordField.getText().isBlank() == false)
		{
			validateLogin();
		}
		else
		{
			loginMessageLabel.setText("Please enter both Username and Password");
		}
		
	}
	
	public void cancelButtonOnAction(ActionEvent event)
	{
		Stage stage = (Stage) cancelButton.getScene().getWindow();
		stage.close();
		
	}
	
	public void validateLogin()
	{
		DatabaseConnection connectNow = new DatabaseConnection();
		Connection connectDB = connectNow.getConnection();
		
		String verifyLogin = "Select count(1) from user_account where username = '"+usernameTextField.getText()+"' and password ='"+enterPasswordField.getText()+"'";
		try
		{
			Statement statement = connectDB.createStatement();
			ResultSet rs = statement.executeQuery(verifyLogin);
			while(rs.next())
			{
				if(rs.getInt(1)>=1)
				{
					loginMessageLabel.setText("Congatulations. Login Successful");
					DialogBox.display("Welcome","Login Successful!");
					createAccountForm();
				}
				else
				{
					loginMessageLabel.setText("Invalid Login. Try agian");
					DialogBox.display("Sorry","Invalid Login. Try Again!");
				}
			}	
		}
		catch(Exception e)
		{
			e.printStackTrace();
			e.getCause();
		}
	}
		
		public void createAccountForm()
		{
			try 
			{
				Parent root = FXMLLoader.load(getClass().getResource("register.fxml"));
//				primaryStage.setTitle("Hello World");
				Stage reisterStage = new Stage();
				reisterStage.initStyle(StageStyle.UNDECORATED);
				reisterStage.setScene(new Scene(root));
				reisterStage.show();
			}
			catch(Exception e)
			{
				e.printStackTrace();
				e.getCause();
			}
		}
}
