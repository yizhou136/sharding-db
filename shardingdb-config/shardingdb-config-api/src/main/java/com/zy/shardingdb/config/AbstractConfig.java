package com.zy.shardingdb.config;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.*;

import java.io.Serializable;
import java.time.Instant;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/**
 * 配置文件基础类.
 *
 * @author by zy.
 */
public abstract class AbstractConfig implements Serializable{
    private static final long    serialVersionUID        = -5864351140409987595L;


    public final static Pattern LOGIC_NAMES_PATTEN = Pattern.compile("((\\w+)[_]?(\\[(.*?)\\])?)[,]*");
    public final static Pattern RANGE_ITEM_PATTEN = Pattern.compile("(\\d+)([,-])*");

    public static Map<String, String[]> parserRangeNames(String names){
        if (Strings.isNullOrEmpty(names)) return Collections.emptyMap();
        //Multimap<String, String[]> multimap = ArrayListMultimap
        Map<String, String[]> hashMap = new HashMap<>();
        Matcher matcher = LOGIC_NAMES_PATTEN.matcher(names);
        while (matcher.find()){
            String logicName = matcher.group(2);
            String rangePattern = matcher.group(4);
            List<String> actualNames = generateRangeItemArray(logicName, rangePattern);
            char lastOne = logicName.charAt(logicName.length()-1);
            if ((lastOne < 'a' || lastOne > 'z') &&
                    (lastOne < 'A' || lastOne > 'Z'))
                logicName = logicName.substring(0, logicName.length()-1);
            hashMap.put(logicName, actualNames.toArray(new String[actualNames.size()]));
        }
        return hashMap;
    }

    public static List<String> generateRangeItemArray(String prefix, String rangePattern){
        if (Strings.isNullOrEmpty(rangePattern)){
            //return Lists.newArrayList(prefix);
            return Arrays.asList(prefix);
        }
        String rangeItems[] = rangePattern.split(",");
        List<String> items = Stream.of(rangeItems)
                .collect(LinkedList<String>::new, (list, item)->{
                    if (Strings.isNullOrEmpty(item)) return;
                    if (item.contains("-")){
                        String startend[] = item.split("-");
                        int start = Integer.parseInt(startend[0]);
                        int end = Integer.parseInt(startend[1]);
                        for (int i=start; i<=end; i++){
                            String idx = String.valueOf(i);
                            String it = String.format("%s%s",Strings.nullToEmpty(prefix),idx);
                            if (!list.contains(it))
                                list.add(it);
                        }
                    }else {
                        String it = String.format("%s%s", Strings.nullToEmpty(prefix),item);
                        list.add(it);
                    }
                }, LinkedList<String>::addAll);
        return items;
    }

}
