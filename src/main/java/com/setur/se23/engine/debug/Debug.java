package com.setur.se23.engine.debug;

public abstract class Debug {

    public abstract void warning(String string);

    public abstract void error(String string);

    public abstract void info(String string);

    public abstract void checkFPS();

    public abstract void clearConsole();

}
