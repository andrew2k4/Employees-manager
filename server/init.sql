-- Vérifiez si l'utilisateur existe déjà
SELECT EXISTS (SELECT 1 FROM mysql.user WHERE user = 'external_user') INTO @user_exists;

-- Si l'utilisateur n'existe pas, créez-le
SET @sql = IF(@user_exists, 'SELECT "User already exists";', 'CREATE USER ''external_user''@''%'' IDENTIFIED BY ''password'';');
PREPARE stmt FROM @sql;
EXECUTE stmt;
DEALLOCATE PREPARE stmt;

-- Accorder les privilèges
GRANT ALL PRIVILEGES ON my_database.* TO 'external_user'@'%';
FLUSH PRIVILEGES;
