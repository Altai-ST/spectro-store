package engine.spectro.filter;

import lombok.Data;

@Data
public class Filter {
    private String fieldName;
    private String operator;
    private Object value;

    // геттеры и сеттеры
}

