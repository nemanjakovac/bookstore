package rs.nkovac.project.bookstore.client;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import rs.nkovac.project.bookstore.model.Book;

public class InitializeForm extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel panelWelcome;
	private JPanel panelAdd;
	private JPanel panelSearch;
	private JTextField txtIsbn;
	private JTextField txtTitle;
	private JTextField txtAuthors;
	private JTextField txtGenre;
	private JTextField txtPagesNumber;
	private JButton btnSaveBook;
	private JTextField txtSearchIsbn;
	private JButton btnSearchBook;
	private ClientController controller;

	/**
	 * Create the main frame.
	 */
	public InitializeForm() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 400);

		createMenu();
		createPanelWelcome();
		createPanelAdd();
		createPanelSearch();
		controller = new ClientController();

	}

	/**
	 * Send new book insert data and show response of action success in dialog
	 */
	private void saveBook() {
		btnSaveBook.setEnabled(false);
		Book book = new Book(txtIsbn.getText(), txtTitle.getText(), txtAuthors.getText(),
				txtGenre.getText(), Integer.parseInt(txtPagesNumber.getText()));
		Vector response = controller.sendAddBookRequest(book);
		JOptionPane.showMessageDialog(this, response.get(1));
		flushTextFields();
		btnSaveBook.setEnabled(true);
	}

	/**
	 * Serch book by given criteria and show book data in dialog if found
	 */
	private void searchBook() {
	//	btnSearchBook.setEnabled(false);
		Vector response = controller.searchBook(txtSearchIsbn.getText());
		Book book = (Book) response.get(1);

		if (book == null) {
			JOptionPane.showMessageDialog(this, response.get(2));
		} else {
			StringBuilder a = new StringBuilder();
			a.append("Book found:").append("\nISBN: ").append(book.getIsbn()).append("\nTitle: ")
					.append(book.getTitle()).append("\nAuthors:").append(book.getAuthors()).append("\nGenre:")
					.append(book.getGenre()).append("\nNumber of pages:").append(book.getPagesNum());
			JOptionPane.showMessageDialog(this, a.toString());
		}

		flushTextFields();
		btnSearchBook.setEnabled(true);
	}

	private void createMenu() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnBooks = new JMenu("Books");
		menuBar.add(mnBooks);

		JMenuItem mntmAddNew = new JMenuItem("Add new...");
		mntmAddNew.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getContentPane().equals(panelAdd)) {
					getContentPane().setVisible(false);
					setContentPane(panelAdd);
					panelAdd.setVisible(true);
					flushTextFields();
				}
			}
		});
		mnBooks.add(mntmAddNew);

		JMenuItem mntmSearch = new JMenuItem("Search");
		mntmSearch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!getContentPane().equals(panelSearch)) {
					getContentPane().setVisible(false);
					setContentPane(panelSearch);
					panelSearch.setVisible(true);
					flushTextFields();
				}
			}
		});
		mnBooks.add(mntmSearch);
	}

	private void createPanelWelcome() {
		panelWelcome = new JPanel();
		panelWelcome.setBorder(new EmptyBorder(5, 5, 5, 5));
		panelWelcome.setLayout(new BorderLayout(0, 0));
		setContentPane(panelWelcome);

		JLabel lblWelcome = new JLabel("Welcome");
		lblWelcome.setHorizontalAlignment(SwingConstants.CENTER);
		panelWelcome.add(lblWelcome, BorderLayout.CENTER);
	}

	private void createPanelAdd() {
		panelAdd = new JPanel();
		GridBagLayout gbl_panelAdd = new GridBagLayout();
		gbl_panelAdd.columnWidths = new int[] { 60, 30, 150, 0 };
		gbl_panelAdd.rowHeights = new int[] { 40, 40, 40, 40, 40, 30, 0 };
		gbl_panelAdd.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelAdd.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };
		panelAdd.setLayout(gbl_panelAdd);

		JLabel lblISBN = new JLabel("ISBN: ");
		lblISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.fill = GridBagConstraints.BOTH;
		gbc_lblISBN.insets = new Insets(0, 0, 5, 5);
		gbc_lblISBN.gridx = 0;
		gbc_lblISBN.gridy = 0;
		panelAdd.add(lblISBN, gbc_lblISBN);

		JLabel label = new JLabel("");
		GridBagConstraints gbc_label = new GridBagConstraints();
		gbc_label.fill = GridBagConstraints.BOTH;
		gbc_label.insets = new Insets(0, 0, 5, 5);
		gbc_label.gridx = 1;
		gbc_label.gridy = 0;
		panelAdd.add(label, gbc_label);

		txtIsbn = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		panelAdd.add(txtIsbn, gbc_textField);
		txtIsbn.setColumns(10);

		JLabel lblTitle = new JLabel("Title: ");
		lblTitle.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblTitle = new GridBagConstraints();
		gbc_lblTitle.fill = GridBagConstraints.BOTH;
		gbc_lblTitle.insets = new Insets(0, 0, 5, 5);
		gbc_lblTitle.gridx = 0;
		gbc_lblTitle.gridy = 1;
		panelAdd.add(lblTitle, gbc_lblTitle);

		JLabel label_1 = new JLabel("");
		GridBagConstraints gbc_label_1 = new GridBagConstraints();
		gbc_label_1.fill = GridBagConstraints.BOTH;
		gbc_label_1.insets = new Insets(0, 0, 5, 5);
		gbc_label_1.gridx = 1;
		gbc_label_1.gridy = 1;
		panelAdd.add(label_1, gbc_label_1);

		txtTitle = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelAdd.add(txtTitle, gbc_textField_1);
		txtTitle.setColumns(10);

		JLabel lblAuthors = new JLabel("Authors: ");
		lblAuthors.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblAuthors = new GridBagConstraints();
		gbc_lblAuthors.fill = GridBagConstraints.BOTH;
		gbc_lblAuthors.insets = new Insets(0, 0, 5, 5);
		gbc_lblAuthors.gridx = 0;
		gbc_lblAuthors.gridy = 2;
		panelAdd.add(lblAuthors, gbc_lblAuthors);

		JLabel label_2 = new JLabel("");
		GridBagConstraints gbc_label_2 = new GridBagConstraints();
		gbc_label_2.fill = GridBagConstraints.BOTH;
		gbc_label_2.insets = new Insets(0, 0, 5, 5);
		gbc_label_2.gridx = 1;
		gbc_label_2.gridy = 2;
		panelAdd.add(label_2, gbc_label_2);

		txtAuthors = new JTextField();
		GridBagConstraints gbc_textField_2 = new GridBagConstraints();
		gbc_textField_2.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_2.insets = new Insets(0, 0, 5, 5);
		gbc_textField_2.gridx = 2;
		gbc_textField_2.gridy = 2;
		panelAdd.add(txtAuthors, gbc_textField_2);
		txtAuthors.setColumns(10);

		JLabel lblGenre = new JLabel("Genre: ");
		lblGenre.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblGenre = new GridBagConstraints();
		gbc_lblGenre.fill = GridBagConstraints.BOTH;
		gbc_lblGenre.insets = new Insets(0, 0, 5, 5);
		gbc_lblGenre.gridx = 0;
		gbc_lblGenre.gridy = 3;
		panelAdd.add(lblGenre, gbc_lblGenre);

		JLabel label_3 = new JLabel("");
		GridBagConstraints gbc_label_3 = new GridBagConstraints();
		gbc_label_3.fill = GridBagConstraints.BOTH;
		gbc_label_3.insets = new Insets(0, 0, 5, 5);
		gbc_label_3.gridx = 1;
		gbc_label_3.gridy = 3;
		panelAdd.add(label_3, gbc_label_3);

		txtGenre = new JTextField();
		GridBagConstraints gbc_textField_3 = new GridBagConstraints();
		gbc_textField_3.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_3.insets = new Insets(0, 0, 5, 5);
		gbc_textField_3.gridx = 2;
		gbc_textField_3.gridy = 3;
		panelAdd.add(txtGenre, gbc_textField_3);
		txtGenre.setColumns(10);

		JLabel lblPagesNumber = new JLabel("Pages Number: ");
		lblPagesNumber.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblPagesNumber = new GridBagConstraints();
		gbc_lblPagesNumber.fill = GridBagConstraints.BOTH;
		gbc_lblPagesNumber.insets = new Insets(0, 0, 5, 5);
		gbc_lblPagesNumber.gridx = 0;
		gbc_lblPagesNumber.gridy = 4;
		panelAdd.add(lblPagesNumber, gbc_lblPagesNumber);

		JLabel label_4 = new JLabel("");
		GridBagConstraints gbc_label_4 = new GridBagConstraints();
		gbc_label_4.fill = GridBagConstraints.BOTH;
		gbc_label_4.insets = new Insets(0, 0, 5, 5);
		gbc_label_4.gridx = 1;
		gbc_label_4.gridy = 4;
		panelAdd.add(label_4, gbc_label_4);

		txtPagesNumber = new JTextField();
		GridBagConstraints gbc_textField_4 = new GridBagConstraints();
		gbc_textField_4.insets = new Insets(0, 0, 5, 5);
		gbc_textField_4.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_4.gridx = 2;
		gbc_textField_4.gridy = 4;
		panelAdd.add(txtPagesNumber, gbc_textField_4);
		txtPagesNumber.setColumns(10);

		btnSaveBook = new JButton("Save Book");
		btnSaveBook.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				saveBook();
			}
		});
		GridBagConstraints gbc_btnSaveBook = new GridBagConstraints();
		gbc_btnSaveBook.insets = new Insets(0, 0, 0, 5);
		gbc_btnSaveBook.gridx = 2;
		gbc_btnSaveBook.gridy = 6;
		panelAdd.add(btnSaveBook, gbc_btnSaveBook);
	}

	private void createPanelSearch() {
		panelSearch = new JPanel();

		GridBagLayout gbl_panelAdd = new GridBagLayout();
		gbl_panelAdd.columnWidths = new int[] { 60, 30, 150, 0 };
		gbl_panelAdd.rowHeights = new int[] { 40, 40, 40, 40, 40, 30, 0 };
		gbl_panelAdd.columnWeights = new double[] { 0.0, 0.0, 0.0 };
		gbl_panelAdd.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0 };

		JLabel lblISBN = new JLabel("ISBN");
		lblISBN.setHorizontalAlignment(SwingConstants.RIGHT);
		GridBagConstraints gbc_lblISBN = new GridBagConstraints();
		gbc_lblISBN.fill = GridBagConstraints.BOTH;
		gbc_lblISBN.insets = new Insets(0, 0, 5, 5);
		gbc_lblISBN.gridx = 0;
		gbc_lblISBN.gridy = 0;

		txtSearchIsbn = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 0;
		txtSearchIsbn.setColumns(10);

		btnSearchBook = new JButton("Search");
		GridBagConstraints gbc_btnSearchBook = new GridBagConstraints();
		gbc_btnSearchBook.insets = new Insets(0, 0, 0, 5);
		gbc_btnSearchBook.gridx = 2;
		gbc_btnSearchBook.gridy = 1;
		btnSearchBook.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				searchBook();
			}
		});

		panelSearch.setLayout(gbl_panelAdd);

		panelSearch.add(lblISBN, gbc_lblISBN);
		panelSearch.add(txtSearchIsbn, gbc_textField);
		panelSearch.add(btnSearchBook, gbc_btnSearchBook);

	}

	private void flushTextFields() {
		for (Component component : panelAdd.getComponents()) {
			if (component instanceof JTextField)
				((JTextField) component).setText("");
		}
		for (Component component : panelSearch.getComponents()) {
			if (component instanceof JTextField)
				((JTextField) component).setText("");
		}
	}
}
