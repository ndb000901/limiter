
import org.junit.jupiter.api.Test;
import work.hello.limiter.common.Limiter;
import work.hello.limiter.local.TokenBucketLimiter;
import work.hello.limiter.redis.RedisTokenBucketLimiter;

public class TokenBucketLimiterTest {

    @Test
    public void testLocalTokenBucketLimiter() throws InterruptedException {

        Limiter tokenBucketLimiter = new TokenBucketLimiter(3, 5);

        for (int i = 0; i < 15; i++) {
            System.out.println(i + ": " + tokenBucketLimiter.tryAcquire());
            Thread.sleep(100);
        }
    }

    @Test
    public void testRedisTokenBucketLimiter() throws InterruptedException {
        Limiter limiter = new RedisTokenBucketLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "token-bucket",
                5,
                3,
                1
        );

        for (int i = 0; i < 1000; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(100);
        }
    }
}
