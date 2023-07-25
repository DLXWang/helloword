package com.test.schedule;

import com.test.api.CommonApi;
import com.test.api.ServiceApi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;


@Component
@Slf4j
public class Task {

    @Value("${hedge.alarm.threshold}")
    String threshold;

    @Resource
    ServiceApi serviceApi;

    @Autowired
    private CommonApi commonApi;

    private String a = threshold + "o";
    @Scheduled(cron= "${spring.task.scheduling.cron.test}") // 每1秒执行一次
    // 每分钟的第0秒执行
    public void myTask() {
        // 定时任务的逻辑代码
        System.out.println("定时任务执行了！");
        String hello = commonApi.hello(
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4IiwiaWF0IjoxNjkwMjQyNTAwLCJleHAiOjE2OTAyNDI1NjB9.F_BJ2tzi4Q5fj_QTOEbghuBGWXbsZYMlbJZABO4nUEQ",
                "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhbGV4IiwiaWF0IjoxNjkwMjQyNTAwLCJleHAiOjE2OTAyNDI2ODB9.u45QeVDNUqNLulerp1GhpJ6DqRTAPmaE5t_B7bqic9o"
        );
        System.out.println(hello);
    }
}
