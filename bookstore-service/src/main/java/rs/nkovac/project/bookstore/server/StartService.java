package rs.nkovac.project.bookstore.server;

import javax.sql.DataSource;

import org.skife.jdbi.v2.DBI;

import io.dropwizard.Application;
import io.dropwizard.setup.Environment;
import rs.nkovac.project.bookstore.configuration.AppConfig;
import rs.nkovac.project.bookstore.controller.BookController;
import rs.nkovac.project.bookstore.service.BookstoreService;

public class StartService extends Application<AppConfig> {
	
	private static final String SQL = "sql";
	
	public static void main(String[] args) throws Exception {
		new StartService().run(args);
	}

	@Override
	public void run(AppConfig configuration, Environment environment) throws Exception {
		System.out.println("Bookstore service started!");
		
		final DataSource dataSource = configuration.getDataSourceFactory().build(environment.metrics(), SQL);
		DBI dbi = new DBI(dataSource);
		
		environment.jersey().register(new BookController(dbi.onDemand(BookstoreService.class)));
		
	}

}
