# 添加管理员, 请在执行脚本前修改'username'和'password'的值
SET @salt = replace(UUID(), '-', '');
INSERT INTO administrator (username, password, salt, gmt_create, gmt_modified)
  VALUE ('username', md5(concat('password', @salt)), @salt,
         now(3), now(3));

# 删除管理员
DELETE FROM administrator WHERE username = 'username';