package com.dz.util;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * AES 是一种可逆加密算法，对用户的敏感信息加密处理 对原始数据进行AES加密后，在进行Base64编码转化�? 正确
 */
public class AES {
	/*
	 * 已确�? 加密用的Key 可以�?26个字母和数字组成 此处使用AES-128-CBC加密模式，key�?要为16位�??
	 */
	private static AES instance = null;

	public static AES getInstance() {
		if (instance == null)
			instance = new AES();
		return instance;
	}

	// 加密
	public String encrypt(String sSrc, String encodingFormat, String sKey,
			String ivParameter) throws Exception {
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		byte[] raw = sKey.getBytes();
		SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
		IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());// 使用CBC模式，需要一个向量iv，可增加加密算法的强�?
		cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
		byte[] encrypted = cipher.doFinal(sSrc.getBytes(encodingFormat));
		return new BASE64Encoder().encode(encrypted);// 此处使用BASE64做转码�??
	}

	// 解密
	public String decrypt(String sSrc, String encodingFormat, String sKey,
			String ivParameter) throws Exception {
		try {
			byte[] raw = sKey.getBytes("ASCII");
			SecretKeySpec skeySpec = new SecretKeySpec(raw, "AES");
			Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
			IvParameterSpec iv = new IvParameterSpec(ivParameter.getBytes());
			cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);
			byte[] encrypted1 = new BASE64Decoder().decodeBuffer(sSrc);// 先用base64解密
			byte[] original = cipher.doFinal(encrypted1);
			String originalString = new String(original, encodingFormat);
			return originalString;
		} catch (Exception ex) {
			return null;
		}
	}

	public static void main(String[] args) throws Exception {
		String sKey = "4deqn5g6cj08v0dq";
		String ivParameter = "1234567890123456";
		// �?要加密的字串
		String cSrc = "{\"trade_order_id\":\"000000000000000000S1801303032489\",\"card_no\":\"1000113200023854226\",\"card_pwd\":\"45803088614827028174\",\"amount\":\"100\"}";
		System.out.println("加密前的字串是：" + cSrc);
		// 加密
		String enString = AES.getInstance().encrypt(cSrc, "utf-8", sKey,
				ivParameter);
		System.out.println("加密后的字串是：" + enString);

		// 解密
		String DeString = AES.getInstance().decrypt(enString, "utf-8", sKey,
				ivParameter);
		System.out.println("解密后的字串是：" + DeString);
	}
}