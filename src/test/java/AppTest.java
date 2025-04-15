import java.util.LinkedHashMap;
import java.util.Map;

public class AppTest {
  public static void main(String[] args) {
    String queryString1 = "subject=제목1&content=내용1&writerName=양관식&boardId=5";
    Map<String, String> params1 = Util.getParams(queryString1);
    System.out.println(params1);

    String queryString2 = "subject=제목20&content=내용20&writerName=오애순&boardId=1";
    Map<String, String> params2 = Util.getParams(queryString2);
    System.out.println(params2);

  }
}

class Util {
  static Map<String, String> getParams(String queryStr) {
    Map<String, String> params = new LinkedHashMap<>();

    String[] queryString = queryStr.split("&");

    for(String bit : queryString) {
      String[] bitBits = bit.split("=");

      params.put(bitBits[0], bitBits[1]);
    }

    return params;
  }

}