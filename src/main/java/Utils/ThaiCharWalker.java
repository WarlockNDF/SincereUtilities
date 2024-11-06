package Utils;

import java.time.LocalDate;
import java.util.Map;

public class ThaiCharWalker {

    private static final Map<Character, Character> ThaiNumberChars = Map.of(
            '0', '๐',
            '1', '๑',
            '2', '๒',
            '3', '๓',
            '4', '๔',
            '5', '๕',
            '6', '๖',
            '7', '๗',
            '8', '๘',
            '9', '๙'
    );

    private static final String currentThaiYearAsThaiStr = toThaiNumberString(LocalDate.now().getYear());

    public static String toThaiNumberString(long number)
    {
        String numberString = String.valueOf(number);
        if (numberString.length() > 5)
        {
            int avgLen = numberString.length() / 2;
            return fastInterLoopSlideChars(avgLen, numberString, ThaiNumberChars);
        }
        return normTransformChars(numberString, ThaiNumberChars);
    }

    private static String fastInterLoopSlideChars(int middle, final String ref, Map<Character, Character> refCharMap)
    {
        middle = middle - 1;
        char[] chars = ref.toCharArray();
        int charsLen = chars.length - 1;
        for (int i = 0; i <= charsLen; i++)
        {
            int rightCompute = charsLen - i;
            if (rightCompute > middle)
            {
                chars[rightCompute] = refCharMap.getOrDefault(chars[rightCompute], chars[rightCompute]);
            }
            if (i <= middle)
            {
                chars[i] = refCharMap.getOrDefault(chars[i], chars[i]);
            }
        }
        return String.valueOf(chars);
    }

    private static String normTransformChars(String ref, Map<Character, Character> refCharMap)
    {
        char[] chars = ref.toCharArray();
        for (int i = 0; i < chars.length; i++)
        {
            chars[i] = refCharMap.getOrDefault(chars[i], chars[i]);
        }
        return String.valueOf(chars);
    }

    public static void main(String[] args)
    {
        System.out.println("start");
        System.out.println(currentThaiYearAsThaiStr);
    }

}
