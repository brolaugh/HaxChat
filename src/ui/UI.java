package ui;

import haxChat.Reader;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;

@SuppressWarnings("serial")
public class UI extends JFrame {
	private JLabel messages;
	private Reader reader;
	private JTextArea reply;

	public UI(String nick, String server, String channel) {
		super("HaxChat");
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setPreferredSize(new Dimension(600, 600));
		setLayout(new GridLayout(1, 2));

		// Skapar top menyn
		JMenuBar menu = new JMenuBar();
		JMenu haxChatmenu = new JMenu("HaxChat");
		setJMenuBar(menu);
		menu.add(haxChatmenu);

		// skapar haxChatmenu meny listan
		JMenuItem joinServer = new JMenuItem("Join Server");
		haxChatmenu.add(joinServer);
		JMenuItem quitChannelButton = new JMenuItem("Quit Channel");
		haxChatmenu.add(quitChannelButton);
		JMenuItem haltButton = new JMenuItem("Shutdown");
		haxChatmenu.add(haltButton);

		messages = new JLabel();

		messages.setText("<html>Connecting<br/>");
		reply = new JTextArea();

		reply.addKeyListener(new tangenter());
		JSplitPane chatDivider = new JSplitPane(JSplitPane.VERTICAL_SPLIT);
		JScrollPane messagesScroll = new JScrollPane(messages);

		add(chatDivider);

		chatDivider.setTopComponent(messagesScroll);
		chatDivider.setBottomComponent(reply);

		// Final touches
		pack();
		setVisible(true);
		reader = new Reader(messages, nick, server, channel);
		
	}

	class tangenter extends KeyAdapter {
		
		public void keyPressed(KeyEvent e) {
			if (e.getKeyCode() == 10) {
				try {
					reader.send(reply.getText());
					reply.setText("");
					reply.setCaretPosition(0);
				} catch (Exception error) {
				}

			}
		}

		public void keyTyped(KeyEvent e) {
			keyPressed(e);
		}
	}

}