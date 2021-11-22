create table if not exists dueños (
    id serial,
    nombre varchar(45) not null,
    edad int not null,
    email varchar(50) not null,
    primary key (id)
    );

create table if not exists usuario (
    id serial,
    nombre varchar(45) not null,
    edad int not null,
    email varchar(50) not null,
    telefono int not null,
    genero varchar(1) not null,
    direccion varchar(100) not null,
    primary key (id)
    );

create table if not exists restaurantes (
    id serial,
    nombre varchar(45) not null,
    calificacion int not null,
    direccion varchar(100) not null,
    dueños_idDueño int not null,
    primary key (id),
    FOREIGN KEY (dueños_idDueño) REFERENCES dueños(id)
    );

create table if not exists categoria (
    id serial,
    tipo varchar(45) not null,
    primary key (id)
    );

create table if not exists categoria_restaurantes(
    categoria_idCategoria int not null,
    restaurantes_idRestaurante int not null,
    FOREIGN KEY (categoria_idCategoria) REFERENCES categoria(id),
    FOREIGN KEY (restaurantes_idRestaurante) REFERENCES restaurantes(id)
    );

create table if not exists consulta (
    id serial,
    restaurantes_idrestaurante int not null,
    categoria_idcategoria int not null,
    usuario_nombre int not null,
    fecha_consulta date not null,
    primary key (id),
    FOREIGN KEY (restaurantes_idrestaurante) REFERENCES restaurantes(id),
    FOREIGN KEY (categoria_idcategoria) REFERENCES categoria(id)
    );
