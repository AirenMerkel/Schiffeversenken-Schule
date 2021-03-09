package conectionBroadcast;

public class Broadcast {
	private static boolean conectedBoolean = false;
	private static String conectIP = null;
	private static boolean info = true;  // if SVFound(true) or SVSearch(False)
	private static String message = messages.Messages.host;

	public static String getMessage() {
		return message;
	}

	public static void setMessage(String message) {
		Broadcast.message = message;
	}
	
	public static byte[] getMessageBytes() {
		return message.getBytes();
	}
	
	public static boolean isInfo() {
		return info;
	}

	public static void setInfo(boolean info) {
		Broadcast.info = info;
	}

	public static String getConectIP() {
		return conectIP;
	}

	public static void setConectIP(String conectIP) {
		Broadcast.conectIP = conectIP;
	}

	public static boolean isConected() {
		return conectedBoolean;
	}

	public static void setConected(boolean conected) {
		conectedBoolean = conected;
	}


	
}
