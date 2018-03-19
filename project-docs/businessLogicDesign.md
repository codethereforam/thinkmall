# 业务逻辑设计

## 描述

设计类、数据库

## 类

- 管理员（Administrator）
- 用户（User）
- 收货地址（ShippingAddress）
- 类别（Category）
- 商品（Product）
- 订单（Order）
- 订单项（OrderItem）
- 购物车（Cart）
- 购物项（CartItem）

## 数据库（thinkmall）

1. administrator（管理员）

字段 | 数据类型 | 注释
---- | ---- | ----
administrator_id | unsigned bigint | 管理员编号
username | varchar(20) | 用户名
password | char(32) | 密码 = md5(真实密码 + 盐)
salt | char(32) | 盐
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

2. user（用户）

字段 | 数据类型 | 注释
---- | ---- | ----
user_id | unsigned bigint | 用户编号
username | varchar(20) | 用户名
password | char(32) | 密码 = md5(真实密码 + 盐)
salt | char(32) | 盐
email | varchar(50) | 邮箱
phone_number | char(11) | 电话号码
sex | unsigned tinyint(2) | 性别(0:女，1:男，2:不愿透露)
is_locked | unsigned tinyint(1) | 是否锁定(1表示是,0表示否)
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

3. shipping_address（收货地址）

字段 | 数据类型 | 注释
---- | ---- | ----
shipping_address_id | unsigned bigint | 收货地址编号
user_id | unsigned bigint | 用户编号
receiver_name | varchar(20) | 收货人姓名
phone_number | char(11) | 电话号码
detail | varchar(255) | 详细地址
is_acquiescent | unsigned tinyint(1) | 是否是默认值(0表示否，1表示是)
is_deleted | unsigned tinyint(1) | 是否删除（0：正常，1：删除）
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

4. category（类别）

字段 | 数据类型 | 注释
---- | ---- | ----
category_id | unsigned bigint | 类别编号
name | varchar(20) | 类别名称
description | varchar(255) | 类别描述
parent_id | unsigned bigint | 父类别编号（0代表是根类别）
is_leaf | unsigned tinyint(1) | 是否为叶子节点（0：否，1：是）
level | unsigned tinyint(2) | 类别层次（只能为1或2或3）
is_deleted | unsigned tinyint(1) | 是否删除（0：正常，1：删除）
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

5. product（商品）

字段 | 数据类型 | 注释
---- | ---- | ----
product_id | unsigned bigint | 商品编号
category_id | unsigned bigint | 类别编号
name | varchar(20) | 商品名称
description | varchar(255) | 商品描述
price | decimal(12,2) | 商品单价（单位：元）
count | unsigned int | 商品数量
image_path | varchar(255) | 图片路径
status | unsigned tinyint(2) | 商品状态(1:正常；2:下架；3：删除)
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

6. order（订单）

字段 | 数据类型 | 注释
---- | ---- | ----
order_id | unsigned bigint | 订单编号
user_id | unsigned bigint | 用户编号
shipping_address_id | unsigned bigint | 收货地址编号
user_message | varchar(255) | 用户留言
status | unsigned tinyint(2) | 订单状态(1:已生成；2：已发货；3:交易成功；4：交易关闭)
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间
gmt_consign | datetime(3) | 发货时间
gmt_success | datetime(3) | 交易成功时间
gmt_close | datetime(3) | 交易关闭时间

7. order_item（订单项）

字段 | 数据类型 | 注释
---- | ---- | ----
order_item_id | unsigned bigint | 订单项编号
order_id | unsigned bigint | 订单编号
product_id | unsigned bigint | 商品编号
count | unsigned int | 购买数量
gmt_create | datetime(3) | 创建时间
gmt_modified | datetime(3) | 更新时间

## 备注

1. 【强制】表达是与否概念的字段,必须使用 is_xxx 的方式命名,数据类型是 unsigned tinyint
( 1 表示是,0 表示否 ) 。
说明:任何字段如果为非负数,必须是 unsigned 。
正例:表达逻辑删除的字段名 is_deleted ,1 表示删除,0 表示未删除。
2. 【强制】表名、字段名必须使用小写字母或数字 , 禁止出现数字开头,禁止两个下划线中间只
出现数字。数据库字段名的修改代价很大,因为无法进行预发布,所以字段名称需要慎重考虑。
说明:MySQL 在 Windows 下不区分大小写,但在 Linux 下默认是区分大小写。因此,数据库
名、表名、字段名,都不允许出现任何大写字母,避免节外生枝。
正例: aliyun_admin , rdc_config , level3_name
反例: AliyunAdmin , rdcConfig , level_3_name
6. 【强制】小数类型为 decimal ,禁止使用 float 和 double 。
说明: float 和 double 在存储的时候,存在精度损失的问题,很可能在值的比较时,得到不
正确的结果。如果存储的数据范围超过 decimal 的范围,建议将数据拆成整数和小数分开存储。
7. 【强制】如果存储的字符串长度几乎相等,使用char定长字符串类型。
9. 【强制】表必备三字段: id , gmt_create , gmt_modified 。
说明:其中 id 必为主键,类型为 unsigned bigint 、单表时自增、步长为 1。 gmt_create,
gmt_modified 的类型均为 datetime 类型,前者现在时表示主动创建,后者过去分词表示被
动更新。
