<!DOCTYPE html>
<html>
<head>
    <title>Shop</title>
</head>
<body>
<h1 class="main-title"></span>Shop</h1><hr>

<h4>Товары</h4>
<#list products as product>
    <div>
        <p class="text">${product.title}</b> ${product.price} руб. | <a class="text" href="/product/${product.id}">Подробнее...</a></p>
    </div>
    <#else>
    <h3>Товаров нет</h3>
</#list>

<hr>

<h4>Добавить категорию</h4>
<form action="/category/create" method="post">
    <p>Категория:</p> <input type="text" name="category"/><br><br>
   <input type="submit" value="Добавить категорию"/>
</form>

<hr>

<h4>Добавить поставщика</h4>
<form action="/supplier/create" method="post">
    <p>Поставщик:</p> <input type="text" name="supplier"/><br><br>
    <input type="submit" value="Добавить поставщика"/>
</form>

<hr>

<h4>Создать новый товар</h4>
<form action="/product/create" method="post">
    <p>Товар:</p> <input type="text" name="title"/><br><br>
    <p>Описание товара:</p> <input type="text" name="description"/><br><br>
    <p>Цена:</p> <input type="number" name="price"/><br><br>
    <p>Город:</p> <input type="text" name="city"/><br><br>
    <p>Магазин:</p> <input type="text" name="author"/><br><br>

    <p>Категория:</p>
    <select name="categoryName">
        <#list categories as category>
            <option value="${category.name}">${category.name}</option>
        </#list>
    </select><br><br>

    <p>Поставщик:</p>
    <select name="supplierName">
        <#list suppliers as supplier>
            <option value="${supplier.name}">${supplier.name}</option>
        </#list>
    </select><br><br>

    <input type="submit" value="Добавить товар"/>
</form>

</body>
</html>