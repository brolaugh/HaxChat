package haxChat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

import javax.swing.JLabel;

public class Reader extends Thread {
	public String getNick() {
		return nick;
	}

	public void setNick(String nick) {
		this.nick = nick;
		this.login = nick;
	}

	public String getServer() {
		return server;
	}

	public void setServer(String server) {
		this.server = server;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}
	
	private JLabel label;
	private String nick;
	private String server;
	private String login;
	private String channel;
	private Socket socket;
	private BufferedWriter writer = null;
	private BufferedReader reader;
	
	public Reader(JLabel label) {
		this.label = label;
			if(nick.isEmpty()){
				nick = "haxChat_client";
				login = nick;
				
			}
			if(server.isEmpty()){
				server = "irc.freenode.net";
			}
			if(channel.isEmpty()){
				channel = "#itg";
			}
		try {
			// Connect directly to the IRC server.
			socket = new Socket(server, 6667);
			writer = new BufferedWriter(new OutputStreamWriter(
					socket.getOutputStream()));
			reader = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));

			// Log on to the server.
			writer.write("NICK " + nick + "\r\n");
			writer.write("USER " + login + " 8 * : Java IRC Hacks Bot\r\n");
			writer.flush();

			// Read lines from the server until it tells us we have connected.
			String line = null;
			while ((line = reader.readLine()) != null) {
				if (line.indexOf("004") >= 0) {
					// We are now logged in.
					break;
				} else if (line.indexOf("433") >= 0) {
					System.out.println("Nickname is already in use.");
					return;
				}
			}

			// Join the channel.
			writer.write("JOIN " + channel + "\r\n");
			writer.flush();

		} catch (Exception error) {
		}

		start();

	}

	public void run() {

		String line = "";
		while (true) {
			try {

				// Keep reading lines from the server.
				while ((line = reader.readLine()) != null) {
					if (line.toLowerCase().startsWith("PING ")) {
						// We must respond to PINGs to avoid being disconnected.
						writer.write("PONG " + line.substring(5) + "\r\n");
						writer.write("PRIVMSG " + channel
								+ " :I got pinged!\r\n");
						writer.flush();
					} else {
						// Print the raw line received by the bot.
						// System.out.println(line);
						label.setText(label.getText() + line + "<br/>");
					}
				}
			} catch (Exception error) {
				System.out.println("fel: " + error.toString());
			}
		}

	}

	public void send(String message) {
		try {
			writer.write("PRIVMSG " + channel + " : " + message + "\r\n");
			writer.flush();
			label.setText(label.getText()+ ":"+nick+"!~"+nick+"@127.0.0.1 PRIVMSG "+ channel + " :"+ message + "<br/>");
		} catch (Exception error) {
			System.out.println(error);

		}
	}

}
