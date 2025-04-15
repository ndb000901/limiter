
import org.junit.jupiter.api.Test;
import work.hello.limiter.common.Limiter;
import work.hello.limiter.local.LeakyBucketLimiter;
import work.hello.limiter.redis.RedisLeakyBucketLimiter;

public class LeakyBucketLimiterTest {

    @Test
    public void testLocalLeakyBucket() throws InterruptedException {
        Limiter limiter = new LeakyBucketLimiter(3, 1);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }

    }

    @Test
    public void testRedisLeakyBucket() throws InterruptedException {

        Limiter limiter = new RedisLeakyBucketLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "leaky-bucket",
                1,
                3

        );
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }
}
