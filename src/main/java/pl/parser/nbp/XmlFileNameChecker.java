package pl.parser.nbp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum XmlFileNameChecker {
    INSTANCE;

    private static final Pattern pattern = Pattern.compile("c[0-9]{3}z[0-9]{6}");

    public boolean check(String xmlFileName){
        Matcher matcher = pattern.matcher(xmlFileName);
        return matcher.find();
    }
}
