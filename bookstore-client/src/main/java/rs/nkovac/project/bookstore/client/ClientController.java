package rs.nkovac.project.bookstore.client;

import java.util.Vector;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rs.nkovac.project.bookstore.model.*;

//import rs.nkovac.project.bookstore.model.Book;

/**
 * @author kovac
 * 
 *         Sends requests to server, and receives response
 *
 */
public class ClientController {

	/**
	 * @param isbn
	 * @param title
	 * @param authors
	 * @param genre
	 * @param pagesNumber
	 * @return
	 * 
	 * 		Send data to insert new book to server
	 */
	public Vector sendAddBookRequest(Book book) {
		Vector resp = new Vector();
		String responseString = "";
		String jsonBook = "";

		// map object
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			jsonBook = objectMapper.writeValueAsString(book);
		} catch (JsonProcessingException e) {
			responseString = "Unable to parse book";
		}

		// create client
		Client client = ClientBuilder.newClient();

		// set request 
		WebTarget webTarget = client.target("http://localhost:6666/bookstore/book/add");

		Invocation.Builder invocationBuilder = webTarget.request(MediaType.APPLICATION_JSON);

		// send post 
		Response response = invocationBuilder.post(Entity.entity(jsonBook, MediaType.APPLICATION_JSON));

		if (response.getStatus() != 200) {
			responseString = "Book insert failed!";
			responseString = response.readEntity(String.class);
		} else {
			responseString = "Book insert successful!";

		}
		resp.add(response.getStatus());
		resp.add(responseString);

		return resp;

	}

	/**
	 * @param searchIsbn
	 * @return
	 * 
	 * 		Sends search data to get book from server. Returns vector containing
	 *         response and book object
	 */
	public Vector searchBook(String searchIsbn) {
		Vector resp = new Vector();
		ObjectMapper objectMapper;
		Book book = null;
		String responseString = "";

		// create client
		Client client = ClientBuilder.newClient();

		// define url for request
		WebTarget webTarget = client.target("http://localhost:6666/bookstore/book/get/" + searchIsbn);

		//	assign parameter if needed
		//	WebTarget webTargetWithQueryParameter = webTarget.queryParam("greeting", "Hi World!");

		// set body type
		Invocation.Builder invocationBuilder = webTarget.request();

		//	set request header if needed
		//	invocationBuilder.header("some-header", "true");

		Response response = invocationBuilder.get();

		if (response.getStatus() != 200) {
			responseString = response.readEntity(String.class);
		} else {
			objectMapper = new ObjectMapper();

			try {
				book = objectMapper.readValue(response.readEntity(String.class), Book.class);
				if (StringUtils.isNotBlank(book.getIsbn())) {
					responseString = "OK";
				} else {
					responseString = "Book not found!";
				}
			} catch (Exception e) {
				responseString = "Error reading response!";
			}

		}
		resp.add(response.getStatus());
		resp.add(book);
		resp.add(responseString);

		return resp;
	}

}
