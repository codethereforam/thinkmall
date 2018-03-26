package priv.thinkam.thinkmall.service;

import com.alibaba.druid.filter.config.ConfigTools;

/**
 * @author thinkam
 * @date 2018/03/26
 */
public class DruidConfigToolsTest {
    public static void main(String[] args) throws Exception {
        String password = "GONudbdnRW4/PYHp9Lf3YxBA7qT/XomLj/25wX+6+q6FnGZ1gg9NU/lfrfhBa4fobXnw9oORxRfrT6AiZli5RQ==";
        String publicKey = "MFwwDQYJKoZIhvcNAQEBBQADSwAwSAJBAIzdPLmecOadxHzHesdZluCHjauna1ffG0fPELMM2n0vJ89qH3TUUCywU9aNo3q4DkavwuQlotQe/xzmiYoVeyECAwEAAQ==";
        System.out.println(ConfigTools.decrypt(publicKey, password));
    }
}
