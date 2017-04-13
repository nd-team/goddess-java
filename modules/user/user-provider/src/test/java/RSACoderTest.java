import com.bjike.goddess.user.utils.RSACoder;
import org.junit.Before;
import org.junit.Test;

import java.security.Key;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * @Author: [liguiqin]
 * @Date: [2017-04-13 14:51]
 * @Description: [ ]
 * @Version: [1.0.0]
 * @Copy: [com.bjike]
 */
public class RSACoderTest {
    private String publicKey;
    private String privateKey;

    @Before
    public void setUp() throws Exception {
        Map<String, Key> keyMap = RSACoder.initKey();

        publicKey = RSACoder.getPublicKey(keyMap);
        privateKey = RSACoder.getPrivateKey(keyMap);
        System.err.println("公钥: \n\r" + publicKey);
        System.err.println("私钥： \n\r" + privateKey);
    }

    @Test
    public void test() throws Exception {
        System.err.println("公钥加密——私钥解密");
        String inputStr = "abc";

        byte[] encodedData = RSACoder.encryptByPublicKey(inputStr, publicKey);

        byte[] decodedData = RSACoder.decryptByPrivateKey(encodedData,
                privateKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

    }

    @Test
    public void testSign() throws Exception {
        System.err.println("私钥加密——公钥解密");
        String inputStr = "sign";
        byte[] data = inputStr.getBytes();

        byte[] encodedData = RSACoder.encryptByPrivateKey(data, privateKey);

        byte[] decodedData = RSACoder.decryptByPublicKey(encodedData, publicKey);

        String outputStr = new String(decodedData);
        System.err.println("加密前: " + inputStr + "\n\r" + "解密后: " + outputStr);
        assertEquals(inputStr, outputStr);

        System.err.println("私钥签名——公钥验证签名");
        // 产生签名
        String sign = RSACoder.sign(encodedData, privateKey);
        System.err.println("签名:" + sign);

        // 验证签名
        boolean status = RSACoder.verify(encodedData, publicKey, sign);
        System.err.println("状态:" + status);
        assertTrue(status);
    }

    @Test
    public void testDecryptByJs() throws Exception {
        String inputStr = "j0HCfgWVehbapv58p+TiQFxVXKzS6I2mD0zP67lQCd+0C/+IYl4jYI3rCghIEy1/TiIUG5rdFO1bqs55jmab10tl0dOCFnKrRwNRHYgTlI/zing1s0m5g1EuZGIv6YSPwd/PI9wmfeie0OieWwA8bT7Cdzi/t2+pOXc3h3LIEd4=";

//        byte[] encodedData = RSACoder.encryptByPrivateKey(data, "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAJWquq1OBuNqqrWdVGcBMkrgA/TRb3wJ9H0reR67QyEm3LpKdiJ1SGmfzkED92gbDTUMXIBSzSIp7RHrxQaALMijvH+ih/QIXzXneFpdHRCv5UD6eApfj+JWRGAQxUirV6GWz1I4b/vQRY6ac3xSpC0l/aQR8+zpeS/dmj0Xzxi3AgMBAAECgYBvJTu+icxpxIQDnVPZrrTHj/bAL2xIRChRcCBhZxWiH/cZTUTPsCNXdTq5Y5WutTAorgZfsZ3u+g25GkrV2gfm0Ar5lve8/lhZM09gfLNRugO0F+LdP0U4/b0X/I3fd0jmb+uxKC2thX8/kX0j5Vz0RusE91xC5pmUjI81hRUHuQJBANk9/RoPCMMbJaoswPu5syZSfAr4fi5iU6UP0ubm+rfyHLUYijGsfwo5FhaLYJf2svY7wT31tHr/8vzC4YGd+G0CQQCwXmeXcM2SccJ3b8Hx/2gtOeKGe2xOHbqGMDlPZOR/PNGIG9zfYKB6TOPQ2g/hiMJL/zKNYEVooz+yaYyTticzAkA5q8EjR9Z1pSFMmfDpYpTymXcgmnM08RlvelHsxmjMqFOCp+8X3Mkk956rgXi1HuLHh3l3b2DoPkZNX5bqB/vJAkB7Fvf/hpfCjCinyNS3+4QOhGanav5UqoTiYEevNIAQmTa3YpLNR5fYo08gFQCebB8GgS4b40Ot2rl64BVfhfJnAkA9y8CjT4VcdbXdA5iW9hHTmS/a2HcgEalTpOQ2Uzat+fOAiZusCuBxRNWAMVBVDKsMbhtlnNu10OoDbSt/ZCGI");


        byte[] decodedData = RSACoder.decryptByPrivateKey(inputStr, "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAJvVuxOMx+jF67jLPAGmt632k5VFqLMnaL25mvD4XekPMwMsLooAQHSkeRQ/Ngx0ZeSeXIihSG/Q1QNpuQRFARk0sa0aHKycMsHZDLjiH0vCSGm/IXY2bdsHPZYClWXSXS3iCFFQ+/N0vK0kHkEpV3y1gkMqArdP254PEr81/1h/AgMBAAECgYEAiPINogOQ1o3ANjhb9nOFbVqInaCGvVH/qONIN0V8eciCouIbz+iivB6hzNoz7EQKGxvT47oghyr1Vzd8gTUG3bL+IiyhnNODBkVoDE1ZiXpWdOC4mwnR0YZt6SazxC+V5exfpwcCFVhAJHhMkbp94SCHt1q42+j37xdi4vVqyRECQQDyzKG2u1QoVn+8eSJJegpXJEymXiULLLxarDWhnNcqLR2d68IaXHLdGHXIY4IQLzKdNR1oexEUFySAJLuvtl9ZAkEApE6yUzlDV8FihKHXFjeRQogv5M7N0VnK2ppG9s6p7OcDLKO1GFR6cYYIFqCoozK4FG4zbmYZqV4/sk/ZzXWTlwJAJqIg6S6NiY2Z7adoqUBd/NetypsYUdrgbZ0RO5H1OqTLHaGx1Pf43u1RcH1E6MZZy2H0N/e9E/FKwYkWxDLEoQJAF0IgkhFQDBMO3BGAvd9dy5o1lWEE13ardWhTVUqJ2UQWOfJuhyQmNP2xFWrmlDtwvUugMid3yHbT82zEOwZCvwJBAN5id4ExB+nVoMSJxlyNXDwF26B6IOyeZLGTkAqyp7heWpFWRT65iQ29uTfrL7IaDEUrz4z68tBKSqp/h51P3qc=");

        String outputStr = new String(decodedData);
        System.err.println("解密: " + outputStr);
    }
}
