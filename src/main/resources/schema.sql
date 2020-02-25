create table token (
    tokenId int not null auto_increment,
    token varchar(512) not null,
    createdDate datetime not null,
    expirationDate datetime,
    PRIMARY KEY (tokenId),
    unique key (token)
);

create table used (
    usedId int not null auto_increment,
    path varchar(512) not null,
    usedDate datetime,
    PRIMARY KEY (usedId)
);