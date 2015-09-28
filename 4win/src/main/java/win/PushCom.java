package win;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class PushCom {
	public PushCom(String players) {
		System.out.println("Erfolgreich PushCommunicator erstellt");
		System.out.println("Du bist: " + players);
		 Pusher pusher = new Pusher("cd535d957f2aa0f1721d");
		 
		 ConnectionEventListener ce = new ConnectionEventListener() 
		 {
			public void onError(String arg0, String arg1, Exception arg2) {
			}
			public void onConnectionStateChange(ConnectionStateChange arg0) {
				System.out.println(arg0.getCurrentState().toString());
			}
		 };
		 
		 
		 System.out.println("Stelle Verbindung her");
		 pusher.connect(ce, ConnectionState.ALL);
		 
		 Channel channel = pusher.subscribe("test_channel");
		 
		 SubscriptionEventListener sb = new SubscriptionEventListener() 
		 {
			public void onEvent(String arg0, String arg1, String arg2) 
			{
				System.out.println("Received event with data: " + arg2);
			}
		 };
		 channel.bind("my_event", sb);
	}
	
	public static void main(String[] args) {
		PushCom p =  new PushCom("X");
	}
}
