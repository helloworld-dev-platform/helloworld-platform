import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BcrypotGeneratorTest {

  @Test
  void generateBcryptHashForPassword0000() {
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String rawPassword = "0000";

    // 비밀번호 "0000"을 해시합니다.
    String hashedPassword = encoder.encode(rawPassword);

    System.out.println("----------------------------------------");
    System.out.println("비밀번호: " + rawPassword);
    System.out.println("BCrypt 해시값: " + hashedPassword);
    System.out.println("----------------------------------------");

    // 이 해시값을 data.sql에 복사하여 사용하세요!
  }

}
