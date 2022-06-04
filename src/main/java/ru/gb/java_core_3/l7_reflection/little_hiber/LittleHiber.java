package ru.gb.java_core_3.l7_reflection.little_hiber;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class LittleHiber {
    private static Connection connection;

    public static <T> void insertObject(T o) {
        Class c = o.getClass();

        if (!c.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException();
        }

        Field[] fields = c.getDeclaredFields();
        checkId(fields);
        try {
            connect();
            // insert into little_cats (name, color) values ('sdvsd', 'sdsdv');

            try  {
                StringBuilder sb = new StringBuilder("insert into ");
                sb.append(o.getClass().getAnnotation(Table.class).name());
                sb.append(" (");
                int questions = 0;
                List<Object> values = new LinkedList<>();

                for (Field field : fields) {
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(Id.class)) {
                        if (field.getAnnotation(Id.class).autoincrement()) {
                            continue;
                        }
                    }

                    if (field.isAnnotationPresent(Column.class)) {
                        String name = field.getAnnotation(Column.class).name().isBlank() ? field.getName()
                                : field.getAnnotation(Column.class).name();
                        sb.append(name);
                        sb.append(", ");
                        questions++;
                        values.add(field.get(o));
                    }
                }

                sb.setLength(sb.length() - 2);
                sb.append(") values (");

                for (int i = 0; i < questions; i++) {
                    sb.append(i + 1 == questions ? "?);" : "?, ");
                }

                try (PreparedStatement stmt = connection.prepareStatement(sb.toString())) {
                    int number = 1;
                    for (Object value : values) {
                        stmt.setObject(number++, value);
                    }
                    stmt.executeUpdate();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        } finally {
            disconnect();
        }

    }

    public static <T> void createTable(Class<T> c) {
        Map<Class, String> typeMap = getTypeMap();

        if (!c.isAnnotationPresent(Table.class)) {
            throw new IllegalArgumentException();
        }

        Field[] fields = c.getDeclaredFields();
        checkId(fields);
        try {
            connect();
            // create table little_cats  (id integer primary key autoincrement, name text, color text);

            try (Statement stmt = connection.createStatement()) {
                StringBuilder sb = new StringBuilder("create table ");
                sb.append(c.getAnnotation(Table.class).name());
                sb.append(" (");

                for (Field field : fields) {
                    if (field.isAnnotationPresent(Column.class)) {
                        String name = field.getAnnotation(Column.class).name().isBlank() ? field.getName()
                                : field.getAnnotation(Column.class).name();
                        sb.append(name);
                        sb.append(" ");
                        sb.append(typeMap.get(field.getType()));

                        if (field.isAnnotationPresent(Id.class)) {
                            sb.append(" primary key");
                            if (field.getAnnotation(Id.class).autoincrement()) {
                                sb.append(" autoincrement");
                            }
                        }
                        sb.append(", ");
                    }
                }

                sb.setLength(sb.length() - 2);
                sb.append(");");

                stmt.execute(sb.toString());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } finally {
            disconnect();
        }

    }

    private static void checkId(Field[] fields) {
        boolean hasId = false;
        for (Field field : fields) {
            if (field.isAnnotationPresent(Id.class)) {
                if (hasId) {
                    throw new IllegalArgumentException("More than one id");
                }
                hasId = true;
            }
        }
        if (!hasId) {
            throw new IllegalArgumentException("No one id");
        }
    }

    private static Map<Class, String> getTypeMap() {
        Map<Class, String> typeMap = new HashMap<>();
        typeMap.put(int.class, "integer");
        typeMap.put(String.class, "text");
        return typeMap;
    }

    private static void disconnect() {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    private static void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:db/reflect.db");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
