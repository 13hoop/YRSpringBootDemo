package com.yongren.github.config;

import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;

import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class FJosnConfig {

    public HttpMessageConverter config() {
        FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();

        FastJsonConfig config = new FastJsonConfig();
        config.setSerializerFeatures(
                SerializerFeature.PrettyFormat,
                SerializerFeature.WriteDateUseDateFormat,
                // 保留map空字段
                SerializerFeature.WriteMapNullValue,
                // String的null转为“”
                SerializerFeature.WriteNullStringAsEmpty,
                // Number的null转为0
                SerializerFeature.WriteNullNumberAsZero,
                // List的null转为[]
                SerializerFeature.WriteNullListAsEmpty,
                // Boolean的null转为false
                SerializerFeature.WriteNullBooleanAsFalse,
                // 避免循环引用
                SerializerFeature.DisableCircularReferenceDetect
        );

        converter.setFastJsonConfig(config);
        converter.setDefaultCharset(Charset.forName("UTF-8"));

        List<MediaType> mediaTypeList = new ArrayList<>();
        // 中文处理, “application/json”
        mediaTypeList.add(MediaType.APPLICATION_JSON);
        converter.setSupportedMediaTypes(mediaTypeList);

        return converter;
    }
}
