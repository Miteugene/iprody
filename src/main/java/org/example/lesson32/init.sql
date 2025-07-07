create table networks (
    id serial primary key,
    name varchar(255) not null unique,
    description text,
    created_at timestamp not null default current_timestamp
);

create table devices (
    id serial primary key,
    network_id bigint not null,
    name varchar(255) not null,
    ip_address varchar(15) not null,
    mac_address varchar(17) unique,
    type varchar(50) not null,
    status varchar(50) not null,
    created_at timestamp not null default current_timestamp,
    foreign key (network_id) references networks(id)
);

create table connections (
    id serial primary key,
    device_from_id bigint not null,
    device_to_id bigint not null,
    type varchar(50) not null,
    status varchar(50) not null,
    created_at timestamp not null default current_timestamp,
    foreign key (device_from_id) references devices(id),
    foreign key (device_to_id) references devices(id)
);

-- Для поиска
CREATE INDEX idx_networks_name ON networks(name);