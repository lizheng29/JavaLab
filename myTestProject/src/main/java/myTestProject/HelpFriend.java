package myTestProject;

import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class HelpFriend {

    public static void main(String[] args) {

        StringBuffer allString = new StringBuffer();
        Path path = Paths.get("C:\\Users\\lizheng\\Desktop\\111.txt");
        try {
            List<String> lines = Files.readAllLines(path);
            lines.forEach(allString::append);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(allString);

        String source = "Stroke[Mesh] OR Strokes[Title/Abstract] OR Cerebrovascular Accident[Title/Abstract] OR " +
                "Cerebrovascular Accidents[Title/Abstract] OR CVA (Cerebrovascular Accident)[Title/Abstract] OR CVAs " +
                "(Cerebrovascular Accident)[Title/Abstract] OR Cerebrovascular Apoplexy[Title/Abstract] OR Apoplexy, " +
                "Cerebrovascular[Title/Abstract] OR Vascular Accident, Brain[Title/Abstract] OR Brain Vascular " +
                "Accident[Title/Abstract] OR Brain Vascular Accidents[Title/Abstract] OR Vascular Accidents, " +
                "Brain[Title/Abstract] OR Cerebrovascular Stroke[Title/Abstract] OR Cerebrovascular " +
                "Strokes[Title/Abstract] OR Stroke, Cerebrovascular[Title/Abstract] OR Strokes, " +
                "Cerebrovascular[Title/Abstract] OR";

        Set<Character> stopWords = new HashSet<>();
        stopWords.add(' ');
        stopWords.add(',');

        while (true) {
            int index = StringUtils.indexOf(source, "[Title/Abstract]");
            if (index == -1) {
                break;
            }
            int lastCharIndex = index;
            int firstCharIndex = lastCharIndex;
            if (source.charAt(lastCharIndex-1) == ')') {
                while (source.charAt(firstCharIndex) != '(') {
                    firstCharIndex--;
                }
                firstCharIndex--;
            } else {
                while (!stopWords.contains(source.charAt(firstCharIndex))) {
                    firstCharIndex--;
                }
            }
            String word = StringUtils.substring(source, firstCharIndex + 1, lastCharIndex);
            source = source.replace(word + "[Title/Abstract]", "'  " + word + "   ':ab,ti");
            System.out.println(source);
        }
    }
}
