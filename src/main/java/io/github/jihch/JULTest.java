package io.github.jihch;

import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.logging.*;

public class JULTest {

    @Test
    public void testQuick() {

        //1、创建日志记录器对象
        Logger logger = Logger.getLogger("io.github.jihch.JULTest");

        //2、日志记录输出
        logger.info("hello jul");

        // 通用方法进行日志记录
        logger.log(Level.INFO, "info msg");

        // 通过占位符 方式输出变量值
        String name = "jack";
        Integer age = 18;
        logger.log(Level.INFO, "用户信息：{0}，{1}", new Object[]{name, age});

    }

    // 日志级别
    @Test
    public void testLogLevel() {
        //1、获取日志记录器对象
        Logger logger = Logger.getLogger("io.github.jihch.JULTest");

        //2、日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }


    // 日志级别
    @Test
    public void testLogConfig() throws IOException {
        //1、获取日志记录器对象
        Logger logger = Logger.getLogger("io.github.jihch.JULTest");

        // 关闭系统默认配置
        logger.setUseParentHandlers(false);

        // 创建 ConsolHandler 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        // 创建简单格式
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger.addHandler(consoleHandler);

        // 配置日志具体级别
        logger.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        // 场景 FileHandler 文件输出
        FileHandler fileHandler = new FileHandler("E:\\record\\2023\\2\\21\\jul.log");

        // 进行关联
        fileHandler.setFormatter(simpleFormatter);
        logger.addHandler(fileHandler);


        //2、日志记录输出
        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");

    }


    // Logger 对象父子关系
    @Test
    public void testLogParent() {
        Logger logger1 = Logger.getLogger("io.github.jihch");
        Logger logger2 = Logger.getLogger("io.github");

        // 测试
        System.out.println(logger1.getParent() == logger2);

        // 所有日志记录器的顶级父元素 LogManager$RootLogger，name ""
        System.out.printf("logger2.getParent():%s, logger2.getParent().getName():%s\n", logger2.getParent(),
                logger2.getParent().getName());

        // 关闭默认配置
        logger2.setUseParentHandlers(false);

        // 创建 ConsolHandler 控制台输出
        ConsoleHandler consoleHandler = new ConsoleHandler();

        // 创建简单格式
        SimpleFormatter simpleFormatter = new SimpleFormatter();

        // 进行关联
        consoleHandler.setFormatter(simpleFormatter);
        logger2.addHandler(consoleHandler);

        // 配置日志具体级别
        logger2.setLevel(Level.ALL);
        consoleHandler.setLevel(Level.ALL);

        logger1.severe("severe");
        logger1.warning("warning");
        logger1.info("info");
        logger1.config("config");
        logger1.fine("fine");
        logger1.finer("finer");
        logger1.finest("finest");
    }

    //加载自定义配置文件
    @Test
    public void testLogProperties() throws IOException {
        // 读取配置文件，通过类加载器
        InputStream resourceAsStream = JULTest.class.getClassLoader().getResourceAsStream("logging.properties");
        // 创建 LogManager
        LogManager logManager = LogManager.getLogManager();
        // 通过 LogManager 加载配置文件
        logManager.readConfiguration(resourceAsStream);

        // 创建日志记录器
        Logger logger = Logger.getLogger("io.github.jihch");

        logger.severe("severe");
        logger.warning("warning");
        logger.info("info");
        logger.config("config");
        logger.fine("fine");
        logger.finer("finer");
        logger.finest("finest");


        Logger logger2 = Logger.getLogger("test");

        logger2.severe("severe test");
        logger2.warning("warning test");
        logger2.info("info test");
        logger2.config("config test");
        logger2.fine("fine test");
        logger2.finer("finer test");
        logger2.finest("finest test");
    }


}
