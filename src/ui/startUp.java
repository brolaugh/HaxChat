package ui;

import haxChat.Reader;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public abstract class startUp extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField nickField;
	private JTextField serverField;
	private JTextField channelField;
	public startUp(){
		super("HaxChat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 600));
        setLayout(new GridLayout(1,4));
        
        JPanel nickPanel = new JPanel();
        
        nickPanel.setLayout(new GridLayout(2,1));
        JLabel nickLabel = new JLabel();
        nickField =new JTextField();
        add(nickPanel);
        nickPanel.add(nickLabel);
        nickPanel.add(nickField);
        
        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new GridLayout(2,1));
        JLabel serverLabel = new JLabel();
        serverField =new JTextField();
        add(serverPanel);
        serverPanel.add(serverLabel);
        serverPanel.add(serverField);
        
        JPanel channelPanel = new JPanel();
        channelPanel.setLayout(new GridLayout(2,1));
        JLabel channelLabel = new JLabel();
        channelField =new JTextField();
        add(channelPanel);
        channelPanel.add(channelLabel);
        channelPanel.add(channelField);
        
        JButton butt = new JButton("Connect");
        butt.addActionListener(this);
        
        
        pack();
        setVisible(true);
        
	}

	private void actionPreformed(ActionEvent e) {
		//Startar chatfönsteret, sätt server och nickname värden och gör fönstret synligt
		Reader.setNick(nickField.getText());
		Reader.setServer(serverField.getText());
		Reader.setChannel(channelField.getText());
		this.setVisible(false);
		UI chatWindow = new UI();
	}
}
