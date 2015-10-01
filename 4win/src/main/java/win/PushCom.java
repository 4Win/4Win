package win;

import com.google.gson.Gson;
import com.pusher.client.AuthorizationFailureException;
import com.pusher.client.Authorizer;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PrivateChannel;
import com.pusher.client.channel.PrivateChannelEventListener;
import com.pusher.client.channel.SubscriptionEventListener;
import com.pusher.client.connection.ConnectionEventListener;
import com.pusher.client.connection.ConnectionState;
import com.pusher.client.connection.ConnectionStateChange;
import com.pusher.client.util.HttpAuthorizer;

public class PushCom {

	public PushCom(String players) {
		System.out.println("Erfolgreich PushCommunicator erstellt");
		System.out.println("Du bist: " + players);
		final String appKey = "6c99188c47107bea4df2";
		final String appSecret = "ec1b8a822123fdeb5579";

		PusherOptions options = new PusherOptions();

		options.setAuthorizer(new Authorizer() {
			/**
			 * The user (the client) can be authorized, if he knows the key and
			 * the secret. And the user is to be authorized on a decided
			 * channel.
			 * 
			 * "Given that your application - d. h. dieser Nutzer hier -
			 * receives a POST request to /pusher/auth with the parameters:
			 * channel_name=private-foobar&socket_id=1234.1234
			 * 
			 * The auth response should be a JSON string with a an auth property
			 * with a value composed of the application key and the
			 * authentication signature separated by a colon ‘:’ as follows:
			 */
			public String authorize(String channelName, String socketId)
					throws AuthorizationFailureException {
				System.out.println("The clients channelName: " + channelName);
				System.out.println("The clients socketId: " + socketId);

				// String to sign ::= <webSocketId>:<channelName>
				String message = socketId + ":" + channelName;

				// calc the hash, e. g. one part of the authentication signature
				String hash = HmacSHA256.getHmacSHA256HexDigest(appSecret,
						message);

				// compose the entire authentication signature
				// <signature> ::= "{"auth":"<appId>:<hash>"}"
				String signature = "{\"auth\":\"" + appKey + ":" + hash + "\"}";

				return signature; // ... wer auch immer das dann auswertet.
			}
		});

		Pusher pusher = new Pusher(appKey, options);
		ConnectionEventListener ce = new ConnectionEventListener() {
			public void onError(String arg0, String arg1, Exception arg2) {
			}

			public void onConnectionStateChange(ConnectionStateChange arg0) {
				System.out.println(arg0.getCurrentState().toString());
			}
		};

		System.out.println("Stelle Verbindung her");
		pusher.connect(ce, ConnectionState.ALL);

		Channel channel = pusher.subscribe("my_channel");

		SubscriptionEventListener sb = new SubscriptionEventListener() {
			public void onEvent(String arg0, String arg1, String arg2) {
				System.out.println("Received event with data: " + arg2);
			}
		};
		channel.bind("new_message", sb);
		PrivateChannel channel2 = null;
		channel2 = pusher.subscribePrivate("private-channel",
			    new PrivateChannelEventListener() {
			        public void onSubscriptionSucceeded(String channelName) {
			            this.trigger("client-myEvent", "{\"myName\":\"Bob\"}");
			        }

					public void onEvent(String channelName, String eventName,
							String data) {
						// TODO Auto-generated method stub
						
					}

					public void onAuthenticationFailure(String message,
							Exception e) {
						// TODO Auto-generated method stub
						
					}

			        // Other PrivateChannelEventListener methods
			    });
	}
}
