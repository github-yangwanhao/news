这是一个java写的收集新闻并定时发送邮件的小工具。
目前只实现了百度头条的收集，也可自行扩展其他渠道，扩展EnumNewsChannelType枚举，增加ILoadNewsService接口的实现类，并在news_channel表配置即可。
主要使用SpringBoot+Mysql实现，没有其他中间件，减少部署的复杂度。SQL位于\src\main\resources\mysql.sql。
配置文件位于\src\main\resources\application-demo.yml，主要需要修改该配置文件里的配置项即可运行。
