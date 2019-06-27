package application;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import DB.DB;

public class Program {

	public static void main(String[] args) throws SQLException, IOException {

		Statement statement = null;
		ResultSet resultSet = null;
		Connection connection = null;

		String ext = ".xml";
		int i = 1;

		try {
			connection = DB.getConnection();

			statement = connection.createStatement();

			resultSet = statement.executeQuery(
					"select conteudo from xml_documento where data_insercao between '2014-01-01' and '2018-12-31'");
			// resultSet = statement.executeQuery("select conteudo from xml_documento where
			// id = 3");

			while (resultSet.next()) {

				String xml = resultSet.getString("conteudo");

				if (xml != null) {
					String path = "C:\\Temp\\\\xmls\\XML_Recebimento";
					path = path + i;

					// System.out.println(xml);

					BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + ext));
					bufferedWriter.write(xml);
					bufferedWriter.flush();

					// System.out.println(path);
					i += 1;
					path = "";
				}

			}
		} catch (Exception e) {
			System.out.println("n");
		}

		DB.closeConnection();

	}

}
