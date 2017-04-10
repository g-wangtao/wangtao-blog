package com.wangtao.blog.common.util.base64;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PushbackInputStream;
import java.nio.ByteBuffer;

/**
 * 系统名称：双至开发平台-SZSP
 * 模块名称：
 * 模块描述：字符加密类
 * 功能列表：
 * 模块作者：zouyong-ocean
 * 开发时间：2015年9月17日 下午6:59:37
 * 模块路径：com.paireach.szsp.framework.base.shared.encypt.base64.CharacterDecoder
 * 更新记录：
 */
public abstract class CharacterDecoder {

	/** Return the number of bytes per atom of decoding */
	protected abstract int bytesPerAtom();

	/** Return the maximum number of bytes that can be encoded per line */
	protected abstract int bytesPerLine();

	/** decode the beginning of the buffer, by default this is a NOP. */
	protected void decodeBufferPrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
	}

	/** decode the buffer suffix, again by default it is a NOP. */
	protected void decodeBufferSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
	}

	/**
	 * This method should return, if it knows, the number of bytes that will be
	 * decoded. Many formats such as uuencoding provide this information. By
	 * default we return the maximum bytes that could have been encoded on the
	 * line.
	 */
	protected int decodeLinePrefix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
		return (bytesPerLine());
	}

	/**
	 * This method post processes the line, if there are error detection or
	 * correction codes in a line, they are generally processed by this method.
	 * The simplest version of this method looks for the (newline) character.
	 */
	protected void decodeLineSuffix(PushbackInputStream aStream, OutputStream bStream) throws IOException {
	}

	/**
	 * This method does an actual decode. It takes the decoded bytes and writes
	 * them to the OutputStream. The integer <i>l</i> tells the method how many
	 * bytes are required. This is always <= bytesPerAtom().
	 */
	protected void decodeAtom(PushbackInputStream aStream, OutputStream bStream, int l) throws IOException {
		throw new CEStreamExhausted();
	}

	/**
	 * This method works around the bizarre semantics of BufferedInputStream's
	 * read method.
	 */
	protected int readFully(InputStream in, byte buffer[], int offset, int len) throws java.io.IOException {
		for (int i = 0; i < len; i++) {
			int q = in.read();
			if (q == -1) {
				return ((i == 0) ? -1 : i);
			}
			buffer[i + offset] = (byte) q;
		}
		return len;
	}

	/**
	 * Decode the text from the InputStream and write the decoded octets to the
	 * OutputStream. This method runs until the stream is exhausted.
	 * 
	 * @exception CEFormatException
	 *                An error has occured while decoding
	 * @exception CEStreamExhausted
	 *                The input stream is unexpectedly out of data
	 */
	public void decodeBuffer(InputStream aStream, OutputStream bStream) throws IOException {
		int i;

		PushbackInputStream ps = new PushbackInputStream(aStream);
		decodeBufferPrefix(ps, bStream);
		while (true) {
			int length;

			try {
				length = decodeLinePrefix(ps, bStream);
				for (i = 0; (i + bytesPerAtom()) < length; i += bytesPerAtom()) {
					decodeAtom(ps, bStream, bytesPerAtom());
				}
				if ((i + bytesPerAtom()) == length) {
					decodeAtom(ps, bStream, bytesPerAtom());
				} else {
					decodeAtom(ps, bStream, length - i);
				}
				decodeLineSuffix(ps, bStream);
			} catch (CEStreamExhausted e) {
				break;
			}
		}
		decodeBufferSuffix(ps, bStream);
	}

	/**
	 * Alternate decode interface that takes a String containing the encoded
	 * buffer and returns a byte array containing the data.
	 * 
	 * @exception CEFormatException
	 *                An error has occured while decoding
	 */

	public byte decodeBuffer(String inputString)[] throws IOException {
		byte inputBuffer[] = null;// new byte[inputString.length()];
		ByteArrayInputStream inStream;
		ByteArrayOutputStream outStream;

		// inputString.getBytes(0, inputString.length(), inputBuffer, 0);
		inputBuffer = inputString.getBytes();
		inStream = new ByteArrayInputStream(inputBuffer);
		outStream = new ByteArrayOutputStream();
		decodeBuffer(inStream, outStream);
		return (outStream.toByteArray());
	}

	/**
	 * Decode the contents of the inputstream into a buffer.
	 */
	public byte decodeBuffer(InputStream in)[] throws IOException {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		decodeBuffer(in, outStream);
		return (outStream.toByteArray());
	}

	/**
	 * Decode the contents of the String into a ByteBuffer.
	 */
	public ByteBuffer decodeBufferToByteBuffer(String inputString) throws IOException {
		return ByteBuffer.wrap(decodeBuffer(inputString));
	}

	/**
	 * Decode the contents of the inputStream into a ByteBuffer.
	 */
	public ByteBuffer decodeBufferToByteBuffer(InputStream in) throws IOException {
		return ByteBuffer.wrap(decodeBuffer(in));
	}
}
