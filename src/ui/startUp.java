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


public class startUp extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private JTextField nickField;
	private JTextField serverField;
	private JTextField channelField;
	public startUp(){
		super("HaxChat");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(300, 200));
        setLayout(new GridLayout(4,1));
        
        JPanel nickPanel = new JPanel();
        
        nickPanel.setLayout(new GridLayout(2,1));
        JLabel nickLabel = new JLabel("Nickname");
        nickField =new JTextField();
        add(nickPanel);
        nickPanel.add(nickLabel);
        nickPanel.add(nickField);
        
        JPanel serverPanel = new JPanel();
        serverPanel.setLayout(new GridLayout(2,1));
        JLabel serverLabel = new JLabel("server");
        serverField =new JTextField();
        add(serverPanel);
        serverPanel.add(serverLabel);
        serverPanel.add(serverField);
        
        JPanel channelPanel = new JPanel();
        channelPanel.setLayout(new GridLayout(2,1));
        JLabel channelLabel = new JLabel("#channel");
        channelField =new JTextField();
        add(channelPanel);
        channelPanel.add(channelLabel);
        channelPanel.add(channelField);
        
        JButton butt = new JButton("Connect");
        butt.addActionListener(this);
        add(butt);
        
        pack();
        setVisible(true);
        
	}

	private void actionPreformed(ActionEvent e) {

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		//Startar chatfönsteret, sätt server och nickname värden och gör fönstret synlig
		this.setVisible(false);
		UI chatWindow = new UI(nickField.getText(), serverField.getText(), channelField.getText());
		
	}
}
