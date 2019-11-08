import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Wix {
    static private String message = "Lorem, ipsum dolor sit amet consectetur adipisicing elit. At omnis commodi\n" +
            "      doloremque. Ipsam amet iure accusamus dolore unde recusandae aspernatur\n" +
            "      odio aliquam asperiores aut in optio totam rerum! Excepturi veritatis\n" +
            "      omnis quas rerum? Perspiciatis consequatur delectus modi eum cupiditate\n" +
            "      eius officiis, nihil enim iusto quidem officia! Minus dolorum ipsum\n" +
            "      temporibus labore maiores. Fuga, assumenda cumque.";
    static private int limit = 12;

    public static void main(String[] args) {
        Wix wix = new Wix();
        System.out.println(wix.solution(message, limit));
    }

    public Integer solution(String s, int k) {
        s = preparedMessage(s);

        if (s.matches("/^[a-zA-Z\\s]*$/")) {
            return -1;
        }

        if (s.length() == 0 || s.length() > 500) {
            return -1;
        }

        if (k < 0 || k > 500) {
            return -1;
        }

        List<String> split = Arrays.asList(s.split(" "));
        if (split.stream().map(String::length).anyMatch(l -> l > k)) {
            return -1;
        }

        LinkedList<String> maxMessage = new LinkedList<>();
        String buff = "";

        int i = 0;
        do {
            if ((buff.concat(" " + split.get(i))).length() <= k) {
                buff = buff.concat(" " + split.get(i));
            } else {
                maxMessage.add(buff);
                buff = split.get(i);
            }
            if (i == split.size() - 1) {
                maxMessage.add(buff);
            }
            i++;
        } while (i <= split.size() - 1);

        return maxMessage.size();
    }

    private String preparedMessage(String s) {
        return s.trim().replaceAll("\\s++", " ");
    }
}
