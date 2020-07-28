package tech.vergieet.sample.metaprogramming.models;

import java.util.List;

public class BaseListResult<T> extends BaseResult<List<T>> {

    public BaseListResult() {
    }

    public BaseListResult(boolean status, String message, List<T> data) {
        super(status, message, data);
    }
}
