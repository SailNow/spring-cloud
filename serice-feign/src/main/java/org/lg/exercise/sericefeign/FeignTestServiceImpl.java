package org.lg.exercise.sericefeign;

import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author LG
 * @create 2018-07-09 17:28
 */
@Component
public class FeignTestServiceImpl implements FeignTestInterface{

    @Override
    public String sayHiFromClientOne(String name) {
        return "test success, server is error! name=" + name;
    }
}
