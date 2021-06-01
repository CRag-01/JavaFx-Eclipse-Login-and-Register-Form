package application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.Statement;




public class RegisterController {
	
	@FXML
	private Button closeButton;
	@FXML
	private Button registerButton;
	@FXML
	private Label registrationMessageLabel;
	@FXML
	private TextField setPasswordField;
	@FXML 
	private TextField firstnameTextField;
	@FXML 
	private TextField lastnameTextField;
	@FXML 
	private TextField usernameTextField;
	@FXML 
	private TextField idTextField;
	
	
	public void registerButtonOnAction(ActionEvent event)
	{
		
		registerUser();
	}
	
	public void closeButtonOnAction(ActionEvent event) 
	{
		Stage stage = (Stage) closeButton.getScene().getWindow();
		stage.close();
		Platform.exit();
	}
	
	public void registerUser()
	{
		if(setPasswordField.getText().length()>=5)
		{
			DatabaseConnection connectNow = new DatabaseConnection();
			Connection connectDB = connectNow.getConnection();
			
			String id = idTextField.getText();
			String firstname = firstnameTextField.getText();
			String lastname = lastnameTextField.getText();
			String username = usernameTextField.getText();
			String password = setPasswordField.getText();
			
			String insertFields = "INSERT INTO user_account(account_id,firstname,lastname,username,password) VALUES (";
			String insertValues = id+",'"+firstname +"','"+ lastname+"','"+ username+"','"+ password+"');";
			String insertToRegister = insertFields + insertValues;
			
			System.out.println(insertToRegister);
			try 
			{
				Statement statement = connectDB.createStatement();
				statement.executeUpdate(insertToRegister);
				registrationMessageLabel.setText("User Registered Sucessfully!");
				DialogBox.display("Congrats","Data Entered Successfully!");
				idTextField.setText("");
				firstnameTextField.setText("");
				lastnameTextField.setText("");
				usernameTextField.setText("");
				setPasswordField.setText("");
				
			}
			catch(Exception e)
			{
				e.printStackTrace();
				e.getCause();
			}		
		}
		else
		{
			registrationMessageLabel.setText("Password too Short.");
		}
	}
	
	

}
