CREATE USER 'external_user'@'%' IDENTIFIED BY 'password';
GRANT ALL PRIVILEGES ON my_database.* TO 'external_user'@'%';
FLUSH PRIVILEGES;
