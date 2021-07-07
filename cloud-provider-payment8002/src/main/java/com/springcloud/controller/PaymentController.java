package com.springcloud.controller;

import com.springcloud.entities.CommonResult;
import com.springcloud.entities.Payment;
import com.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;


@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

    @PostMapping("/payment/create")
    public CommonResult create(@RequestBody Payment payment){
        int result = paymentService.create(payment);

        log.info("============插入数据成功,返回结果：" + result);

        if (result > 0){
            return new CommonResult(200,"插入数据成功,serverPort: " + serverPort,result);
        }else {
            return new CommonResult(444,"插入数据失败,serverPort: " + serverPort,null);
        }
    }

    @GetMapping("/payment/getPaymentById/{id}")
    public CommonResult getPaymentById(@PathVariable("id") Long id){
        Payment payments = paymentService.getPaymentById(id);

        log.info("============查询数据成功",payments);

        if (payments != null){
            return new CommonResult(200,"查询数据成功,serverPort: " + serverPort,payments);
        }else {
            return new CommonResult(200,"查询数据失败,serverPort: " + serverPort,null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String element : services) {
            log.info("****element: " +element);
        }
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        for (ServiceInstance instance:instances) {
            log.info(instance.getServiceId()+"\t"+instance.getHost()+"\t"+instance.getPort()+"\t"+instance.getUri());
        }
        return this.discoveryClient;
    }

    @GetMapping(value = "/payment/lb")
    public String getPaymentLb(){
        return serverPort;
    }

    @GetMapping("/payment/zipkin")
    public String paymentZipkin(){
        return "hi,i'am paymentzipkin server fall back, welcome to here";
    }
}
