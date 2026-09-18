package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class Employee {
	static Scanner sc = new Scanner(System.in);
	static Connection conn = null;
	static Statement st = null;
	static PreparedStatement ps = null;
	static ResultSet rs = null;

	public static void main(String[] args) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee?user=root&&password=kalyan");

			// st.execute("CREATE TABLE EMP(emp_id int,email varchar(30),name
			// varchar(20),password varchar(10),contact long,age int ,address
			// varchar(40),salary double,dept int )");

			st = conn.createStatement();

			boolean e = true;
			while (e) {
				System.out.println("1.Login \n2.CreateAccount \n3.Exit");
				int ch = sc.nextInt();
				switch (ch) {
				case 1 -> {
					System.out.println("1.Login with email \n2.Login with mobile \n3.Go back \n.Exit");
					ch = sc.nextInt();
					switch (ch) {
					case 1 -> {
						System.out.println("1.Continue \n 2.Go back");
						ch = sc.nextInt();
						switch (ch) {
						case 1 -> {

							System.out.println("Enter the email:");
							String email = sc.next();
							System.out.println("Enter the password:");
							String password = sc.next();
							rs = st.executeQuery("select email,password from emp where email='" + email + "'");

							if (rs.next()) {
								String dbEmail = rs.getString("email");
								String dbPassword = rs.getString("password");

								if (dbEmail.equals(email) && dbPassword.equals(password)) {
									System.out.println("Welcome to profile");
									while(e) {
										System.out.println("1.view details \n2.update \n3.Delete \n4.Exit");
										ch = sc.nextInt();
										switch (ch) {
										case 1 -> {
											rs = st.executeQuery("select * from emp where email ='" + dbEmail + "'");
											if(rs.next()) {
											System.out.println(rs.getInt(1) + " \n " + rs.getString(2) + " \n "
													+ rs.getString(3) + " \n " + rs.getString(4) + " \n " + rs.getLong(5)
													+ " \n " + rs.getInt(6) + " \n " + rs.getString(7) + " \n "
													+ rs.getDouble(8) + " \n " + rs.getInt(9));

										}
										}
										case 2 -> {
											ps = conn.prepareStatement(
													"update emp set name=?, password=?, contact=?, age=?, address=?, salary=?, dept=? where email='"+email+"'");
											System.out.println("Enter your email:");
											String updatedemail = sc.next();
											System.out.println("Enter your name:");
											String updatedname = sc.next();
											System.out.println("Enter your password:");
											String updatedpassword = sc.next();
											System.out.println("Enter your Contact:");
											long updatedcontact = sc.nextLong();
											System.out.println("Enter your age:");
											int updatedage = sc.nextInt();
											sc.nextLine();
											System.out.println("Enter your address:");
											String updatedaddress = sc.nextLine();
											System.out.println("Enter your salary:");
											double updatedsalary = sc.nextDouble();
											System.out.println("Enter your dept:");
											int updateddept = sc.nextInt();

											ps.setString(1, updatedname);
											ps.setString(2, updatedpassword);
											ps.setLong(3, updatedcontact);
											ps.setInt(4, updatedage);
											ps.setString(5, updatedaddress);
											ps.setDouble(6, updatedsalary);
											ps.setInt(7, updateddept);
											ps.setString(8, updatedemail);
											int rows = ps.executeUpdate();

											if (rows > 0) {
												System.out.println("Updated successfully");
											} else {
												System.out.println("Update failed / email not found");
											}
										}
										case 3 -> {
											ps = conn.prepareStatement("DELETE FROM emp WHERE email='" + email + "'");
										}
										case 4 -> {
											System.out.println("Exiting...");

											System.exit(0);
										}
										}
									}

								} else {
									System.out.println("Invalid email or password");
								}
							} else {
								System.out.println("No account found, please create account first");
							}
						}
						
						case 2 -> {
							return;
						}
						}
					}
					case 2 -> {
						System.out.println("1.Continue \n 2.Go back");
						ch = sc.nextInt();
						switch (ch) {
						case 1 -> {
							OtpGenerator otp = new OtpGenerator();

							System.out.println("Enter your mobile no");
							long mobile = sc.nextLong();
							System.out.println("Enter the otp:");
							otp.otpGen();
							int enteredotp = sc.nextInt();
							if (enteredotp == otp.otp) {
								System.out.println("Welcome to profile");
								while(e) {
									System.out.println("1.view details \n2.update \n3.Delete \n4.Exit");
									ch = sc.nextInt();
									switch (ch) {
									case 1 -> {
										rs = st.executeQuery("select * from emp where contact ='" + mobile + "'");
										if(rs.next()) {
										System.out.println(rs.getInt(1) + " \n " + rs.getString(2) + " \n "
												+ rs.getString(3) + " \n " + rs.getString(4) + " \n " + rs.getLong(5)
												+ " \n " + rs.getInt(6) + " \n " + rs.getString(7) + " \n "
												+ rs.getDouble(8) + " \n " + rs.getInt(9));

									}
										else {
											System.out.println("No account found by your mobile");
										}
									}
									case 2 -> {
										ps = conn.prepareStatement(
												"update emp set name=?, password=?, contact=?, age=?, address=?, salary=?, dept=? where contact='"+mobile+"'");
										System.out.println("Enter your email:");
										String updatedemail = sc.next();
										System.out.println("Enter your name:");
										String updatedname = sc.next();
										System.out.println("Enter your password:");
										String updatedpassword = sc.next();
										System.out.println("Enter your Contact:");
										long updatedcontact = sc.nextLong();
										System.out.println("Enter your age:");
										int updatedage = sc.nextInt();
										sc.nextLine();
										System.out.println("Enter your address:");
										String updatedaddress = sc.nextLine();
										System.out.println("Enter your salary:");
										double updatedsalary = sc.nextDouble();
										System.out.println("Enter your dept:");
										int updateddept = sc.nextInt();

										ps.setString(1, updatedname);
										ps.setString(2, updatedpassword);
										ps.setLong(3, updatedcontact);
										ps.setInt(4, updatedage);
										ps.setString(5, updatedaddress);
										ps.setDouble(6, updatedsalary);
										ps.setInt(7, updateddept);
										ps.setString(8, updatedemail);
										int rows = ps.executeUpdate();

										if (rows > 0) {
											System.out.println("Updated successfully");
										} else {
											System.out.println("Update failed / email not found");
										}
									}
									case 3 -> {
										ps = conn.prepareStatement("DELETE FROM emp WHERE email='" + mobile + "'");
									}
									case 4 -> {
										System.out.println("Exiting...");

										System.exit(0);
									}
									}
									}

								} else {
									System.out.println(" Incorrect otp / Account not fount ,Please create account first");
								}
							}
							case 2 -> {
								return;
							}
							}
						}
						case 3 -> {
							return;
						}
						case 4 -> {
							System.out.println("Exiting...");

							System.exit(0);
						}
						
								}
				}
				case 2 -> {
					createAccount();
				}
				case 3 -> {
					System.out.println("Exiting...");

					System.exit(0);
				}
				}
			}

		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
	
	}
	}
	public static void createAccount() throws SQLException {
		rs = st.executeQuery("select max(emp_id) from emp");
		rs.next();
		int maxId = rs.getInt(1);
		int emp_id;

		if (rs.wasNull()) {
			emp_id = 1;
		} else {
			emp_id = maxId + 1;
		}

		System.out.println("Enter your email:");
		String email = sc.next();
		System.out.println("Enter your name:");
		String name = sc.next();
		System.out.println("Enter your password:");
		String password = sc.next();
		System.out.println("Enter your Contact:");
		long contact = sc.nextLong();
		System.out.println("Enter your age:");
		int age = sc.nextInt();
		sc.nextLine();
		System.out.println("Enter your address:");
		String address = sc.nextLine();
		System.out.println("Enter your salary:");
		double salary = sc.nextDouble();
		System.out.println("Enter your dept:");
		int dept = sc.nextInt();

		ps = conn.prepareStatement("insert into emp values (?,?,?,?,?,?,?,?,?)");

		ps.setInt(1, emp_id);
		ps.setString(2, email);
		ps.setString(3, name);
		ps.setString(4, password);
		ps.setLong(5, contact);
		ps.setInt(6, age);
		ps.setString(7, address);
		ps.setDouble(8, salary);
		ps.setInt(9, dept);
		ps.executeUpdate();

		System.out.println("Account created successfully");
	}	
	}

