package top.kcaco.seckill.utils;

import cn.hutool.core.codec.Base64;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * Aes工具类
 *
 * @author kcaco
 */
public class AesUtil {

    /**
     * 编码.
     */
    private static final String ENCODING = "UTF-8";

    /**
     * 算法定义.
     */
    private static final String AES_ALGORITHM = "AES";

    /**
     * 指定填充方式.
     */
    private static final String CIPHER_CBC_PADDING = "AES/CBC/PKCS5Padding";

    /**
     * 密钥 (需要前端和后端保持一致)十六位作为密钥
     */
    private static final String AES_KEY = "ZXW0vV2gHgkAPAh0";

    /**
     * 密钥偏移量 (需要前端和后端保持一致)十六位作为密钥偏移量
     */
    private static final String IV_SEED = "d65MdZ67A9L3Rm0P";

    /**
     * AES解密-CBC模式.
     *
     * @param text 待解密文本
     * @return 解密结果
     */
    public static String decryptCbc(String text) {
        try {
            byte[] bytes = AES_KEY.getBytes(ENCODING);
            // 设置解密算法，生成秘钥.
            SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
            // 偏移.
            IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
            // "算法/模式/补码方式".
            Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
            // 选择解密.
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            // 先进行Base64解码.
            byte[] decodeBase64 = Base64.decode(text);

            // 根据待解密内容进行解密.
            byte[] decrypted = cipher.doFinal(decodeBase64);
            // 将字节数组转成字符串.
            return new String(decrypted, ENCODING);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * AES加密-CBC模式.
     *
     * @param text 待加密文本
     * @return 加密结果
     */
    public static String encryptCbc(String text) {
        try {
            byte[] bytes = AES_KEY.getBytes(ENCODING);
            // 设置加密算法，生成秘钥.
            SecretKeySpec skeySpec = new SecretKeySpec(bytes, AES_ALGORITHM);
            // "算法/模式/补码方式".
            Cipher cipher = Cipher.getInstance(CIPHER_CBC_PADDING);
            // 偏移.
            IvParameterSpec iv = new IvParameterSpec(IV_SEED.getBytes(ENCODING));
            // 选择加密.
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, iv);
            // 根据待加密内容生成字节数组.
            byte[] encrypted = cipher.doFinal(text.getBytes(ENCODING));
            // 将字节数组转成字符串.
            return Base64.encodeUrlSafe(encrypted);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
