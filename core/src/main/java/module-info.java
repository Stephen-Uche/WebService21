module core {
    requires com.google.gson;
    requires utils;
    opens x.snowroller to com.google.gson;
}