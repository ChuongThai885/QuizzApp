package QuizzApp.Service;

import org.apache.commons.codec.binary.Base64;

public class EncodeService {
	public String encodeString(String str)
	{
		Base64 base64 = new Base64();
		return new String(base64.encode(str.getBytes()));
	}
	public String decodeString(String str)
	{
		Base64 base64 = new Base64();
		return new String(base64.decode(str.getBytes()));
	}
}
