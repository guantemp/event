/*
 * Copyright (c) 2022. www.hoprxi.com All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package event.hoprxi.infrastruture.persistence;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;
import salt.hoprxi.crypto.application.PasswordService;
import salt.hoprxi.utils.ResourceWherePath;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

/***
 * @author <a href="www.hoprxi.com/authors/guan xiangHuan">guan xiangHuang</a>
 * @since JDK8.0
 * @version 0.0.1 builder 2022-08-22
 */
public class PsqlSetup {
    private static final String CREATE_TABLE_EVENT_SQL = "create type catalog_event as enum ('brand','category', 'item', 'prohibitSellItem');\n" +
            "\n" +
            "create table if not exists catalog\n" +
            "(\n" +
            "    id         bigint        not null,\n" +
            "    event_type catalog_event not null,\n" +
            "    occurredOn timestamp     not null,\n" +
            "    value      jsonb,\n" +
            "    primary key (id)\n" +
            ");\n" +
            "\n" +
            "create index if not exists type_index\n" +
            "    on catalog (event_type);";

    private static final String CREATE_USER = "create user {0} with login createdb password ''{1}'';";
    private static final String CREATE_DATABASE = "create database {0} with owner=event;";

    private static final Config config;

    static {
        config = ConfigFactory.load("event");
    }

    public static void setup(String user, String password, String databaseName) throws SQLException, IOException, URISyntaxException {
        String recommendUserName = PasswordService.generateVeryStrongPassword();
        String recommendUserPassword = PasswordService.generateVeryStrongPassword();
        System.out.println(recommendUserName + " : " + recommendUserPassword);
       /*
        List<? extends Config> writes = config.getConfigList("write");
        Config write = writes.get(0);
        //jdbc:postgresql://host:port/databaseName
        String jdbcUrl = MessageFormat.format("jdbc:postgresql://{0}:{1}/event", write.getString("host"), write.getString("port"));
        System.out.println(jdbcUrl);
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        //props.setProperty("ssl", "true");
        //props.setProperty("options", "-c search_path=test,public,pg_catalog -c statement_timeout=90000");
        try (Connection connection = DriverManager.getConnection(jdbcUrl, props)) {
            Statement statement = connection.createStatement();
            //statement.execute(MessageFormat.format(CREATE_USER, recommendUserName, recommendUserPassword));
            //tatement.execute(MessageFormat.format(CREATE_DATABASE, "event"));
            statement.execute(CREATE_TABLE_EVENT_SQL);
            statement.close();

        }
        */
        Pattern pattern = Pattern.compile("user");
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        System.out.println(ResourceWherePath.toUrl("event.conf"));
        Stream<String> lines = Files.lines(Paths.get(loader.getResource("event.conf").toURI()));
        lines.forEach(line -> {
            Matcher matcher = pattern.matcher(line);
            if (matcher.matches())
                System.out.println(line);
        });
    }
}
