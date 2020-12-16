import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.regex.Pattern;
import java.util.regex.Matcher;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = testApp.class)
public class testApp {
    @Test
    public void test() {
        // [821088229800, 원희용, anmax, 서울특별시 강남구 역삼동 선릉로 433, null, null, null]
        String msg = "#{고객명} 고객님! #{택배회사명}입니다.\\n#{시간} 택배를 배달할 예정입니다.\\n등기번호(운송장번호) : #{운송장번호}";

        String var1 = "황수영";
        String var2 = "대한통운";
        String var3 = "오늘 11시부터 13시 사이에";
        String var4 = "123123123";

        String varList = "1,3,6";
//        varList = varList.split(",");

        for(String var:varList.split(",")) {
            System.out.println(var);
            System.out.println(msg);
            msg=msg.replaceFirst("#\\{(.|\n)*?\\}", var);
        }
        System.out.println(msg);

    }
//        String originTxt = msg;
//        String targtTxt = "HELLO WORLD TEST! <테스트 문자열1> 안녕 < 테스트문자열2 > BYE WORLD";
//        String newTxt = originTxt;
//        String reg = "#\{(?:.*?)\}";
//
//        Pattern pattern = Pattern.compile(reg);
//        Matcher matcher = pattern.matcher(originTxt);
//
//        while (matcher.find()) {
//            System.out.println("Full match: " + matcher.group(0));
//            System.out.println("Start index: " + matcher.start(0));
//            System.out.println("End index: " + matcher.end(0));
//
//            String matchStr = matcher.group(0);
//            String before = newTxt.substring(0, matcher.start(0));
//            String after = newTxt.substring(matcher.end(0));
//            matchStr = matchStr.replaceAll("&lt;", "<");
//            matchStr = matchStr.replaceAll("&gt;", ">");
//            matchStr = matchStr.replaceAll("TEST", "테스트");
//            newTxt = before + matchStr + after;
//            matcher = pattern.matcher(newTxt);
//
//            System.out.println("Change: " + newTxt);
//            System.out.println();
//        }
//
//        System.out.println("Origin: " + originTxt);
//        System.out.println("Result: " + newTxt);
//    }

}
