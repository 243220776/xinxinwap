package com.app.utils;

import com.app.domain.User;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;

import java.security.Key;
import org.apache.shiro.codec.Base64;
import org.apache.shiro.codec.H64;
import org.apache.shiro.codec.Hex;
import org.apache.shiro.crypto.AesCipherService;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Md5Hash;

public final class EndecryptUtils
{
  public static String encrytBase64(String password)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
    byte[] bytes = password.getBytes();
    return Base64.encodeToString(bytes);
  }
  
  public static String decryptBase64(String cipherText)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
    return Base64.decodeToString(cipherText);
  }
  
  public static String encrytHex(String password)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "不能为空");
    byte[] bytes = password.getBytes();
    return Hex.encodeToString(bytes);
  }
  
  public static String decryptHex(String cipherText)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(cipherText), "消息摘要不能为空");
    return new String(Hex.decode(cipherText));
  }
  
  public static String generateKey()
  {
    AesCipherService aesCipherService = new AesCipherService();
    Key key = aesCipherService.generateNewKey();
    return Base64.encodeToString(key.getEncoded());
  }
  
  public static String encrypt(String data)//md5 加密
  {
    String result = "";
    try
    {
      result = MD5.convert(data);
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    return result;
  }
  
  public static User md5Password(String username, String password)
  {
    Preconditions.checkArgument(!Strings.isNullOrEmpty(username), "username不能为空");
    Preconditions.checkArgument(!Strings.isNullOrEmpty(password), "password不能为空");
    SecureRandomNumberGenerator secureRandomNumberGenerator = new SecureRandomNumberGenerator();
    String salt = secureRandomNumberGenerator.nextBytes().toHex();
    
    String password_cipherText = new Md5Hash(password, username + salt, 2).toBase64();
    User user = new User();
    user.setPassword(password_cipherText);
    user.setUsername(username);
    return user;
  }
  
  public static void main(String[] args)
  {
    String password = "123";
    String cipherText = encrytHex(password);
    System.out.println(password + "hex加密之后的密文是:" + cipherText);
    String decrptPassword = decryptHex(cipherText);
    System.out.println(cipherText + "hex解密之后的密码是:" + decrptPassword);
    String cipherText_base64 = encrytBase64(password);
    System.out.println(password + "base64加密之后的密文是:" + cipherText_base64);
    String decrptPassword_base64 = decryptBase64(cipherText_base64);
    System.out.println(cipherText_base64 + "base64解密之后的密码是:" + decrptPassword_base64);
    String h64 = H64.encodeToString(password.getBytes());
    System.out.println(h64);
    String salt = "7road";
    String cipherText_md5 = new Md5Hash(password, salt, 4).toHex();
    System.out.println(password + "通过md5加密之后的密文是:" + cipherText_md5);
    System.out.println(generateKey());
    System.out.println("==========================================================");
    AesCipherService aesCipherService = new AesCipherService();
    aesCipherService.setKeySize(128);
    Key key = aesCipherService.generateNewKey();
    String aes_cipherText = aesCipherService.encrypt(password.getBytes(), key.getEncoded()).toHex();
    System.out.println(password + " aes加密的密文是:" + aes_cipherText);
    
    String aes_mingwen = new String(aesCipherService.decrypt(Hex.decode(aes_cipherText), key.getEncoded()).getBytes());
    System.out.println(aes_cipherText + " aes解密的明文是:" + aes_mingwen);
  }
}
