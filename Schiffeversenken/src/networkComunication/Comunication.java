package networkComunication;

public class Comunication {

	private static boolean setMsg = false;
	private static boolean answerTime = false;
	private static String incomeMsg = null;
	private static String outgoingMsg = null;
	
	
	
	public static boolean isSetMsg() {
		return setMsg;
	}

	public static void setSetMsg(boolean setMsg) {
		Comunication.setMsg = setMsg;
	}
	
	public static boolean isAnswerTime() {
		return answerTime;
	}
	
	public static void setAnswerTime(boolean thisAnswerTime) {
		answerTime = thisAnswerTime;
	}
	
	public static String getIncomeMsg() {
		return incomeMsg;
	}
	
	public static void setIncomeMsg(String thisincomeMsg) {
		incomeMsg = thisincomeMsg;
	}
	
	public static String getOutgoingMsg() {
		return outgoingMsg;
	}
	
	public static void setOutgoingMsg(String thisoutgoingMsg) {
		outgoingMsg = thisoutgoingMsg;
	}
	
}
