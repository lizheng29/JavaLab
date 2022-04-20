package myTestProject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HelpFriend {

    public static void main(String[] args) {

        StringBuffer allString = new StringBuffer();
        Path path = Paths.get("D:\\111.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(allString::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
//        System.out.println(allString);

        String source = allString.toString();

        Set<Character> stopWords = new HashSet<>();
        stopWords.add(' ');
        stopWords.add(',');
        int replaceCount = 0;
        while (true) {
            int index = source.indexOf("[Title/Abstract]");
            if (index == -1) {
                break;
            }
            int lastCharIndex = index;
            int firstCharIndex = lastCharIndex;
            if (source.charAt(lastCharIndex - 1) == ')') {
                while (source.charAt(firstCharIndex) != '(') {
                    firstCharIndex--;
                }
                firstCharIndex--;
            } else {
                while (!stopWords.contains(source.charAt(firstCharIndex))) {
                    firstCharIndex--;
                }
            }
            String word = source.substring(firstCharIndex + 1, lastCharIndex);
            source = source.replace(word + "[Title/Abstract]", "'  " + word + "   ':ab,ti");
//            System.out.println(source);
            replaceCount++;
        }
        Path path2 = Paths.get("D:\\222.txt");
        try {
            Files.deleteIfExists(path2);
            Files.createFile(path2);
            Files.write(path2, source.getBytes(StandardCharsets.UTF_8));
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("替换了 " + replaceCount + " 处");
    }
}
