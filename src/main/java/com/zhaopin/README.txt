[Jersey + Spring + Mybatis + Gradle + PostgreSql]

Class And Method:   MessageController#send
Request URL:        http://localhost:8080/ihr/im/message/send
Request Method:     POST
Request Body:       {"attach":"{\"data\":{\"ids\":[\"wangwu\"],\"tinfo\":{\"1\":\"11927\",\"10\":\"1458887253106\",\"11\":\"1458886362629\",\"12\":\"1458887253159\",\"16\":\"0\",\"17\":\"0\",\"2\":\"11011\",\"21\":\"0\",\"22\":\"0\",\"23\":\"0\",\"24\":\"0\",\"3\":\"张三\",\"4\":\"0\",\"5\":\"zhangsan\",\"6\":\"10000\",\"8\":\"1\",\"9\":\"3\"},\"uinfos\":[{\"1\":\"wangwu\",\"11\":\"236911\",\"12\":\"1457417052774\",\"13\":\"1457491548992\",\"2\":\"11011\",\"3\":\"wangwu\",\"6\":\"0\"},{\"1\":\"zhangsan\",\"11\":\"142915\",\"12\":\"1438738423546\",\"13\":\"1456885865056\",\"2\":\"11011\",\"3\":\"zhangsan\",\"4\":\"http://b12026.nos.netease.com/MTAxMTAxMA==/bmltYV8xNDI5MTZfMTQzODg2NDI4ODE0Ml81NjM3ZTIxMC1iMjE5LTRhYjgtOGZlOS02MzBjZWFjYmMwZDE=\",\"6\":\"1\"}]},\"id\":1}","convType":"TEAM","eventType":"1","fromAccount":"zhangsan","fromClientType":"IOS","msgTimestamp":"1458887253168","msgType":"NOTIFICATION","msgidClient":"693b8b53-3d0e-445e-b086-debaffa7a376","msgidServer":"800407420932","resendFlag":"0","tMembers":"[zhaoliu, wangwu, zhangsan, lisi]","to":"11927"}


上线前注意事项:
(1)JDK版本为1.8;
(2)确保log4j.properties中的日志目录为linux目录;
(3)修改resource.properties文件中的url地址为平台的实际接口url;
(4)本项目不使用数据库,所以将db.properties文件中和数据库有关配置注释掉;
(5)去掉数据库连接池有关配置;
(6)去掉所有和mybatis有关的配置;








