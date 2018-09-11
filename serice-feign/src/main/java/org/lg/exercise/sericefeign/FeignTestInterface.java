package org.lg.exercise.sericefeign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 描述：
 *
 * @author LG
 * @create 2018-07-09 15:14
 */
@FeignClient(value = "service-hi", fallback = FeignTestServiceImpl.class)
public interface FeignTestInterface {
    @RequestMapping(value = "/hi",method = RequestMethod.GET)
    String sayHiFromClientOne(@RequestParam(value = "name") String name);
}
