create database pruebacastores;

create table roles(
	idRol int primary key auto_increment,
    descripcion varchar(50) not null
);

create table usuarios(
	idUsuario int primary key auto_increment,
    nombre varchar(100) not null,
    correo varchar(50) not null,
    contrasena varchar(25) not null,
    idRol int not null,
    estatus varchar(15) default 'Activo',
    constraint FK_usuarios_roles
		foreign key (idRol) references roles(idRol)
);

create table productos(
	idProducto int primary key auto_increment,
    nombre varchar(50) not null,
    cantidad int default 0,
    estatus varchar(15) default 'Activo'
);

create table historiales(
	idHistorial int primary key auto_increment,
    tipo varchar(20) not null,
    idUsuario int not null,
    idProducto int not null,
    fechaHora datetime not null,
    constraint FK_historiales_usuarios
    foreign key (idUsuario)
		references usuarios(idUsuario),
	constraint FK_historiales_productos
    foreign key (idProducto)
		references productos(idProducto)
);

-- insertar Roles
insert into roles(descripcion)value('Administrador');
insert into roles(descripcion)value('Almacenista');


-- insertar Usuarios
insert into usuarios(nombre, correo, contrasena, idRol)values('Daniel','daniel10@gmail.com','dani1234',1);
insert into usuarios(nombre, correo, contrasena, idRol)values('Alejandro','alex07@gmail.com','alex458',2);
