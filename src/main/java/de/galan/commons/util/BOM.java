package de.galan.commons.util;

import static java.nio.charset.StandardCharsets.*;
import static org.apache.commons.lang3.StringUtils.*;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Adding and remove BOM (byteordermark) UTF-8 headers.
 */
public class BOM {

	private final static byte[] BOM_ARRAY = new byte[] {(byte)0xEF, (byte)0xBB, (byte)0xBF};
	private final static int UTF8_BOM_LENGTH = BOM_ARRAY.length;


	public static String getBOM() {
		return new String(BOM_ARRAY, UTF_8);
	}


	public static OutputStream writeBom(OutputStream out) throws IOException {
		if (out != null) {
			out.write(BOM_ARRAY);
		}
		return out;
	}


	public static String clean(String input) {
		String result = input;
		if (isNotBlank(input)) {
			byte[] bytes = input.getBytes(UTF_8);
			if (isUTF8(bytes)) {
				byte[] barray = new byte[bytes.length - UTF8_BOM_LENGTH];
				System.arraycopy(bytes, UTF8_BOM_LENGTH, barray, 0, barray.length);
				result = new String(barray, UTF_8);
			}
		}
		return result;
	}


	public static boolean isUTF8(byte[] bytes) {
		if (bytes != null && bytes.length >= UTF8_BOM_LENGTH) {
			if ((bytes[0] == BOM_ARRAY[0]) && (bytes[1] == BOM_ARRAY[1]) && (bytes[2] == BOM_ARRAY[2])) {
				return true;
			}
		}
		return false;
	}

}
