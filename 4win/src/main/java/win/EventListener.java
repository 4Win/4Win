package win;

import com.pusher.client.Pusher;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;

public class EventListener {
	 public static void main(String[] args) {
		 Pusher pusher = new Pusher("141730");
		 ConnectionEventListener ce = new ConnectionEventListener() 
		 {
			public void onError(String arg0, String arg1, Exception arg2) {
				System.out.println("bla");	
			}
			public void onConnectionStateChange(ConnectionStateChange arg0) {
				System.out.println("blub");		
			}
		 };
		 
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
		 pusher.disconnect();
		 pusher.connect();
		 
}
}