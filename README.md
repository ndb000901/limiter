# 该仓库实现了常用限流算法，支持单机或分布式系统


## 1. 单机

### 1.1 简单计算器示例

```java

public class SimpleCounterLimiterTest {

    @Test
    public void testLocalSimpleCounter() throws InterruptedException {
        Limiter limiter = new SimpleCounterLimiter(1, 1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }
    
}
```

### 1.2 漏桶算法示例

```java

public class LeakyBucketLimiterTest {

    @Test
    public void testLocalLeakyBucket() throws InterruptedException {
        Limiter limiter = new LeakyBucketLimiter(3, 1);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }

    }
    
}
```

### 1.3 令牌桶示例

```java
public class TokenBucketLimiterTest {

    @Test
    public void testLocalTokenBucketLimiter() throws InterruptedException {

        Limiter tokenBucketLimiter = new TokenBucketLimiter(3, 5);

        for (int i = 0; i < 15; i++) {
            System.out.println(i + ": " + tokenBucketLimiter.tryAcquire());
            Thread.sleep(100);
        }
    }
}

```

### 1.4 滑动窗口计数器示例

```java

public class SlidingWindowCounterLimiterTest {

    @Test
    public void testLocalSlidingWindowCounterLimiter() throws InterruptedException {
        Limiter limiter = new SlidingWindowCounterLimiter(100, 10, 3);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }
    
}
```

### 1.5 滑动窗口日志示例

```java

public class SlidingWindowLogLimiterTest {


    @Test
    public void testLocalSlidingWindowLogLimiter() throws InterruptedException {
        Limiter limiter = new SlidingWindowLogLimiter(3, 1000);

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }
    
}
```

## 2. 分布式系统

### 2.1 简单计算器示例

```java

public class SimpleCounterLimiterTest {
    
    @Test
    public void testRedisSimpleCounter() throws InterruptedException {

        Limiter limiter = new RedisSimpleCounterLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "simple-counter",
                3,
                10
        );
        for (int i = 0; i < 100; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(500);
        }
    }
}
```

### 2.2 漏桶算法示例

```java

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
```

### 2.3 令牌桶示例

```java
public class TokenBucketLimiterTest {
    

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

```

### 2.4 滑动窗口计数器示例

```java
public class SlidingWindowCounterLimiterTest {
    
    @Test
    public void testRedisSlidingWindowCounterLimiter() throws InterruptedException {

        Limiter limiter = new RedisSlidingWindowCounterLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "sliding-window",
                6000,
                1000
        );
        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }

    }


}

```

### 2.5 滑动窗口日志示例

```java

public class SlidingWindowLogLimiterTest {
    

    @Test
    public void testRedisSlidingWindowCounterLimiter() throws InterruptedException {
        Limiter limiter = new RedisSlidingWindowLogLimiter(
                "redis://:redis123456@192.168.43.242:6379",
                "sliding-window-log",
                3,
                1000
        );

        for (int i = 0; i < 10; i++) {
            System.out.println(i + ": " + limiter.tryAcquire());
            Thread.sleep(200);
        }
    }


}
```