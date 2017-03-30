package com.zy.shardingdb.config;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Multimap;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Stream;

/**
 * @author by zy.
 */
public class ConfigTest {
    private static final String names = "weibo_[1,3,,2,4]," +
            "ss,user,video_[3-5],232_sdf_2[33,22,2-3]";

    @Test
    public void testParseNames(){
        Map<String,String[]> multimap = AbstractConfig.parserRangeNames(names);
        Assert.assertEquals(4, multimap.get("weibo_").length);
        Assert.assertEquals(1, multimap.get("user").length);
        Assert.assertEquals(3, multimap.get("video_").length);
        Assert.assertEquals(4, multimap.get("232_sdf_2").length);
    }

    @Test
    public void testDatabaseConfig(){
        Matcher matcher = AbstractConfig.LOGIC_NAMES_PATTEN.matcher(names);
        while (matcher.find()) {
            //System.out.print(matcher.group(1));
            System.out.print("|");
            System.out.print(matcher.group(2));
            String rangePattern = matcher.group(4);
            System.out.println("|"+rangePattern);
            /*if (rangePattern != null){
                Matcher matcher1 = AbstractConfig.RANGE_ITEM_PATTEN.matcher(rangePattern);
                while (matcher1.find()){
                    //System.out.println(matcher1.group());
                    System.out.println(matcher1.group(1));
                    System.out.println(matcher1.group(2));
                }
            }*/
        }

        List<String> items = AbstractConfig.generateRangeItemArray("show","1,23,2,,3,4,abc,4-3");
        if (items != null)
            items.forEach(System.out::println);


        Multimap<String, String> multimap = LinkedListMultimap.create(10);

        String key = "key";
        multimap.put(key, "a");
        multimap.put(key, "b");
        multimap.put(key, "c");

        System.out.println(multimap.get(key));
    }
}
