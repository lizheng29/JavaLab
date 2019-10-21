package myTestProject;

import com.google.common.util.concurrent.RateLimiter;

import java.time.LocalTime;

/**
 * @description: 限流器使用
 * @author: lizheng29
 * @create: 2019-09-02 15:14
 **/
public class RateLimiterTest {

    public static void main(String[] args) {
        RateLimiter rateLimiter = RateLimiter.create(1); // 每秒往桶里放一个令牌
        for (int i = 0; i < 100; i++) {
            rateLimiter.acquire(3); // 需要3个令牌才能执行,因此需要等3秒才能执行
            System.out.println(i + "  " + LocalTime.now().toString());
        }
    }
}
