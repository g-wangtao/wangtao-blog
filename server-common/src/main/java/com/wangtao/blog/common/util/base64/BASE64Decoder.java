package com.wangtao.blog.common.util.base64;

import java.io.OutputStream;
import java.io.PushbackInputStream;

/**
 * @ClassName:BASE64Decoder
 * @author: KarlWang
 * @Description: TODO(字符串解密工具类) 
 * @date:2017年4月10日 下午2:57:16
 * @see com.wangtao.blog.common.util.base64.BASE64Decoder
 */
public class BASE64Decoder extends CharacterDecoder {

	/**
	 * This character array provides the character to value map based on
	 * RFC1521.
	 */
	private static final char PEM_ARRAY[] = {
		// 0 1 2 3 4 5 6 7
		'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', // 0
		'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', // 1
		'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', // 2
		'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', // 3
		'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', // 4
		'o', 'p', 'q', 'r', 's', 't', 'u', 'v', // 5
		'w', 'x', 'y', 'z', '0', '1', '2', '3', // 6
		'4', '5', '6', '7', '8', '9', '+', '/' // 7
	};

	private static final byte PEM_CONVERT_ARRAY[] = new byte[256];

	static {
		for (int i = 0; i < 255; i++) {
			PEM_CONVERT_ARRAY[i] = -1;
		}
		for (int i = 0; i < PEM_ARRAY.length; i++) {
			PEM_CONVERT_ARRAY[PEM_ARRAY[i]] = (byte) i;
		}
	}

	byte decodeBuffer[] = new byte[4];

	/** This class has 4 bytes per atom */
	protected int bytesPerAtom() {
		return (4);
	}

	/** Any multiple of 4 will do, 72 might be common */
	protected int bytesPerLine() {
		return (72);
	}

	/**
	 * Decode one BASE64 atom into 1, 2, or 3 bytes of data.
	 */
	protected void decodeAtom(PushbackInputStream inStream, OutputStream outStream, int rem) throws java.io.IOException {
		int i, remI = rem;
		byte a = -1, b = -1, c = -1, d = -1;

		if (rem < 2) {
			throw new CEFormatException("BASE64Decoder: Not enough bytes for an atom.");
		}
		do {
			i = inStream.read();
			if (i == -1) {
				throw new CEStreamExhausted();
			}
		} while (i == '\n' || i == '\r');
		decodeBuffer[0] = (byte) i;

		i = readFully(inStream, decodeBuffer, 1, rem - 1);
		if (i == -1) {
			throw new CEStreamExhausted();
		}

		if (rem > 3 && decodeBuffer[3] == '=') {
			// rem = 3;
			remI = 3;
		}
		if (rem > 2 && decodeBuffer[2] == '=') {
			// rem = 2;
			remI = 2;
		}
		switch (remI) {
		case 4:
			d = PEM_CONVERT_ARRAY[decodeBuffer[3] & 0xff];
			c = PEM_CONVERT_ARRAY[decodeBuffer[2] & 0xff];
			b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
			a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
			// NOBREAK
			break;
		case 3:
			c = PEM_CONVERT_ARRAY[decodeBuffer[2] & 0xff];
			b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
			a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
			// NOBREAK
			break;
		case 2:
			b = PEM_CONVERT_ARRAY[decodeBuffer[1] & 0xff];
			a = PEM_CONVERT_ARRAY[decodeBuffer[0] & 0xff];
			break;
		default:
			break;
		}

		switch (remI) {
		case 2:
			outStream.write((byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)));
			break;
		case 3:
			outStream.write((byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)));
			outStream.write((byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)));
			break;
		case 4:
			outStream.write((byte) (((a << 2) & 0xfc) | ((b >>> 4) & 3)));
			outStream.write((byte) (((b << 4) & 0xf0) | ((c >>> 2) & 0xf)));
			outStream.write((byte) (((c << 6) & 0xc0) | (d & 0x3f)));
			break;
		default:
			break;
		}
	}

}
