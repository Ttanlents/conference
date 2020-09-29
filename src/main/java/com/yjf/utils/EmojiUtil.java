package com.yjf.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author 余俊锋
 * @date 2020/9/27 18:55
 * @Description
 */
public class EmojiUtil {



        public static boolean hasEmoji(String content){
            Pattern pattern = Pattern.compile("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]");
            Matcher matcher = pattern.matcher(content);
            if(matcher .find()){
                return true;
            }
            return false;
        }

        public static String replaceEmoji(String str){
            if(!hasEmoji(str)){
                return str;
            }else{
                str=str.replaceAll("[\ud83c\udc00-\ud83c\udfff]|[\ud83d\udc00-\ud83d\udfff]|[\u2600-\u27ff]", " ");
                return str;
            }

        }


}
