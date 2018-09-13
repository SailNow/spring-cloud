package org.lg.exercise.servicefeignhystrix;

import org.springframework.stereotype.Component;

/**
 * 描述：
 *
 * @author LG
 * @create 2018-09-12 17:01
 */
@Component
public class SchedualServiceHiHystric implements SchedualServiceHi{

    @Override
    public String sayHiFromClientOne(String name) {
        return "sorry " + name;
    }
}
