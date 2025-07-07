package org.example.lesson32.jdbc;

import org.example.lesson32.model.Device;
import org.example.lesson32.model.DeviceConnection;
import org.example.lesson32.model.Model;
import org.example.lesson32.model.Network;

import java.sql.*;
import java.util.*;
import java.util.Date;

public class NetworkDao {
    private final String HOST = "localhost";
    private final int PORT = 5432;
    private final String DATABASE = "mydatabase";
    private final String USER = "admin";
    private final String PASSWORD = "admin";

    public NetworkDao() throws ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
    }

    private Connection getConnection() throws SQLException {
        String url = "jdbc:postgresql://"+HOST+":"+PORT+"/"+ DATABASE;
        return DriverManager.getConnection(url, USER, PASSWORD);
    }

    public Network save(Network networkToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "insert into " + Network.getTable() + " " +
                            "(name, description) " +
                            "values (?,?)", Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setString(1, networkToSave.getName());
                statement.setString(2, networkToSave.getDescription());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                networkToSave.setId(generatedKeys.getLong("id"));
                networkToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return networkToSave;
            }
        }
    }

    public Device save(Device deviceToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "insert into " + Device.getTable() + " " +
                            "(name, ip_address,mac_address,type,status, network_id) " +
                            "values (?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setString(1, deviceToSave.getName());
                statement.setString(2, deviceToSave.getIpAddress());
                statement.setString(3, deviceToSave.getMacAddress());
                statement.setString(4, deviceToSave.getType());
                statement.setString(5, deviceToSave.getStatus());
                statement.setLong(6, deviceToSave.getNetworkId());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                deviceToSave.setId(generatedKeys.getLong("id"));
                deviceToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return deviceToSave;
            }
        }
    }

    public DeviceConnection save(DeviceConnection connectionToSave) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "insert into " + DeviceConnection.getTable() + " " +
                            "(type,status, device_from_id, device_to_id) " +
                            "values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS)
            ) {
                statement.setString(1, connectionToSave.getType());
                statement.setString(2, connectionToSave.getStatus());
                statement.setLong(3, connectionToSave.getDeviceFromId());
                statement.setLong(4, connectionToSave.getDeviceToId());
                statement.execute();

                ResultSet generatedKeys = statement.getGeneratedKeys();
                generatedKeys.next();
                connectionToSave.setId(generatedKeys.getLong("id"));
                connectionToSave.setCreatedAt(generatedKeys.getTimestamp("created_at"));
                return connectionToSave;
            }
        }
    }

    public List<Network> getAllNetworks() throws SQLException {
        String sql = "SELECT * FROM " + Network.getTable();
        return getNetworksByQuery(sql);
    }

    public List<Network> getAllNetworksLike(String name) throws SQLException {
        String sql = "SELECT * FROM " + Network.getTable() + " WHERE name LIKE '%" + name + "%'";
        return getNetworksByQuery(sql);
    }

    private List<Network> getNetworksByQuery(String sql) throws SQLException {
        try (var connection = getConnection();
             var statement = connection.createStatement();
             var resultSet = statement.executeQuery(sql)) {

            List<Network> networks = new ArrayList<>();
            while (resultSet.next()) {
                networks.add(parseTo(resultSet, new Network()));
            }
            return networks;
        }
    }

    public List<Device> getAllDevices() throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select * from " + Device.getTable());
                List<Device> devices = new ArrayList<>();

                while (resultSet.next()) {
                    devices.add(parseTo(resultSet, new Device()));
                }

                return devices;
            }
        }
    }

    public List<DeviceConnection> getAllConnections() throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.createStatement()) {
                ResultSet resultSet = statement.executeQuery("select * from " + DeviceConnection.getTable());
                List<DeviceConnection> deviceConnections = new ArrayList<>();

                while (resultSet.next()) {
                    long id = resultSet.getLong("id");
                    String type = resultSet.getString("type");
                    String status = resultSet.getString("status");
                    long deviceFromId = resultSet.getLong("device_from_id");
                    long deviceToId = resultSet.getLong("device_to_id");
                    Date createdAt = resultSet.getTimestamp("created_at");
                    deviceConnections.add(new DeviceConnection(id, deviceFromId, deviceToId, type, status, createdAt));
                }

                return deviceConnections;
            }
        }
    }

    public void remove(DeviceConnection deviceConnection) throws SQLException {
        remove(deviceConnection, DeviceConnection.getTable());
    }

    public void remove(Network network) throws SQLException {
        remove(network, Network.getTable());
    }

    public void remove(Model model, String table) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "delete from " + table + " " +
                            "where id = ?")
            ) {
                statement.setLong(1, model.getId());
                statement.execute();
            }
        }
    }

    public List<Device> getDevicesOf(Network network) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "select * from " + Device.getTable() + " " +
                            "where network_id = ?")
            ) {
                statement.setLong(1, network.getId());
                ResultSet resultSet = statement.executeQuery();
                return parseDevicesFrom(resultSet);
            }
        }
    }

    public List<Device> parseDevicesFrom(ResultSet resultSet) throws SQLException {
        List<Device> devices = new ArrayList<>();

        while (resultSet.next()) {
            devices.add(parseTo(resultSet, new Device()));
        }

        return devices;
    }

    public Network update(Network networkToUpdate) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "update " + Network.getTable() + " " +
                            "set name=?, description=? " +
                            "where id = ? " +
                            "returning id, name, description, created_at")
            ) {
                statement.setString(1, networkToUpdate.getName());
                statement.setString(2, networkToUpdate.getDescription());
                statement.setLong(3, networkToUpdate.getId());
                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return parseTo(resultSet, networkToUpdate);
            }
        }
    }

    public Device update(Device deviceToUpdate) throws SQLException {
        try (var connection = getConnection()) {
            try (var statement = connection.prepareStatement(
                    "update " + Device.getTable() + " " +
                            "set network_id=?, name=?, ip_address=?, mac_address=?, type=?, status=? " +
                            "where id = ? " +
                            "returning id, network_id, name, ip_address, mac_address, type, status, created_at ")
            ) {
                statement.setLong(1, deviceToUpdate.getNetworkId());
                statement.setString(2, deviceToUpdate.getName());
                statement.setString(3, deviceToUpdate.getIpAddress());
                statement.setString(4, deviceToUpdate.getMacAddress());
                statement.setString(5, deviceToUpdate.getType());
                statement.setString(6, deviceToUpdate.getStatus());
                statement.setLong(7, deviceToUpdate.getId());

                statement.execute();

                ResultSet resultSet = statement.getResultSet();
                resultSet.next();
                return parseTo(resultSet, deviceToUpdate);
            }
        }
    }

    public List<Device> getAllDevicesWithNetworks() throws SQLException {
        String sql = """
            SELECT
                d.id AS device_id,
                d.name AS device_name,
                d.ip_address,
                d.mac_address,
                d.type AS device_type,
                d.status AS device_status,
                d.network_id AS device_network_id,
                d.created_at AS device_created_at,
                n.id AS network_id,
                n.name AS network_name,
                n.description AS network_description,
                n.created_at AS network_created_at
            FROM devices d
            JOIN networks n ON d.network_id = n.id
            ORDER BY d.id
            """;

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            List<Device> devices = new ArrayList<>();

            while (resultSet.next()) {
                Device device = new Device();
                device.setId(resultSet.getLong("device_id"));
                device.setName(resultSet.getString("device_name"));
                device.setIpAddress(resultSet.getString("ip_address"));
                device.setMacAddress(resultSet.getString("mac_address"));
                device.setType(resultSet.getString("device_type"));
                device.setStatus(resultSet.getString("device_status"));
                device.setNetworkId(resultSet.getLong("device_network_id"));
                device.setCreatedAt(resultSet.getTimestamp("device_created_at"));

                Network network = new Network();
                network.setId(resultSet.getLong("network_id"));
                network.setName(resultSet.getString("network_name"));
                network.setDescription(resultSet.getString("network_description"));
                network.setCreatedAt(resultSet.getTimestamp("network_created_at"));

                device.setNetwork(network);

                devices.add(device);
            }

            return devices;
        }
    }

    public List<Network> getAllNetworksWithDevices() throws SQLException {
        String sql = """
            SELECT
                n.id AS network_id,
                n.name AS network_name,
                n.description AS network_description,
                n.created_at AS network_created_at,
                d.id AS device_id,
                d.name AS device_name,
                d.ip_address,
                d.mac_address,
                d.type AS device_type,
                d.status AS device_status,
                d.network_id AS device_network_id,
                d.created_at AS device_created_at
            FROM networks n
            LEFT JOIN devices d ON n.id = d.network_id
            ORDER BY n.id
            """;

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql);
             var resultSet = statement.executeQuery()) {

            Map<Long, Network> networkMap = new LinkedHashMap<>();

            while (resultSet.next()) {
                long networkId = resultSet.getLong("network_id");

                Network network = networkMap.get(networkId);
                if (network == null) {
                    network = new Network();
                    network.setId(networkId);
                    network.setName(resultSet.getString("network_name"));
                    network.setDescription(resultSet.getString("network_description"));
                    network.setCreatedAt(resultSet.getTimestamp("network_created_at"));
                    network.setDevices(new ArrayList<>());
                    networkMap.put(networkId, network);
                }

                long deviceId = resultSet.getLong("device_id");
                if (!resultSet.wasNull()) {
                    Device device = new Device();
                    device.setId(deviceId);
                    device.setName(resultSet.getString("device_name"));
                    device.setIpAddress(resultSet.getString("ip_address"));
                    device.setMacAddress(resultSet.getString("mac_address"));
                    device.setType(resultSet.getString("device_type"));
                    device.setStatus(resultSet.getString("device_status"));
                    device.setNetworkId(resultSet.getLong("device_network_id"));
                    device.setCreatedAt(resultSet.getTimestamp("device_created_at"));

                    network.getDevices().add(device);
                }
            }

            return new ArrayList<>(networkMap.values());
        }
    }

    public List<Network> getNetworksWithDeviceCount(String status) throws SQLException {
        String sql = """
            SELECT
                n.id AS network_id,
                n.name AS network_name,
                n.description AS network_description,
                n.created_at AS network_created_at,
                COUNT(d.id) AS active_device_count
            FROM networks n
            LEFT JOIN devices d
                ON n.id = d.network_id AND d.status = ?
            GROUP BY n.id, n.name, n.description, n.created_at
            ORDER BY n.name
            """;

        try (var connection = getConnection();
             var statement = connection.prepareStatement(sql);
             ) {
            statement.setString(1, status);
            var resultSet = statement.executeQuery();

            List<Network> networks = new ArrayList<>();

            while (resultSet.next()) {
                Network network = new Network();
                network.setId(resultSet.getLong("network_id"));
                network.setName(resultSet.getString("network_name"));
                network.setDescription(resultSet.getString("network_description"));
                network.setCreatedAt(resultSet.getTimestamp("network_created_at"));
                network.setActiveDeviceCount(resultSet.getInt("active_device_count"));

                networks.add(network);
            }

            return networks;
        }
    }


    private Device parseTo(ResultSet resultSet, Device device) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String ipAddress = resultSet.getString("ip_address");
        String macAddress = resultSet.getString("mac_address");
        String type = resultSet.getString("type");
        String status = resultSet.getString("status");
        long networkId = resultSet.getLong("network_id");
        Date createdAt = resultSet.getTimestamp("created_at");

        device.setId(id);
        device.setName(name);
        device.setIpAddress(ipAddress);
        device.setMacAddress(macAddress);
        device.setType(type);
        device.setStatus(status);
        device.setNetworkId(networkId);
        device.setCreatedAt(createdAt);
        return device;
    }

    private Network parseTo(ResultSet resultSet, Network network) throws SQLException {
        long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String description = resultSet.getString("description");
        Date createdAt = resultSet.getTimestamp("created_at");

        network.setId(id);
        network.setName(name);
        network.setDescription(description);
        network.setCreatedAt(createdAt);
        return network;
    }
}
