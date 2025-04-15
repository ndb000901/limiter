package work.hello.limiter.common;

public interface Limiter {

    boolean tryAcquire();
}
