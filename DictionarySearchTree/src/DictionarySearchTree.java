import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;

public class DictionarySearchTree {

	private JFrame frame;
	private JTextField addWordText;
	private JTextField checkWordText;
	private JTextField removeWordText;
	private JTextField addWordTextField;
	private JTextField checkWordTextField;
	private JTextField removeWordTextField;
	private JButton addWordBtn;
	private JButton checkWordBtn;
	private JButton removeWordBtn;
	
	private DictionaryTree wez = new DictionaryTree();
	private DictionaryTree wezTemp = new DictionaryTree();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DictionarySearchTree window = new DictionarySearchTree();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DictionarySearchTree() {
		initialize();
		
		addWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performAddWord();
			}
	});
		
		checkWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performCheckWord();
			}
	});
		
		removeWordBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				performRemoveWord();
			}
	});
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Dictionary Tree");
		lblNewLabel.setBounds(180, 10, 77, 35);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Add Word");
		lblNewLabel_1.setBounds(25, 61, 62, 21);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Remove Word");
		lblNewLabel_2.setBounds(317, 65, 96, 17);
		frame.getContentPane().add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Check Word");
		lblNewLabel_3.setBounds(180, 65, 96, 17);
		frame.getContentPane().add(lblNewLabel_3);
		
		addWordText = new JTextField();
		addWordText.setBounds(25, 103, 96, 19);
		frame.getContentPane().add(addWordText);
		addWordText.setColumns(10);
		
		checkWordText = new JTextField();
		checkWordText.setBounds(180, 103, 96, 19);
		frame.getContentPane().add(checkWordText);
		checkWordText.setColumns(10);
		
		removeWordText = new JTextField();
		removeWordText.setBounds(317, 103, 96, 19);
		frame.getContentPane().add(removeWordText);
		removeWordText.setColumns(10);
		
		addWordBtn = new JButton("Add Word");
		addWordBtn.setBounds(25, 143, 96, 21);
		frame.getContentPane().add(addWordBtn);
		
		checkWordBtn = new JButton("Check Word");
		checkWordBtn.setBounds(180, 143, 112, 21);
		frame.getContentPane().add(checkWordBtn);
		
		removeWordBtn = new JButton("Remove Word");
		removeWordBtn.setBounds(302, 143, 124, 21);
		frame.getContentPane().add(removeWordBtn);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 174, 127, 66);
		frame.getContentPane().add(scrollPane);
		
		addWordTextField = new JTextField();
		scrollPane.setViewportView(addWordTextField);
		addWordTextField.setColumns(10);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(163, 174, 128, 59);
		frame.getContentPane().add(scrollPane_1);
		
		checkWordTextField = new JTextField();
		scrollPane_1.setViewportView(checkWordTextField);
		checkWordTextField.setColumns(10);
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(302, 174, 124, 59);
		frame.getContentPane().add(scrollPane_2);
		
		removeWordTextField = new JTextField();
		scrollPane_2.setViewportView(removeWordTextField);
		removeWordTextField.setColumns(10);
	}
	
	private void performAddWord() {
		
		String hold = addWordText.getText().toLowerCase();
		
		wez.addWordNode(hold);
		wezTemp.addWordNode(hold);
		
		addWordTextField.setText("Your word has been added.");
		
//		System.out.println("wez words");
//		wez.inOrder();
//		System.out.println("wezTemp words");
//		wezTemp.inOrder();

	}
	
	private void performCheckWord() {
		
		//System.out.println("check Word");
		//wez.inOrder();
		
		assert(wez != null): "wez is null";
		
		//System.out.println("checkWordText " + checkWordText.getText().toLowerCase());
		
		WordNode temp = new WordNode(checkWordText.getText().toLowerCase());
		
		
		Boolean present = wez.spellCheck(wez.root,temp);
		
		
//		System.out.println("wez words");
//		wez.inOrder();
//		System.out.println("wezTemp words");
//		wezTemp.inOrder();
		
		assert (wez != wezTemp): "wez changed";
		
		if (present == false) {
			checkWordTextField.setText("Sorry the word either does not exist or was misspelled");
		} else {
			checkWordTextField.setText("You spelled it correctly");
		}
	}
	
	private void performRemoveWord() {
		
		assert(wez != null): "Wez tree is null";
		
		//System.out.println("before remove Word");
		//wez.inOrder();
		//System.out.println("WEZTEMP");
		//wezTemp.inOrder();
		
		String temp = removeWordText.getText().toLowerCase();
		
		WordNode tempWordNode = new WordNode(removeWordText.getText().toLowerCase());
		
		Boolean isPresent = wez.spellCheck(wez.root,tempWordNode);
		
		wez.removeWord(wez.root,temp);
		
		//System.out.println("after remove Word");
//		wez.inOrder();
//		System.out.println("WEZTEMP");
//		wezTemp.inOrder();
		
		assert (wez != wezTemp): "wez is still the same";
		
		if (isPresent != true) {
			removeWordTextField.setText("Sorry the word was not removed");
		} else {
			removeWordTextField.setText("Word was removed");
		}
	}
}
