package br.com.caelum.agenda;

import java.sql.Connection;
import java.sql.DriverManager;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;

public class ConnectionFactory {

	@Produces
	@RequestScoped
	public Connection getConnection() {
		System.out.println("conectando ...");

		try {
			Class.forName("com.mysql.jdbc.Driver");
			return DriverManager.getConnection("jdbc:mysql://localhost/fj21", "root", "");
		} catch (Exception e) {
			throw new RuntimeException(e);
		}

	}

	public void close(@Disposes Connection con) {
		System.out.println("Closing connection....");
		try {
			con.close();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}
