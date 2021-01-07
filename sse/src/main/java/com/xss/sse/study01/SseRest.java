package com.xss.sse.study01;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName SseRest
 * @Description 半双工sse响应式代码
 * @Author xushaoshuai
 * @Parameters
 * @Date 2020/12/31 3:36 下午
 * @Return
 */
@RequestMapping("/sse")
@RestController
public class SseRest {

    private static Map<String, SseEmitter> sseCache = new ConcurrentHashMap<>();

    @GetMapping("/subscribe")
    public SseEmitter method(String id) {
        //设置一小时的超时时间
        SseEmitter sseEmitter = new SseEmitter(3600000L);
        sseCache.put(id,sseEmitter);
        //超时回调
        sseEmitter.onTimeout(()->sseCache.remove(id));
        //结束之后的回调
        sseEmitter.onCompletion(()-> System.out.println("已完成"));
        return sseEmitter;
    }

    @GetMapping("/push")
    public String method(String id,String content) throws IOException {
        SseEmitter sseEmitter = sseCache.get(id);
        if(sseEmitter != null){
            sseEmitter.send(content);
        }
        return "完成";
    }

    @GetMapping("/pushAll")
    public String pushAll(String content) throws IOException {
        for (String s : sseCache.keySet()) {
            SseEmitter sseEmitter = sseCache.get(s);
            if(sseEmitter != null){
                sseEmitter.send(content);
            }
        }
        return "发布完成all";
    }
}
