import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {
  public static void main(String[] args) {
    String queryString = "subject=제목1&content=내용1&writerName=양관식&boardId=5";
    String[] queryStringBits = queryString.split("&");
    System.out.println(Arrays.toString(queryStringBits));

    List<String> paramNames = new ArrayList<>();
    List<String> paramValues = new ArrayList<>();

    for (String bit : queryStringBits) {
      String[] bitBits = bit.split("=");
      String paramName = bitBits[0];
      String paramValue = bitBits[1];

      paramNames.add(paramName);
      paramValues.add(paramValue);
    }

    System.out.println(paramNames);
    System.out.println(paramValues);

    String targetData = "content";
    int findIndex = paramNames.indexOf("content");
    System.out.printf("%s : %s\n", targetData, paramValues.get(findIndex));
  }
}
