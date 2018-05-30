package com.joe.shellclient.utils.serializer;

import com.alibaba.fastjson.annotation.JSONField;
import com.alibaba.fastjson.serializer.JSONSerializable;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.ObjectSerializer;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.Date;

/**
 * @author: Joe
 * @description:
 * @date: Create in 11:14 2018/5/18
 */
public class Date2LongSerializer implements ObjectSerializer {

    @Override
    public void write(JSONSerializer serializer, Object object, Object fieldName, Type fieldType, int features) throws IOException {
        Date date= (Date) object;
        serializer.write(date.getTime()/1000);
    }
}
