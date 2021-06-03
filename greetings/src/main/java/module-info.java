module greetings.plugins {
    requires spi;
    provides spi.Greeting with plugins.EnglishGreeting, plugins.SwedishGreeting;
    opens plugins to core;
}