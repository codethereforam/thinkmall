package priv.thinkam.thinkmall.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * MD5工具类
 *
 * @author thinkam
 * @date 2018/03/19
 */
public class Md5Util {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5Util.class);

    public static String digest(String string) {
        try {
            MessageDigest md5 = MessageDigest.getInstance("MD5");
            md5.update(StandardCharsets.UTF_8.encode(string));
            return String.format("%032x", new BigInteger(1, md5.digest()));
        } catch (NoSuchAlgorithmException e) {
            LOGGER.error("md5工具计算出错", e);
            return null;
        }
    }

}
