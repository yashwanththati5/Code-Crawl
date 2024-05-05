CREATE TABLE IF NOT EXISTS leetcode_data (
    userid varchar(64) PRIMARY KEY,
    usersolvedcount varchar(64),
    usercontestrating varchar(64)
);

CREATE TABLE IF NOT EXISTS codechef_data (
    userid varchar(64) PRIMARY KEY,
    usersolvedcount varchar(64),
    usercontestrating varchar(64)
);

CREATE TABLE IF NOT EXISTS codeforces_data (
    userid varchar(64) PRIMARY KEY,
    usersolvedcount varchar(64),
    usercontestrating varchar(64)
);

CREATE TABLE IF NOT EXISTS geeksforgeeks_data (
    userid varchar(64) PRIMARY KEY,
    usersolvedcount varchar(64),
    usercontestrating varchar(64)
);
