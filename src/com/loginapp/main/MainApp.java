package com.loginapp.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.loginapp.util.DbUtil;

public class MainApp {

	public static void main(String args[]) throws IOException {
		Connection con=DbUtil.getConnection();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String s=br.readLine();
		String input[]=s.split(" ");
		if(input.length!=3){
			System.out.println("Wrong input");
			System.exit(0);
		}
		int ch=Integer.parseInt(input[0]);
		String user=input[1];
		String pass=input[2];
		switch(ch){
		case 1:
			String cmd="Select * from login_test_tbl where user_name=? and user_pass=?";
			try {
				PreparedStatement stmt=con.prepareStatement(cmd);
				stmt.setString(1, user);
				stmt.setString(2, pass);
				ResultSet res=stmt.executeQuery();
				if(res.next()){
					System.out.println("User found.");
				}
				else{
					System.out.println("User not found.");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 2:
			cmd="Insert into login_test_tbl values(?,?)";
			try {
				PreparedStatement stmt=con.prepareStatement(cmd);
				stmt.setString(1, user);
				stmt.setString(2, pass);
				stmt.executeUpdate();
				System.out.println("User added");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Error Cannot add");
				//e.printStackTrace();
			}
			break;
		case 3:
			try {
					String qry="Update login_test_tbl set user_pass=? where user_name=?";
					PreparedStatement st=con.prepareStatement(qry);
					st.setString(1, pass);
					st.setString(2, user);
					int n=st.executeUpdate();
					if(n==0)
						System.out.println("No such user exists.");
					else
						System.out.println("Password Updated.");
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
				e.printStackTrace();
			}
			break;
		}
	}
}
