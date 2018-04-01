package priv.thinkam.thinkmall.common.util;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * @author thinkam
 * @date 2018/04/01
 */
public class Md5UtilTest {

    @Test
    public void testDigest() {
        assertTrue("5d41402abc4b2a76b9719d911017c592".equalsIgnoreCase(Md5Util.digest("hello")));
    }
}