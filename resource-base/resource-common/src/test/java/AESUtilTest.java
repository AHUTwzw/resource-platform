import com.resource.common.utils.AESUtil;
import org.junit.jupiter.api.Test;

import java.security.NoSuchAlgorithmException;

public class AESUtilTest {
    @Test
    public void generateKey() {
        try {
            String key = AESUtil.generateKey();
            System.out.println("Generated AES Key: " + key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
