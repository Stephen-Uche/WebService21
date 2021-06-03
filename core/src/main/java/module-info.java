module core {
    requires com.google.gson;
    requires spi;
    requires static lombok;
    uses spi.Greeting;
}