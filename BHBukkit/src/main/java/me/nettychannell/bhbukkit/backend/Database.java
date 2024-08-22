package me.nettychannell.bhbukkit.backend;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.nettychannell.bhbukkit.backend.profiler.HomeProfiler;
import me.nettychannell.bhbukkit.serializer.ObjectSerializer;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

public class Database {

    private final HikariDataSource hikariDataSource;

    public Database(String host, int port, String database, String username, String password) {
        HikariConfig hikariConfig = new HikariConfig();

        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);
        hikariConfig.setUsername(username);
        hikariConfig.setPassword(password);
        hikariConfig.setMaximumPoolSize(10);
        hikariConfig.setMinimumIdle(10);
        hikariConfig.setConnectionTimeout(5000);
        hikariConfig.setIdleTimeout(60000);

        this.hikariDataSource = new HikariDataSource(hikariConfig);
        this.createTable();
    }

    private void createTable() {
        try (Connection connection = this.hikariDataSource.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS homes (uuid VARCHAR(36) PRIMARY KEY, homes LONGBLOB)");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public HomeProfiler getHomeProfiler(UUID uuid) {
        String query = "SELECT * FROM homes WHERE uuid = ?";

        try (Connection connection = this.hikariDataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, uuid.toString());

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                return new HomeProfiler(uuid, Bukkit.getOfflinePlayer(uuid).getName(), ObjectSerializer.deserialize(resultSet.getBytes("homes")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void setHomeProfiler(HomeProfiler homeProfiler) {
        String query = "INSERT INTO homes (uuid, homes) VALUES (?, ?) ON DUPLICATE KEY UPDATE homes = ?";

        try (Connection connection = this.hikariDataSource.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, homeProfiler.getUuid().toString());
            preparedStatement.setBytes(2, ObjectSerializer.serialize(homeProfiler.getHomes()));
            preparedStatement.setBytes(3, ObjectSerializer.serialize(homeProfiler.getHomes()));

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CompletableFuture<Void> setHomeProfilerAsync(HomeProfiler homeProfiler) {
        return CompletableFuture.runAsync(() -> setHomeProfiler(homeProfiler));
    }


}
