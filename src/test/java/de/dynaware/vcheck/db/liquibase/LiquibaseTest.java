package de.dynaware.vcheck.db.liquibase;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.LiquibaseException;
import liquibase.resource.ClassLoaderResourceAccessor;
import liquibase.resource.ResourceAccessor;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;

public class LiquibaseTest {
	private static final String CONNECTION_STRING = "jdbc:h2:mem:db";
	private static final String USER_NAME = "sa";
	private static final String PASSWORD = "sa";

	private Connection connection;

	@Before
	public void setUp() throws ClassNotFoundException, SQLException {
		Class.forName("org.h2.Driver");
		connection = DriverManager.getConnection(CONNECTION_STRING, USER_NAME, PASSWORD);
	}

	@Test
	public void testLiquibaseScriptWithDotInPath()
			throws LiquibaseException {
		ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();

		// Get liquibase database
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
		Liquibase liquibase = new Liquibase("db-change.log/db-changelog-master.xml", resourceAccessor, database);
		liquibase.update("test");

		assertTrue(true);
	}

	@Test
	public void testLiquibaseScriptWithoutDotInPath()
			throws LiquibaseException {
		ResourceAccessor resourceAccessor = new ClassLoaderResourceAccessor();

		// Get liquibase database
		Database database = DatabaseFactory.getInstance().findCorrectDatabaseImplementation(new JdbcConnection(connection));
		Liquibase liquibase = new Liquibase("db-changelog/db-changelog-master.xml", resourceAccessor, database);
		liquibase.update("test2");

		assertTrue(true);
	}

	@After
	public void tearDown() throws SQLException   {
		connection.close();
	}
}
