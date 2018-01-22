package com.teamknp.hotel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiFunction;
import java.util.function.Function;

@Configuration
public class ThemeFunc {

    private static final Map<String, String> PREFIX_TO_COLOR;

    static {
//        purple | blue | green | orange | red
        HashMap<String, String> prefixes = new HashMap<>();
        prefixes.put("/admin/dashboard", "purple");
        prefixes.put("/admin/product", "blue");
        prefixes.put("/admin/delivery", "blue");
        prefixes.put("/admin/staff", "orange");

        PREFIX_TO_COLOR = Collections.unmodifiableMap(prefixes);
    }

    @Bean
    public Function<String, String> currentUrlWithoutParam() {
        return param -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(param)
                .toUriString();
    }

    @Bean
    public BiFunction<String, String, String> currentUrlWithoutParams2() {
        return (param, param2) -> ServletUriComponentsBuilder
                .fromCurrentRequest()
                .replaceQueryParam(param)
                .replaceQueryParam(param2)
                .toUriString();
    }

    @Bean
    Function<HttpServletRequest, String> currentThemeColor() {
        return (HttpServletRequest request) -> {
            for (Map.Entry<String, String> entry : PREFIX_TO_COLOR.entrySet()) {
                if (request.getRequestURI().startsWith(entry.getKey())) {
                    return entry.getValue();
                }
            }


            return "red";
        };
    }
}
