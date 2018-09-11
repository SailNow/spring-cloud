package org.lg.exercise.sericefeign;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 描述：
 *
 * @author LG
 * @create 2018-07-09 15:19
 */
@RestController
public class FeignTestController {

    @Autowired
    FeignTestInterface feignTestInterface;

    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    public String sayHi(@RequestParam String name){
        return feignTestInterface.sayHiFromClientOne(name);
    }
}
