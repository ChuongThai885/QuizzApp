package QuizzApp.Service;

import java.io.UnsupportedEncodingException;

import org.apache.axis.encoding.Base64;

public class EncodeService {
	public String encodeString(String text) throws UnsupportedEncodingException {
		byte[] bytes = text.getBytes("UTF-8");
		String encodeString = Base64.encode(bytes);
		return encodeString;
	}

	public String decodeString(String encodeText) throws UnsupportedEncodingException {
		byte[] decodeBytes = Base64.decode(encodeText);
		String str = new String(decodeBytes, "UTF-8");
		return str;
	}
}
