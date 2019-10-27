/*
 * QC REST API client
 *
 * Copyright (C) 2019  matthias.dirickx@outlook.com
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package be.mdi.testing.qc;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Static property manager.
 * The rudimentary property manager loads a file from resources (root).
 * Name : qcclient.properties
 *
 * The properties are loaded to a map and resolved.
 *
 * If any properties are added at runtime you need to call resolveProperties() if the properties contain placeholders.
 *
 * There are three public accessors.
 *     - getProperty()
 *     - setProperty()
 *     - resolveProperties()
 *
 * Get a property with getProperty(name).
 * Set a property with setProperty(name).
 *
 * If there are properties containing placeholders,
 * make sure to call resolveProperties() to resolve all of the property placeholders.
 */
public class PropertyManager {

    private static final String PROP_FIND_REGEX = "\\$\\{[^\\{\\}]*\\}";
    private static final Pattern pattern = Pattern.compile(PROP_FIND_REGEX);

    private static PropertyManager pr;
    private Properties props;
    private Map<String, String> propsMap;

    private PropertyManager() {
        props = new Properties();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("qcclient.properties");
        try {
            props.load(inputStream);
        } catch(IOException e) {
            e.printStackTrace();
        }
        propsMap = new HashMap<>();
    }

    private void pushPropsToMap() {
        for(String key : props.stringPropertyNames()) {
            propsMap.put(
                    key,
                    resolveProperty(props.getProperty(key))
            );
        }
    }

    /**
     * Recursive function to resolve the property placeholders.
     * @param propValue
     * @return String (resolved property without placeholders)
     */
    private String resolveProperty(String propValue) {
        String resolvedProperty = propValue;
        Matcher matcher = pattern.matcher(propValue);

        if(matcher.find(0)) {

            String match = matcher.group();
            resolvedProperty = resolvedProperty.replace(match, getProperty(match.substring(2, match.length()-1)));
            Matcher nestedMatcher = pattern.matcher(resolvedProperty);

            if(nestedMatcher.find(0)) {
                resolvedProperty = resolveProperty(resolvedProperty);
            }
        }
        return resolvedProperty;
    }

    private static synchronized Map<String, String> getProperties() {
        if(pr == null) {
            synchronized (PropertyManager.class) {
                if(pr == null) {
                    pr = new PropertyManager();
                    pr.pushPropsToMap();
                    resolveProperties();
                }
            }
        }
        return pr.propsMap;
    }

    /**
     * Gets a property from the hashmap stored in the PropertyManager singleton.
     * @param name
     * @return
     */
    public static String getProperty(String name) {
        return getProperties().get(name);
    }

    /**
     * Set property  in the hasmap stroed in the PropertyManager singleton.
     * Setting a value with setProperty does NOT store this property in the .properties file.
     * @param name
     * @param value
     */
    public static void setProperty(String name, String value) {
        getProperties().put(name, value);
    }

    /**
     * Resolve all properties that are stored in the hashmap in the PropertyManager singleton.
     */
    public static void resolveProperties() {
        for(String key : pr.propsMap.keySet()) {
            String resolvedProp = pr.resolveProperty(getProperty(key));
            pr.propsMap.put(key, resolvedProp);
        }
    }
}
